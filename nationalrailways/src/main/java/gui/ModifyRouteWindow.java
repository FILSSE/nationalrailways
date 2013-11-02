/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModifyRouteWindow.java
 *
 * Created on 16-May-2011, 23:00:15
 */
package gui;

import businesslogic.ModifyRouteController;
import domain.Route;
import domain.RouteList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AndreiM
 */
public class ModifyRouteWindow extends javax.swing.JFrame {

    /** Creates new form ModifyRouteWindow */
    public ModifyRouteWindow() {
       initComponents();
       refreshTable();
    }

    private void refreshTable(){
         
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        while(model.getRowCount()!=0){
            model.removeRow(model.getRowCount()-1);
        }
        RouteList rl=ModifyRouteController.getRouteList();
        for(Route r:rl.getListRoutes()){
            model.addRow(new Object[]{r.getDepartureTime(),r.getArrivalTime(),r.getDistance(),r.getListStation().getListStation().get(0).getName(),r.getListStation().getListStation().get(1).getName(),r.getTrain().getType(),r.getTrain().getNoSeats(),r.getTrain().getId()});          
        }
        jTable1.setModel(model);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Departure Time", "Arrival Time", "Distance", "Departure Station", "Arrival Station", "Train Type", "Seats", "TrainID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Make Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(683, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int indexSelected=jTable1.getSelectedRow();
//        System.out.println(jTable1.getValueAt(indexSelected, 7));
        
        
 /*       for(int i=0;i<=7;i++){
            System.out.println(i+":"+jTable1.getValueAt(indexSelected, i).getClass());
        } */
        if(indexSelected!=(-1)){
            ModifyRouteController.updateRoute(jTable1.getValueAt(indexSelected, 0).toString(),jTable1.getValueAt(indexSelected,1 ).toString(),Integer.parseInt(jTable1.getValueAt(indexSelected, 2).toString()),jTable1.getValueAt(indexSelected, 3).toString(),(String)jTable1.getValueAt(indexSelected, 4),jTable1.getValueAt(indexSelected, 5).toString(),Integer.parseInt(jTable1.getValueAt(indexSelected, 6).toString()),Integer.valueOf(jTable1.getValueAt(indexSelected, 7).toString()));
            refreshTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
