/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Servidor {
   public static void main(String[] args) throws Exception {
   
   Ips ips = new Ips();
       
   System.out.println("Iniciando servidor.");
   
   ServerSocket server = new ServerSocket (2525);
   
   System.out.println("Aguardando conexão.");
   
   Socket socket = server.accept();
   
   System.out.println("Conexão establecida.");
   
   InputStream input = socket.getInputStream();
   OutputStream output = socket.getOutputStream ();
   
   BufferedReader in = new BufferedReader (new InputStreamReader(input));
   PrintStream out = new PrintStream(output);
   
   while (true) {
       String mensagem = in.readLine();
       
       System.out.println(
               "Mensagem recebida do cliente: " +
                           mensagem);
       
       
       if("FIM".equals(mensagem)) {
           break;
       }else if(mensagem.charAt(0) == '1') {
            
            ArrayList<String> ipList = ips.presences(mensagem);
           for(int i=0 ; i<ipList.size() ; i++){
                out.print(ipList.get(i));
            }
           break;
       }else if(null == mensagem){
           System.out.println("Valor null, encerrando coneção.");
           break;
       }
       
   }
       System.out.println("Encerrando conexão.");
       
       in.close();
       
       out.close();
       
       socket.close();
       
       System.out.println("Encerrando servidor.");
        
       server.close();
   }
}