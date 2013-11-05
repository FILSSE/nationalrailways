/** 
 * This program is designed in order to illustrate and verify the communication between 
 * the database server and the rest application for a normal "Buy Ticket" use case scenarion
 * that the application offers
 *
 * Author: Carmen Casaru
 *
 *
 * flags : -s -r -n, -t -p
 */

/* Definition of messages on which the communication is based */
mtype{ 

	/* These messages form the handshake packets needed to establish the connection 
    between a terminal and the database server */
	Connection_Request,
	Connection_Established_Reply,

	/* This state is required by the database server letting the terminal know that it is ready
	to receive and process requests */
	Waiting_For_Request,

	/* Requesting a ticket */
	Terminal_Ticket_Request,
	Terminal_Ticket_Results_Reply,

	/* Pay desired ticket */
	Pay_Ticket_Request,
	Pay_Ticket_Reply,

	/* Pay chash */
	Pay_Cash_Request,
	Pay_Cash_Reply,

	/* Pay with credit card */
	Pay_With_Credit_Card_Request,
	Pay_With_Credit_Card_Reply_Reply,

	/* These messages comprise the packets needed to end a connection between a terminal and
	the database server */
	Connection_End_Request,
	Connection_End_Established_Reply,
};

/* The number of terminals that are available and can be connected to the database server */
#define terminalNumber 3

/* A global synchronous channel used for communication between the terminal and the database 
server */
chan ssocket = [0] of { mtype, chan, chan };

/* The database server */
active proctype DataBaseServer()
{
	/* Used to hold a uniqe ID for each terminal with which the database server establishes a connection */
	int terminalID = 0;

	/* Input and output channels. Can store max 1 message per channel */
    chan ChannelIn[terminalNumber] = [1] of { mtype};
	chan ChannelOut[terminalNumber] = [1] of { mtype};

	do
		/**************************** Establish Connection *****************************/

		/* Waiting for a connection request from a terminal */
		::  ssocket ? Connection_Request(ChannelIn[terminalID], ChannelOut[terminalID])->

			/* Establish connection. Send reply to terminal */
			ssocket ! Connection_Established_Reply(ChannelIn[terminalID], ChannelOut[terminalID]) ->

			/* Inform terminal that the database server is ready to receive and process requests */
			ssocket ! Waiting_For_Request(ChannelIn[terminalID], ChannelOut[terminalID]) ->
			
			/********************************* Buy Ticket **********************************/
			
			/* Terminal request for all available train tickets */
			ssocket ? Terminal_Ticket_Request(ChannelIn[terminalID], ChannelOut[terminalID]) ->
			
			/* Send the list of all available tickets to the terminal */
			ssocket ! Terminal_Ticket_Results_Reply(ChannelIn[terminalID], ChannelOut[terminalID])->
			
			/* The client wants to pay for the desired ticket */
			ssocket ? Pay_Ticket_Request(ChannelIn[terminalID], ChannelOut[terminalID])->

			/* Inform the terminal the the payment protocol has been initiated and that the payment 
			can commence */
			ssocket ! Pay_Cash_Reply(ChannelIn[terminalID], ChannelOut[terminalID])->
			
			if
				/* Client has chosen to pay cash */
				::ssocket ? Pay_Cash_Request(ChannelIn[terminalID], ChannelOut[terminalID]) ->

					/*Confirm cash payment */
					ssocket ! Pay_Cash_Reply(ChannelIn[terminalID], ChannelOut[terminalID]);

				/* Client has chose to pay using a credit card */
				::ssocket ? Pay_With_Credit_Card_Request(ChannelIn[terminalID], ChannelOut[terminalID]) ->

					/*Confirm credit card payment */
					ssocket ! Pay_With_Credit_Card_Reply_Reply(ChannelIn[terminalID], ChannelOut[terminalID]);
			fi;

			/******************************* End Connection ********************************/
			
			/* The terminal has initiated the disconnect sequence */
			ssocket ? Connection_End_Request(ChannelIn[terminalID], ChannelOut[terminalID]) ->

			/* Confirm connection termination */
			ssocket ! Connection_End_Established_Reply(ChannelIn[terminalID], ChannelOut[terminalID]) ->

			/* Increase Id corresponding to next terminal connection */
			terminalID++;
			
		/* Upon reaching the maximum number of terminal connections, stop */
		::terminalID == terminalNumber ->
		break;
	od;
}

/* The terminal */
active [terminalNumber] proctype Terminal()
{
	/* Input and output channels. Can store max 1 message per channel */
	chan TerminalChanIn = [1] of { mtype};
	chan TerminalChanOut = [1] of { mtype};

    /**************************** Establish Connection *****************************/

	/* Send a connection request to the database server */
	ssocket ! Connection_Request( TerminalChanOut, TerminalChanIn) -> 

	/* Wait for acknowlegment from dabase server, letting us know that the connection has been established 
	successfully */
	ssocket ? Connection_Established_Reply(TerminalChanOut, TerminalChanIn) ->

	/* Wait for message from database server, letting us know that it is ready to receive requests 
	and process them */
	ssocket ? Waiting_For_Request(TerminalChanOut, TerminalChanIn) ->

	/********************************* Buy Ticket **********************************/

	/* Look up all available tickets */
	ssocket ! Terminal_Ticket_Request(TerminalChanOut, TerminalChanIn) ->

	/* Wait for a list of all available tickets from the database server */
	ssocket ? Terminal_Ticket_Results_Reply(TerminalChanOut, TerminalChanIn)->

	/* Pay for desired ticket */
	ssocket ! Pay_Ticket_Request(TerminalChanOut, TerminalChanIn)->

	/* Wait for confirmation from the database server that the payments protocol has been initiated 
	successfully and that the payment operation can commence */
	ssocket ? Pay_Cash_Reply(TerminalChanOut, TerminalChanIn)->
	
	if
		/* Choose to pay with cash */
		::ssocket ! Pay_Cash_Request(TerminalChanOut, TerminalChanIn);

		/* Choose to pay with a credit card */
		::ssocket ! Pay_With_Credit_Card_Request(TerminalChanOut, TerminalChanIn);
	fi;

	if
		/* Wait for cash payment confirmation */
		::ssocket ? Pay_Cash_Reply(TerminalChanOut, TerminalChanIn);

		/* Wait for credit card payment confirmation */
		::ssocket ? Pay_With_Credit_Card_Reply_Reply(TerminalChanOut, TerminalChanIn);
	fi;

	/******************************* End Connection ********************************/

   	/* Send Connection_End_Request */
	ssocket ! Connection_End_Request(TerminalChanOut, TerminalChanIn)-> 

	/* Wait for acknowledgement form database server */
	ssocket ? Connection_End_Established_Reply(TerminalChanOut, TerminalChanIn);
}
