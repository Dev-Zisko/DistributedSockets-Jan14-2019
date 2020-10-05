/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Zisko
 */
public class SocketClienteSSL {

    public static void main(String[] args) throws IOException {
		SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		Socket client = clientFactory.createSocket("192.168.0.100", 8000);
    }

}
