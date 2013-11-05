#define match = 1
/* Definition of types */
mtype {
		login
		successfulLogin,
		accesRegistry,
		allowedAccess,
		makeChanges,
		successfulChanges,
		updateRegistry,
		successfulMessage,
		logout,
		successfulLogout
	}
	
/* -- Global definitions
--------------------------------------------------------------- */

#define iSize 100 /*Size of the internal event channel buffers(>=1) */

/* --- External event channels --- */
chan DADS = [iSize] of {mtype} /* Channel for sending-receiving messages */

proctype DesktopApplication(){

Start:atomic{ /*All operations are executed as atomic transition */
	printf("\n DesktopApplication: Start\n");
	DADS!login; /* sending of messages */
	DADS?successfulLogin; /* reception of messages */
	goto Next1;
	}

Next1:atomic{ /*All operations are executed as atomic transition */
	printf("\n DesktopApplication: Next1\n");
	DADS!accesRegistry; /* sending of messages */
	DADS?allowedAccess; /* reception of messages */
	goto Next2;
	}

Next2:atomic{ /*All operations are executed as atomic transition */
	printf("\n DesktopApplication: Next2\n");
	DADS!makeChanges; /* sending of messages */
	DADS?successfulChanges; /* reception of messages */
	goto Next3; /* if we want a cycle */
	}

Next3:atomic{ /*All operations are executed as atomic transition */
	printf("\n DesktopApplication: Next3\n");
	DADS!updateRegistry; /* sending of messages */
	DADS?successfulMessage; /* reception of messages */
	goto Next4; /* if we want a cycle */
	}

Next4:atomic{ /*All operations are executed as atomic transition */
	printf("\n DesktopApplication: Next4\n");
	DADS!logout; /* sending of messages */
	DADS?successfulLogout; /* reception of messages */
	goto Start; /* if we want a cycle */
	}
	
}

proctype DatabaseServer(){

Start:atomic{ /*All operations are executed as atomic transition */
	printf("\n DatabaseServer: Start\n");
	DADS?login; /* reception of messages */
	DADS!successfulLogin; /* sending of messages */
	goto Next1;
	}
	
Next1:atomic{ /*All operations are executed as atomic transition */
	printf("\n DatabaseServer: Next1\n");
	DADS?accesRegistry; /* reception of messages */
	DADS!allowedAccess; /* sending of messages */
	goto Next2;
	}
	
Next2:atomic{ /*All operations are executed as atomic transition */
	printf("\n DatabaseServer: Next2\n");
	DADS?makeChanges; /* reception of messages */
	DADS!successfulChanges; /* sending of messages */
	goto Next3;
	}

Next3:atomic{ /*All operations are executed as atomic transition */
	printf("\n DatabaseServer: Next3\n");
	DADS?updateRegistry; /* reception of messages */
	DADS!successfulMessage; /* sending of messages */
	goto Next4;
	}

Next4:atomic{ /*All operations are executed as atomic transition */
	printf("\n DatabaseServer: Next4\n");
	DADS?logout; /* reception of messages */
	DADS!successfulLogout; /* sending of messages */
	goto Start;
	}

100
}

/* --- Initialization --------------------------------*/

init{
	
	run DesktopApplication();
	run DatabaseServer();

}
