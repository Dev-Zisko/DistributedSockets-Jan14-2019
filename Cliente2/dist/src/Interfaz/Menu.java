/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Control.Controladora;
import dominio.Archivo;
import dominio.SocketCliente;
import dominio.Usuario;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author richa
 */
public class Menu extends javax.swing.JFrame {
    
    Controladora control;
    Socket socket;
    String user, pass;
    
    
    /**
     * Creates new form Menu
     */
    public Menu(String username, String password) {
        initComponents();
        user = username;
        pass = password;
        control = new Controladora (this);
    }
    
    public Menu() {
        initComponents();
        control = new Controladora (this);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Bcommit = new javax.swing.JButton();
        Bupdate = new javax.swing.JButton();
        Bcheckout = new javax.swing.JButton();
        Bsalir = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        PanelVacio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PanelCommit = new javax.swing.JPanel();
        PanelUpdate = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUpdate = new javax.swing.JTable();
        buArchivoUpdate = new javax.swing.JButton();
        PanelCheckout = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCheckout = new javax.swing.JTable();
        buArchivoCheckout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Bcommit.setText("Commit");
        Bcommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcommitActionPerformed(evt);
            }
        });

        Bupdate.setText("Update");
        Bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BupdateActionPerformed(evt);
            }
        });

        Bcheckout.setText("Checkout");
        Bcheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcheckoutActionPerformed(evt);
            }
        });

        Bsalir.setText("Salir");
        Bsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Bcheckout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bcommit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(Bcommit)
                .addGap(18, 18, 18)
                .addComponent(Bupdate)
                .addGap(18, 18, 18)
                .addComponent(Bcheckout)
                .addGap(18, 18, 18)
                .addComponent(Bsalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido al controlador de versiones!");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Commit:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Update:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Checkout:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Sube archivos al servidor principal y los réplica en otros servidores.");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Permite traer la última versión del archivo seleccionado.");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Permite elegir que versión del archivo traerse.");

        javax.swing.GroupLayout PanelVacioLayout = new javax.swing.GroupLayout(PanelVacio);
        PanelVacio.setLayout(PanelVacioLayout);
        PanelVacioLayout.setHorizontalGroup(
            PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVacioLayout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVacioLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(PanelVacioLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(PanelVacioLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        PanelVacioLayout.setVerticalGroup(
            PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVacioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(48, 48, 48)
                .addGroup(PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(62, 62, 62)
                .addGroup(PanelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jLayeredPane1.add(PanelVacio, "card5");

        javax.swing.GroupLayout PanelCommitLayout = new javax.swing.GroupLayout(PanelCommit);
        PanelCommit.setLayout(PanelCommitLayout);
        PanelCommitLayout.setHorizontalGroup(
            PanelCommitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        PanelCommitLayout.setVerticalGroup(
            PanelCommitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        jLayeredPane1.add(PanelCommit, "card2");

        tablaUpdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaUpdate);

        buArchivoUpdate.setText("Traer Archivo Seleccionado");
        buArchivoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buArchivoUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelUpdateLayout = new javax.swing.GroupLayout(PanelUpdate);
        PanelUpdate.setLayout(PanelUpdateLayout);
        PanelUpdateLayout.setHorizontalGroup(
            PanelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUpdateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buArchivoUpdate)
                .addGap(251, 251, 251))
        );
        PanelUpdateLayout.setVerticalGroup(
            PanelUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUpdateLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(buArchivoUpdate)
                .addContainerGap())
        );

        jLayeredPane1.add(PanelUpdate, "card3");

        tablaCheckout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaCheckout);

        buArchivoCheckout.setText("Traer Archivo Seleccionado");
        buArchivoCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buArchivoCheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCheckoutLayout = new javax.swing.GroupLayout(PanelCheckout);
        PanelCheckout.setLayout(PanelCheckoutLayout);
        PanelCheckoutLayout.setHorizontalGroup(
            PanelCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCheckoutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buArchivoCheckout)
                .addGap(251, 251, 251))
        );
        PanelCheckoutLayout.setVerticalGroup(
            PanelCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCheckoutLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(buArchivoCheckout)
                .addContainerGap())
        );

        jLayeredPane1.add(PanelCheckout, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BcommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BcommitActionPerformed
        //control.activarPanel(PanelCommit, PanelUpdate, PanelCheckout, PanelVacio);
        try{
            socket = new Socket("192.168.0.100",8000);
        }catch (IOException ex){
            System.out.println("Conexion caida en commit.");
        }
        
        JFileChooser filechooser=new JFileChooser();
        int returnVal = filechooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = filechooser.getSelectedFile();
                DataInputStream input;
                BufferedInputStream bis;
                BufferedOutputStream bos;
                int in;
                byte[] byteArray;
                try
                {
                    bis = new BufferedInputStream(new FileInputStream(file));                  
                    bos = new BufferedOutputStream(socket.getOutputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("002|"+user);
                    System.out.println("002-Enviado archivo seleccionado del commit.");
                    dos.writeUTF(file.getName());
                    JOptionPane.showMessageDialog(this, "Fichero Enviado Correctamente.");
                    //Enviamos el fichero
                    byteArray = new byte[8192];
                    while ((in = bis.read(byteArray)) != -1){
                        bos.write(byteArray,0,in);
                    }
                    bis.close();
                    bos.close();
                }catch ( Exception e ) {
                    System.err.println("Error de envio de archivo de commit. "+e);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Seleccion de archivo cancelado.");
            }
    }//GEN-LAST:event_BcommitActionPerformed

    private void BupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BupdateActionPerformed
        control.activarPanel(PanelUpdate, PanelCommit, PanelCheckout, PanelVacio);
        tablaUpdate.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Nombre del Archivo", "Fecha de subida"}));
        JOptionPane.showMessageDialog(this, "Cargando tabla por favor espere.");
        Archivo archivo = null;
        ArrayList<Archivo> listaArchivos = null;
        try{
            socket = new Socket("192.168.0.100",8000);
        }catch (IOException ex){
            System.out.println("Conexion caida en update.");
        }
        try{
            DataOutputStream dos;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("003|"+user);
            System.out.println("003-Enviado actualizacion de tabla de archivos del update.");
            ObjectInputStream ois;
            ois  = new ObjectInputStream(socket.getInputStream());
            listaArchivos = (ArrayList) ois.readObject();
            System.out.println("003-Tabla llenada con los datos recibidos del update.");
            ois.close();
            dos.close();      
        }catch (IOException ex){
            System.out.println("Conexion caida al intentar llenar tabla del update.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        control.llenarTabla(listaArchivos, tablaUpdate);
        JOptionPane.showMessageDialog(this, "Tabla de la version mas reciente de archivos subidos. Lista!.");
    }//GEN-LAST:event_BupdateActionPerformed

    private void BcheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BcheckoutActionPerformed
        control.activarPanel(PanelCheckout, PanelUpdate, PanelCommit, PanelVacio);
        tablaCheckout.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Nombre del Archivo", "Fecha de subida"}));
        JOptionPane.showMessageDialog(this, "Cargando tabla por favor espere.");
        Archivo archivo = null;
        ArrayList<Archivo> listaArchivos = null;
        try{
            socket = new Socket("192.168.0.100",8000);
        }catch (IOException ex){
            System.out.println("Conexion caida en checkout.");
        }
        try{
            DataOutputStream dos;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("004|"+user);
            System.out.println("004-Enviado actualizacion de tabla de archivos del checkout.");
            ObjectInputStream ois;
            ois  = new ObjectInputStream(socket.getInputStream());
            listaArchivos = (ArrayList) ois.readObject();
            System.out.println("004-Tabla llenada con los datos recibidos del checkout.");
            ois.close();
            dos.close();
      
        }catch (IOException ex){
            System.out.println("Conexion caida al intentar llenar tabla del checkout.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        control.llenarTabla(listaArchivos, tablaCheckout);
        JOptionPane.showMessageDialog(this, "Tabla de todas las versiones de archivos subidos. Lista!.");
    }//GEN-LAST:event_BcheckoutActionPerformed

    private void BsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsalirActionPerformed
        JOptionPane.showMessageDialog(this, "Desconectado correctamente. Hasta luego "+user+"!.");    
        Login login =new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_BsalirActionPerformed

    private void buArchivoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buArchivoUpdateActionPerformed
        int row = tablaUpdate.getSelectedRow();
        String name = tablaUpdate.getValueAt(row, 0).toString();
        String date = tablaUpdate.getValueAt(row, 1).toString();
        try{
            socket = new Socket("192.168.0.100",8000);
        }catch (IOException ex){
            System.out.println("Conexion caida al realizar transferencia del archivo del update.");
        }
        try{
            DataOutputStream dos;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("005|"+user+"|"+name+"|"+date);
            System.out.println("005-Enviados datos de busqueda de archivo seleccionado.");
            JOptionPane.showMessageDialog(this, "Buscando archivo en el servidor. Por favor Espere!.");
            BufferedInputStream bis;
            BufferedOutputStream bos;
            byte[] receivedData;
            int in;
            String file;
             //Buffer de 1024 bytes
            receivedData = new byte[1024];
            bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
            file = file.substring(file.indexOf('\\')+1,file.length());
            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while ((in = bis.read(receivedData)) != -1){
                bos.write(receivedData,0,in);
            }
            bos.close();
            dis.close();
            int prueba = control.crearArchivo(user, file, 0);
            if(prueba==1){
                JOptionPane.showMessageDialog(this, "Archivo recibido en su escritorio, revise la carpeta con su nombre de usuario!.");
            }
            if(prueba==0){
                JOptionPane.showMessageDialog(this, "Archivo no encontrado en el servidor. Intente nuevamente!.");
            }
            dos.close();
        }catch (IOException ex){
            System.out.println("Conexion caida en la busqueda de archivos del update.");
        }
    }//GEN-LAST:event_buArchivoUpdateActionPerformed

    private void buArchivoCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buArchivoCheckoutActionPerformed
        int row = tablaCheckout.getSelectedRow();
        String name = tablaCheckout.getValueAt(row, 0).toString();
        String date = tablaCheckout.getValueAt(row, 1).toString();
        try{
            socket = new Socket("192.168.0.100",8000);
        }catch (IOException ex){
            System.out.println("Conexion caida al realizar transferencia del archivo del checkout.");
        }
        try{
            System.out.println("Iniciamos conexion");
            DataOutputStream dos;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("006|"+user+"|"+name+"|"+date);
            System.out.println("006-Enviados datos de busqueda de archivo seleccionado.");
            JOptionPane.showMessageDialog(this, "Buscando archivo en el servidor. Por favor Espere!.");
            BufferedInputStream bis;
            BufferedOutputStream bos;
            byte[] receivedData;
            int in;
            String file;
             //Buffer de 1024 bytes
            receivedData = new byte[1024];
            bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
            file = file.substring(file.indexOf('\\')+1,file.length());
            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while ((in = bis.read(receivedData)) != -1){
                bos.write(receivedData,0,in);
            }
            bos.close();
            dis.close();
            int prueba = control.crearArchivo(user, file, 0);
            if(prueba==1){
                JOptionPane.showMessageDialog(this, "Archivo recibido en su escritorio, revise la carpeta con su nombre de usuario!.");
            }
            if(prueba==0){
                JOptionPane.showMessageDialog(this, "Archivo no encontrado en el servidor. Intente nuevamente!.");
            }
            dos.close();
        }catch (IOException ex){
            System.out.println("Conexion caida en la busqueda de archivos del checkout.");
        }
    }//GEN-LAST:event_buArchivoCheckoutActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*try {
                    PrintStream fileOut = new PrintStream("Logs_Client.txt");
                     System.setOut(fileOut);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                   
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bcheckout;
    private javax.swing.JButton Bcommit;
    private javax.swing.JButton Bsalir;
    private javax.swing.JButton Bupdate;
    private javax.swing.JPanel PanelCheckout;
    private javax.swing.JPanel PanelCommit;
    private javax.swing.JPanel PanelUpdate;
    private javax.swing.JPanel PanelVacio;
    private javax.swing.JButton buArchivoCheckout;
    private javax.swing.JButton buArchivoUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCheckout;
    private javax.swing.JTable tablaUpdate;
    // End of variables declaration//GEN-END:variables
}
