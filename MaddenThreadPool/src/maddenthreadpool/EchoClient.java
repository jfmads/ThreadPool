package maddenthreadpool;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Joseph Madden 
 * Date Created : 04/05/2019
 * Date Modified: 04/12/2019
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {
        System.out.println("CLIENT STARTING");
        final String HOST = "127.0.0.1";
        String clientInput;
        Scanner inClient = new Scanner(System.in); // reads in a line of text from the keyboard

        try (Socket clientSocket = new Socket(HOST, EchoServer.PORT)) { // tries to open connection on local host
            System.out.println("CLIENT CONNECTED");
            PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true); // output stream to server
            Scanner serverInput = new Scanner(clientSocket.getInputStream());
            
           while(true){ 
                System.out.print("Client: ");
                clientInput = inClient.nextLine(); // stores client input into a variable
                if(clientInput.equals(".")) // while client input != period
                    break;
                serverOut.println(clientInput); // writes to the client input to server 
                System.out.println(serverInput.nextLine()); // writes server response to console
           }
            System.out.println("Client done.");
            
        } catch (NoSuchElementException e) {

        }
    }
}
