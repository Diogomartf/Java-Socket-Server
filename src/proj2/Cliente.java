/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2;

/**
 *
 * @author diogomartf
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    
   public static void main(String[] args) throws Exception {

        System.out.println("Iniciando cliente.");

        System.out.println("Iniciando conexão com o servidor.");

        Socket socket = new Socket("localhost", 2525);

        System.out.println("Conexão establecida.");

        InputStream input = socket.getInputStream ();
        OutputStream output = socket.getOutputStream ();

        BufferedReader in = new BufferedReader (new InputStreamReader (input));
        PrintStream out = new PrintStream(output);

        Scanner scanner = new Scanner (System.in);
   
        while(true)  {
            System.out.print("Digite o seu ip: ");
            String mensagem = scanner.nextLine();

            out.println(mensagem);
            
             if(mensagem.charAt(0) == '1') {
                //Receber mensagem do servidor e imprimir.
               String msg;
               while ((msg = in.readLine()) != null) {              
                    System.out.println(msg);  
                }
               break;
            }else if("FIM".equals(mensagem)) {
                break;
            }

        }
   
    System.out.println("Encerrando coneção.");

    in.close();

    out.close();

    socket.close();
   }
}
