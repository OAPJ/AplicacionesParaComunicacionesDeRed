/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *
 * @author ovall
 */
public class ServidorArchivo extends javax.swing.JFrame implements Runnable {
//    private boolean bandera = true;
//    private String aux[];
    private String decision;
    private String decisionServidor = "";
    private String nombreArchivo;
    private Thread hilo;
    
    //Para recivir
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream entrada;
    private InputStream llegada;
    private FileOutputStream destino;
    
    //Para mandar
    private DataOutputStream salida;
    private OutputStream os;
    private PrintStream envio;
    private File archivo;

    /**
     * Creates new form ServidorArchivo
     */
    public ServidorArchivo() {
        initComponents();
        bloquer();
        iniciarHilo();
        try {
            servidor = new ServerSocket(4000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jLabel4.setText("Esperando...");
    }

    public void iniciarHilo() {
        hilo = new Thread(this);
        hilo.start();
    }

//    public void pararHilo() {
//        bandera = false;
//    }

    public void bloquer() {
        btEnviar.setEnabled(false);
        btMostrar.setEnabled(false);
        btRecibir.setEnabled(false);
    }

    public void desbloquear() {
        btEnviar.setEnabled(true);
        btMostrar.setEnabled(true);
        btRecibir.setEnabled(true);
    }

    @Override
    public void run() {
        //Aqui va todo lo del hilo
//        while(bandera){
        try {
            cliente = servidor.accept();
            // Creamos flujo de entrada para leer los datos que envia el cliente
            entrada = new DataInputStream(cliente.getInputStream());
            // Obtenemos La decisión del cliente
            decision = entrada.readUTF();
            System.out.println(decision);
            
            if (decision.equals("1")) {
                recibir();
            } else if (decision.equals("2")) {
                enviar();
            } else {
                mostrar();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        }
    }

    public void enviar() {
        try {
            System.out.println("A");
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(decisionServidor);
//            os = cliente.getOutputStream();
//            envio = new PrintStream(os);
//            //Seleccionamos el archivo
//            archivo = openFile();
//            //Enviamos el nombre del archivo
//            salida = new DataOutputStream(cliente.getOutputStream());
//            salida.writeUTF(archivo.getName());
//            //Creamos el flujo de entrada para relizar la lectura del archivo
//            FileInputStream origen = new FileInputStream(archivo.getAbsoluteFile());
//            //Creamos un array de byte
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while((len = origen.read(buffer)) > 0)
//                envio.write(buffer, 0, len);
//            System.out.println("Archivo enviado: "+archivo.getName());
//            origen.close();
//            envio.close();
//            cliente.close();
            bloquer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recibir() {
        try {
            nombreArchivo = entrada.readUTF();
            llegada = cliente.getInputStream();
            // indicar donde guardaremos el archivo
            destino = new FileOutputStream("Compartir/"+nombreArchivo);
            // Creamos el array de bytes para leer los datos del archivo
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = llegada.read(buffer)) > 0) 
                destino.write(buffer);
            destino.close();
            llegada.close();
//            cliente.close();
            jLabel4.setText("Archivo Recibido "+nombreArchivo);
            //Desbloqueamos para que el servidor tome una opcion
            desbloquear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrar() {

    }
    
    public File openFile(){
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = selector.showOpenDialog(null);
        if (res == 1 )
            return null;
        File archivo = selector.getSelectedFile();
        return selector.getSelectedFile();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btEnviar = new javax.swing.JButton();
        btRecibir = new javax.swing.JButton();
        btMostrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        archivos = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Servidor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Archivos");

        btEnviar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        btRecibir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btRecibir.setText("Recibir");

        btMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btMostrar.setText("Mostrar");

        archivos.setColumns(20);
        archivos.setRows(5);
        jScrollPane1.setViewportView(archivos);

        jLabel3.setText("Estado:");

        jLabel4.setText("Desconectado");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(146, 146, 146)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btEnviar)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btRecibir)
                        .addGap(25, 25, 25)
                        .addComponent(btMostrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        // TODO add your handling code here:
        decisionServidor = "1";
        enviar();
    }//GEN-LAST:event_btEnviarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            destino.close();
            llegada.close();
            cliente.close();
            System.exit(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ServidorArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea archivos;
    private javax.swing.JButton btEnviar;
    private javax.swing.JButton btMostrar;
    private javax.swing.JButton btRecibir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
