package maddenthreadpool;

import java.util.LinkedList;

/**
 * @author Joseph Madden
 * Date Created : 04/05/2019
 * Date Modified: 04/12/2019
 *
 */
public class ThreadPool {

    public static final int SIZE = 3; // this many clients are allowed to be connected at a time
    WorkQueue wq;

    /**
     * Creates an array of threads and instantiates the work queue. It fills the
     * array with WorkerThread objects and starts them.
     */
    public ThreadPool() {

        Thread[] threads = new Thread[SIZE];    // creates an array of threads
        wq = new WorkQueue();          // instantiate WorkQueue

        for (int i = 0; i < threads.length; i++) {
            System.out.println("Creating Thread " + i);
            threads[i] = new Thread(new WorkerThread());
            threads[i].start();
        }
    }

    /**
     * Gathers task to add to threadpool and then adds it to WorkQueue
     *
     * @param c connection object
     */
    public void addTask(Connection c) {
        wq.addTask(c);
    }

    /**
     * Stores a linked list of runnable tasks. Utilizes the LinkedList class.
     * One method to add a task and one to get a task.
     */
    public class WorkQueue {

        // stores a linked list of runnable tasks
        LinkedList<Connection> list = new LinkedList<>();

        /**
         * adds a task to the LinkedList
         * @param c connection object
         */
        public synchronized void addTask(Connection c) {
            list.add(c);
            notifyAll();
        }

        /**
         * Pops a task from the LinkedList
         *
         * @return a connection object
         */
        public synchronized Connection popTask() {
            while (list.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException ex) {

                }
            }
            return list.pop();
        }
    }

    /**
     * Implements the runnable interface. The run method tries to get a task
     * from the queue and run it.
     */
    public class WorkerThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                wq.popTask().run();
            }
        }
    }
}
