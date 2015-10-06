/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;



public class Ips {
    
     @SuppressWarnings("empty-statement")
     public ArrayList<String> presences(String myIp) throws IOException, URISyntaxException {
            //Analisa um dado URL e extrai o host, path, port e protocol
            String urlStr = "http://heartbeat.dsi.uminho.pt/heartbeat.svc/show?ip=" + myIp;
            
            //estrutura uri [scheme:][//authority][path][?query][#fragment]
            URI uri = new URI(urlStr); 
            String host = uri.getHost( ); 
            String path = uri.getRawPath( ); 
            if (path == null || path.length( ) == 0) {
                path = "/";
            } 

            String query = uri.getRawQuery( ); 
            if (query != null && query.length( ) > 0) {
                path += "?" + query;
            } 
        
        //Extrai o protocolo e port e certificasse que coincidem
        String protocol = uri.getScheme( ); 
        int port = uri.getPort( ); 
        if (port == -1) {
            if (protocol.equals("http")) { 
                port = 80; // http port 
            }
            else if (protocol.equals("https")) {
                port = 443; // https port 
            }
            else {
            }
        }
        
        /*
        1- Fazer um conecçao socket connection ao servidor; 
        2- Enviar um pedido HTTP corretamente formatado
        3- Receber a resposta
        */
        
       Socket socket = new Socket( host, port );
       
       /*Enviar este pedido usando o Socket's OutputStream 
       e simplesmente "imprimir" o pedido como uma string a este*/
       
       PrintWriter request = new PrintWriter( socket.getOutputStream() );
        request.print(  "GET " + path + " HTTP/1.1\r\n" + 
                               "Host: " + host + "\r\n" + 
                               "Connection: close\r\n\r\n"); 
        request.flush( ); 
        /*
        A "\r\n" (line feed, new line) combinaçoes certificam-se 
       que o pedido vai de encontro com a especificação de formato HTTP requeridas. 
        Para receber a resposta, usa-se o Socket's InputStream  e
        lê-se o resultado do text linha a linha
        */
     
        
        InputStream inStream = socket.getInputStream( ); 
        BufferedReader in = new BufferedReader(
                new InputStreamReader(inStream));
        String line = "";
        
        ArrayList<String> ipList = new ArrayList<String>() ; 
        
        while ((line = in.readLine()) != null) {     
            if(line.startsWith("1") | line.startsWith("2") | line.startsWith("8")){
                ipList.add(line);
            }
            
                 
        }
       return ipList;
    }
}
