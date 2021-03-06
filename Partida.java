/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dames.ui;

/**
 *
 * @author Alumne
 */
public class Partida extends javax.swing.JFrame {
    
    private static boolean jugaX;
    private static boolean jugaO;
    private static int filaOrigen;
    private static int columnaOrigen;
    
    /**
     * Creates new form Partida
     */
    public Partida() {
        initComponents();
        jugaX = true;
        jugaO = false;
        filaOrigen = -1;
        columnaOrigen = -1;
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taulell = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Sortir");

        taulell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X", null, "X", null, "X", null, "X", null},
                {null, "X", null, "X", null, "X", null, "X"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"O", null, "O", null, "O", null, "O", null},
                {null, "O", null, "O", null, "O", null, "O"}
            },
            new String [] {
                "Columna 1", "Columna 2", "Columna 3", "Columna 4", "Columna 5", "Columna 6", "Columna 7", "Columna 8"
            }
        ));
        taulell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulellMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taulell);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void taulellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulellMouseClicked
        // TODO add your handling code here:
        int fila = obtenirFilaClicada();
        int columna = obtenirColumnaClicada();
        
        System.out.println(fila);
        System.out.println(columna);
        
        if(noHiHaOrigen()){
            if(jugaX && EsX(fila,columna)){
                ActualitzaNouOrigen(fila,columna);
            }else if(jugaO && EsO(fila,columna)){
                ActualitzaNouOrigen(fila,columna);
            }else{
                mostraError();
            }
        }else{
            if(movimentVàlid(fila,columna)){ //si diagonal cap avall per X o cap a dalt per O
                if(esBuit(fila,columna) || OcupatContrari(fila,columna)){
                    mou(fila,columna);
                }
            }else{
                if(OcupatPropi(fila,columna)){
                    ActualitzaNouOrigen(fila,columna);
                }else{
                    mostraErrorMoviment();
                }
            }
        }
        
    }//GEN-LAST:event_taulellMouseClicked
    
    private int obtenirFilaClicada(){
        return taulell.getSelectedRow();
    }
    
    private int obtenirColumnaClicada(){
        return taulell.getSelectedColumn();
    }
    
    private boolean noHiHaOrigen(){
        return (filaOrigen == -1);
    }
    
    private boolean EsX(int fila,int columna){
        return (taulell.getValueAt(fila, columna).equals("X"));
    }
    
    private boolean EsO(int fila,int columna){
        return (taulell.getValueAt(fila, columna).equals("O"));
    }
    
    private void ActualitzaNouOrigen(int fila, int columna){
        filaOrigen = fila;
        columnaOrigen = columna;
    }
    
    private void mostraError(){
        System.err.println("S'ha produït un error");
    }
    
    private boolean movimentVàlid(int fila, int columna){
        boolean moviment = false;
        
        if(jugaO && fila == filaOrigen-1 && (columna == columnaOrigen+1 || columna == columnaOrigen-1)){
            moviment = true;
        }
        
        if(jugaX && fila == filaOrigen+1 && (columna == columnaOrigen+1 || columna == columnaOrigen-1)){
            moviment = true;
        }
        
        return moviment;
    }
    
    private boolean esBuit(int fila, int columna){
        return ((taulell.getValueAt(fila, columna) == null) || (taulell.getValueAt(fila, columna) == ""));
    }
    
    private boolean OcupatContrari(int fila, int columna){
//        String valor = (taulell.getValueAt(fila, columna) == null)?"": (String)taulell.getValueAt(fila, columna);
//        if(jugaO && valor.equals("X")){
//            return true;
//        }else if(jugaX && valor.equals("O")){
//            return true;
//        }else{
//            return false;
//        }
        return !OcupatPropi(fila, columna);
    }
    
    private void mou(int fila, int columna){
        if(jugaO){
            taulell.setValueAt("O", fila, columna);
            taulell.setValueAt("", filaOrigen, columnaOrigen);
        }else{
            taulell.setValueAt("X", fila, columna);
            taulell.setValueAt("", filaOrigen, columnaOrigen);
        }
        jugaX = jugaO;
        jugaO = !jugaX;
        filaOrigen = -1;
        columnaOrigen = -1;
    }
    
    private boolean OcupatPropi(int fila, int columna){
        String valor = (taulell.getValueAt(fila, columna) == null)?"": (String)taulell.getValueAt(fila, columna);
        if(jugaO && valor.equals("O")){
            return true;
        }else if(jugaX && valor.equals("X")){
            return true;
        }else{
            return false;
        }
    }
    
    private void mostraErrorMoviment(){
        System.err.println("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Partida().setVisible(true);
            }
        });
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable taulell;
    // End of variables declaration//GEN-END:variables
}
