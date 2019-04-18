package maddenthreadpool2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Joseph Madden 
 * Date Created : 04/05/2019
 * Date Modified: 04/10/2019
 *
 */
public class Connection implements Runnable {

    private Socket iSocket = new Socket(); // instance variable

    public Connection(Socket socket) throws IOException {
        this.iSocket = socket; // initializes instance variable
    }

    @Override
    public void run() {
        try { // connects to the client inputstream
            Scanner in = new Scanner(this.iSocket.getInputStream());
            PrintWriter out = new PrintWriter(this.iSocket.getOutputStream(), true); // connects to the client outputstream
            
            while (in.hasNext()) { // while true :: temporary 
                out.println("Server: " + in.nextLine()); // prints client input as server
                out.flush();
            }
        } catch (IOException e) {
            
        }
    }
}
