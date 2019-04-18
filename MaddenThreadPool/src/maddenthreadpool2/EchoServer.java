package maddenthreadpool2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Joseph Madden
 * Date Created : 04/05/2019
 * Date Modified: 04/12/2019
 * 
 * An multi-threaded EchoServer program that uses thread pool to
 * limit the number of active threads.
 * 
 * EchoServer : The server opens and listens for a client infinitely. When
 *              a new connection is found, a Connection object is created.
 *              The new connection is then added to the ThreadPool.
 * 
 * EchoClient : The client tries to open a connection on the local host.
 *              It reads in a line of code from the keyboard and writes it to 
 *              the server and then writes the servers response to the console.
 *              Once a user writes a single period ( '.' ) the connection is
 *              terminated. 
 * 
 * Connection : The construction of this object initializes and instance
 *              variable with reference to a socket. The Runnable interface is 
 *              implemented to allow thread execution. The run method has a
 *              loop that reads in a line of text from the client. The same
 *              input is then flushed back out to the client. 
 * 
 * ThreadPool : Consists of two inner classes: WorkQueue, WorkerThread.
 *              Creates an array of threads and instantiates the WorkQueue.
 *              The array is then filled with WorkerThread objects and starts
 *              them. The size of the array is a constant 3.
 * 
 *   WorkQueue: This class utilizes a LinkedList object to store 
 *              the runnable tasks. These threads are synchronized for 
 *              proper use. Inner methods are used to add and get tasks. 
 *              A thread will block if a task is requested when not available.
 * 
 *  WorkerThread: Implements the Runnable interface. The run method attempts to 
 *                get a task from the queue and run it.
 * 
 * 
 */
public class EchoServer {
    public final static int PORT = 1111;
    
    public static void main(String[] args) throws IOException{
        System.out.println("SERVER CONNECTED");
        ServerSocket socket = new ServerSocket(PORT); // opens server socket
        ThreadPool tp = new ThreadPool(); // initiates threadpool object
       
        while (true) {   // infinite loop; looks for client connections infinitely
           
                Socket clientSocket = socket.accept(); // server socket accepts new connection to a client socket
                System.out.println("CLIENT CONNECTED");
                Connection c = new Connection(clientSocket);
                tp.addTask(c); // adds new connection to the threadpool
        }
    }
}




















