/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Servidor;
import dominio.Usuario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Zisko
 */
public class ServidorXml implements Serializable {
    private Element root;
   
    private String fileLocation = "src//Archivos//ServidorBaseDatos.xml";
    
    public ServidorXml() {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            root = doc.getRootElement();
        } catch (JDOMException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        }
    }
    
    private Element ServidorXmlElement(Servidor nServidor) {
        Element servidorE = new Element("Servidor");
        
        Element id = new Element("Id");
        id.setText(Integer.toString(nServidor.getId()));
        
        Element ip = new Element("Ip");
        ip.setText(nServidor.getIp());
        
        Element rol = new Element("Rol");
        rol.setText(Integer.toString(nServidor.getRol()));
        
        Element port = new Element("Port");
        port.setText(Integer.toString(nServidor.getPort()));
        
        Element state = new Element("State");
        state.setText(Integer.toString(nServidor.getState()));

        servidorE.addContent(id);
        servidorE.addContent(ip);
        servidorE.addContent(rol);
        servidorE.addContent(port);
        servidorE.addContent(state);
        
        return servidorE;
    }
    
     private Servidor ServidorToObject(Element element) throws ParseException {
        Servidor nServidor = new Servidor(Integer.parseInt(element.getChildText("Id")), Integer.parseInt(element.getChildText("Port")), Integer.parseInt(element.getChildText("State")), Integer.parseInt(element.getChildText("Rol")), element.getChildText("Ip")) { 
       };
       return nServidor;
       }
     
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(fileLocation);
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
      
    public static Element buscar(List raiz, Integer id) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (id==Integer.parseInt(e.getChild("Id").getText())) {
                return e;
            }
        }
        return null;
    }
    
    public boolean agregarServidor(Servidor nServidor) {
        boolean resultado = false;
        root.addContent(ServidorXmlElement((Servidor) nServidor));
        resultado = updateDocument();
        return resultado;
    }
    
    public Servidor buscarServidor(Integer id) {
        Element aux = new Element("Servidor");
        List Servidor = this.root.getChildren("Servidor");
        while (aux != null) {
            
            aux = ServidorXml.buscar(Servidor, id);
            if (aux != null) {
                try {
                    return ServidorToObject(aux);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return null;
    }
    
    public boolean actualizarServidor(Servidor nServidor, int id) {
        boolean resultado = false;
        Element aux = new Element("Servidor");
        List Servidor = this.root.getChildren("Servidor");
        while (aux != null) {
            aux = ServidorXml.buscar(Servidor, id);
            if (aux != null) {
                Servidor.remove(aux);
                resultado = updateDocument();
            }
        }
        agregarServidor(nServidor);
        return resultado;
    }
    
    public ArrayList<Servidor> todosLosServidores() {
        ArrayList<Servidor> resultado = new ArrayList<Servidor>();     
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            try {
                    resultado.add(ServidorToObject(xmlElem));                                  
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
}
