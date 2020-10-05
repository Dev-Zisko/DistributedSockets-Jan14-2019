/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import controlador.Controladora;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //PrintStream fileOut = new PrintStream("Logs_Server.txt");
        //System.setOut(fileOut);
      //Se crean los serversockets
        int id = 2;
        int idactual = 0;
        int rol = 0;
        int entrada = 0;
        int entre = 0;
        int encontre = 0;
        ServerSocket socketC = null;
        ServerSocket socketS = null;
        Controladora control;
        control = new Controladora();
        //Colocar estado en activo=1 y rol del lider
        //rol = control.setearRol();
        //idactual = rol;
        if(entre==0){
            try {
                Socket socket10 = new Socket("192.168.0.100",10000);
                if(socket10.isBound() && encontre == 0){
                    idactual = 0;
                    rol = idactual;
                    encontre = 1;
                }
                socket10.close();
            } catch (Exception e) {
                System.out.println("El servidor 0 no esta activo.");
            }
            try {
                Socket socket10 = new Socket("192.168.0.100",10001);
                if(socket10.isBound() && encontre == 0){
                    idactual = 1;
                    rol = idactual;
                    encontre = 1;
                }
                socket10.close();
            } catch (Exception e) {
                System.out.println("El servidor 1 no esta activo.");
            }
            try {
                Socket socket10 = new Socket("192.168.0.100",10003);
                if(socket10.isBound() && encontre == 0){
                    idactual = 3;
                    rol = idactual;
                    encontre = 1;
                }
                socket10.close();
            } catch (Exception e) {
                System.out.println("El servidor 3 no esta activo.");
            }
            entre = 1;
        }
        else{
            idactual = 2;
            rol = idactual;
        }
        System.out.println("El rol actual es: "+ rol);
        boolean estado1 = control.setearEstado1(id);
        if(estado1==true){
            System.out.println("Estado cambiado correctamente.");
        }
        else{
            System.out.println("Error al cambiar estado.");
        }
        //##########################
        System.out.print("Inicializando servidor... ");
        try {
            socketC = new ServerSocket(8002);
            socketS = new ServerSocket(10002);
            //#########################################
            System.out.println("\t[OK]");
            int idSessionC = 0;
            int idSessionS = 0;
            //Para identificar los ids de cada conexión y validaciones
            //########################################################
            while (true) {
                //Se crean los sockets de conexión
                Socket socketCC;
                Socket socketSS;
                //#################################
                //Se espera conexión de los servidores
                //####################################
                //Se espera por conexiones de los clientes
                //########################################
                try {
                    if(rol==0){
                        idactual = 0;
                        Socket socket = new Socket("192.168.0.100",10000);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
                        int idarchivo = control.buscarArchivo();
                        dos1.writeUTF("003|"+Integer.toString(idarchivo));
                        System.out.println("003-Envio de solicitud de réplica.");
                        DataInputStream dis1 = new DataInputStream(socket.getInputStream());
                        String mensaje = dis1.readUTF();
                        System.out.println("003-Mensaje recibido.");
                        String[] parts = mensaje.split("\\|");
                        if(parts[0].equals("002")){
                            System.out.println("002-Recibido archivo a replicar.");
                            DataOutputStream output;
                            BufferedInputStream bis1;
                            BufferedOutputStream bos1;
                            byte[] receivedData;
                            int in;
                            String file;
                             //Buffer de 1024 bytes
                            receivedData = new byte[1024];
                            bis1 = new BufferedInputStream(socket.getInputStream());
                            dis1 = new DataInputStream(socket.getInputStream());
                            //Recibimos el nombre del fichero
                            file = dis1.readUTF();
                            file = file.substring(file.indexOf('\\')+1,file.length());
                            //Para guardar fichero recibido
                            bos1 = new BufferedOutputStream(new FileOutputStream(file));
                            while ((in = bis1.read(receivedData)) != -1){
                                bos1.write(receivedData,0,in);
                            }
                            bos1.close();
                            bis1.close();
                            int prueba = control.crearArchivo(parts[1], file, 0);
                            if(prueba==1){
                                System.out.println("002-Archivo replicado correctamente.");
                            }
                            if(prueba==0){
                                System.out.println("002-No se pudo replicar el archivo.");
                            }
                        }
                        else{
                            System.out.println("002-Aun no hay archivos que replicar");
                        }
                        Socket socket1 = new Socket("192.168.0.100",10000);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos11 = new DataOutputStream(socket1.getOutputStream());
                        dos11.writeUTF("030|"+id);
                        System.out.println("030-Envio de id de estado de conexion.");                       
                        DataInputStream dis11 = new DataInputStream(socket1.getInputStream());
                        System.out.println("030-Mensaje recibido.");
                        ArrayList<dominio.Servidor> listaServidores = null;
                        ObjectInputStream ois;
                        ois  = new ObjectInputStream(socket1.getInputStream());
                        listaServidores = (ArrayList) ois.readObject();
                        ois.close();
                        control.setearEstado(listaServidores);
                    }
                    else if(rol==2){
                        idactual = 2;
                        try {
                            socketC.setSoTimeout(2000);
                            socketCC = socketC.accept();
                            if(socketCC.isBound()){
                                System.out.println("Nueva conexión entrante de cliente: "+socketCC);
                                ((HiloC) new HiloC(socketCC, idSessionC)).start();
                                idSessionC++; 
                            }
                        } catch (java.io.InterruptedIOException e) {
                            System.out.println("Tiempo de espera de cliente agotado");
                        }
                        try {
                            socketS.setSoTimeout(2000);
                            socketSS = socketS.accept();
                            if(socketSS.isBound()){
                                idSessionS = 02;
                                System.out.println("Nueva conexión entrante del servidor: "+socketSS);
                                ((HiloS) new HiloS(socketSS, idSessionS)).start(); 
                            }
                        } catch (java.io.InterruptedIOException e) {
                            System.out.println("Tiempo de espera de servidor agotado");
                        }                        
                    }
                    else if(rol==1){
                        idactual = 1;
                        Socket socket = new Socket("192.168.0.100",10001);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
                        int idarchivo = control.buscarArchivo();
                        dos1.writeUTF("003|"+Integer.toString(idarchivo));
                        System.out.println("003-Envio de solicitud de réplica.");
                        DataInputStream dis1 = new DataInputStream(socket.getInputStream());
                        String mensaje = dis1.readUTF();
                        System.out.println("003-Mensaje recibido.");
                        String[] parts = mensaje.split("\\|");
                        if(parts[0].equals("002")){
                            System.out.println("002-Recibido archivo a replicar.");
                            DataOutputStream output;
                            BufferedInputStream bis1;
                            BufferedOutputStream bos1;
                            byte[] receivedData;
                            int in;
                            String file;
                             //Buffer de 1024 bytes
                            receivedData = new byte[1024];
                            bis1 = new BufferedInputStream(socket.getInputStream());
                            dis1 = new DataInputStream(socket.getInputStream());
                            //Recibimos el nombre del fichero
                            file = dis1.readUTF();
                            file = file.substring(file.indexOf('\\')+1,file.length());
                            //Para guardar fichero recibido
                            bos1 = new BufferedOutputStream(new FileOutputStream(file));
                            while ((in = bis1.read(receivedData)) != -1){
                                bos1.write(receivedData,0,in);
                            }
                            bos1.close();
                            bis1.close();
                            int prueba = control.crearArchivo(parts[1], file, 0);
                            if(prueba==1){
                                System.out.println("002-Archivo replicado correctamente.");
                            }
                            if(prueba==0){
                                System.out.println("002-No se pudo replicar el archivo.");
                            }
                        }
                        else{
                            System.out.println("002-Aun no hay archivos que replicar");
                        }
                        Socket socket1 = new Socket("192.168.0.100",10001);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos11 = new DataOutputStream(socket1.getOutputStream());
                        dos11.writeUTF("030|"+id);
                        System.out.println("030-Envio de id de estado de conexion.");                       
                        DataInputStream dis11 = new DataInputStream(socket1.getInputStream());
                        System.out.println("030-Mensaje recibido.");
                        ArrayList<dominio.Servidor> listaServidores = null;
                        ObjectInputStream ois;
                        ois  = new ObjectInputStream(socket1.getInputStream());
                        listaServidores = (ArrayList) ois.readObject();
                        ois.close();
                        control.setearEstado(listaServidores);
                    }
                    else if(rol==3){
                        idactual = 3;
                        Socket socket = new Socket("192.168.0.100",10003);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
                        int idarchivo = control.buscarArchivo();
                        dos1.writeUTF("003|"+Integer.toString(idarchivo));
                        System.out.println("003-Envio de solicitud de réplica.");
                        DataInputStream dis1 = new DataInputStream(socket.getInputStream());
                        String mensaje = dis1.readUTF();
                        System.out.println("003-Mensaje recibido.");
                        String[] parts = mensaje.split("\\|");
                        if(parts[0].equals("002")){
                            System.out.println("002-Recibido archivo a replicar.");
                            DataOutputStream output;
                            BufferedInputStream bis1;
                            BufferedOutputStream bos1;
                            byte[] receivedData;
                            int in;
                            String file;
                             //Buffer de 1024 bytes
                            receivedData = new byte[1024];
                            bis1 = new BufferedInputStream(socket.getInputStream());
                            dis1 = new DataInputStream(socket.getInputStream());
                            //Recibimos el nombre del fichero
                            file = dis1.readUTF();
                            file = file.substring(file.indexOf('\\')+1,file.length());
                            //Para guardar fichero recibido
                            bos1 = new BufferedOutputStream(new FileOutputStream(file));
                            while ((in = bis1.read(receivedData)) != -1){
                                bos1.write(receivedData,0,in);
                            }
                            bos1.close();
                            bis1.close();
                            int prueba = control.crearArchivo(parts[1], file, 0);
                            if(prueba==1){
                                System.out.println("002-Archivo replicado correctamente.");
                            }
                            if(prueba==0){
                                System.out.println("002-No se pudo replicar el archivo.");
                            }
                        }
                        else{
                            System.out.println("002-Aun no hay archivos que replicar");
                        }
                        Socket socket1 = new Socket("192.168.0.100",10003);
                        System.out.println("Conectado correctamente al servidor principal.");
                        DataOutputStream dos11 = new DataOutputStream(socket1.getOutputStream());
                        dos11.writeUTF("030|"+id);
                        System.out.println("030-Envio de id de estado de conexion.");                       
                        DataInputStream dis11 = new DataInputStream(socket1.getInputStream());
                        System.out.println("030-Mensaje recibido.");
                        ArrayList<dominio.Servidor> listaServidores = null;
                        ObjectInputStream ois;
                        ois  = new ObjectInputStream(socket1.getInputStream());
                        listaServidores = (ArrayList) ois.readObject();
                        ois.close();
                        control.setearEstado(listaServidores);
                    }
                } catch (Exception ex) {
                    boolean estado0 = control.setearEstado0(idactual);
                    if(estado0==true){
                        System.out.println("Estado cambiado correctamente.");
                    }
                    else{
                        System.out.println("Error al cambiar estado.");
                    }
                    rol = control.nuevoRol(idactual, id);
                    idactual = rol;
                    if(rol==id){
                        System.out.println("Soy el nuevo servidor maestro.");
                    }
                    else{
                        System.out.println("El nuevo servidor maestro es: "+rol);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
