package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    static int N =5;
    public static Semaphore mutex =new Semaphore(1,1, "mutex");
    // Koyta cell faka ache.
    public static Semaphore empty =new Semaphore(N,N, "empty");
    // Koyta cell fill ache.
    public static Semaphore full =new Semaphore(0,N, "full");
    public static Queue<Integer> buffer = new LinkedList<Integer>();

    public static void main(String[] args) {
        Producer producer1=new Producer(mutex,empty,full,buffer);
        Producer producer2=new Producer(mutex,empty,full,buffer);

        Consumer consumer1=new Consumer(mutex,empty,full,buffer);
        Consumer consumer2=new Consumer(mutex,empty,full,buffer);


        new Thread(producer1,"Producer 1").start();

        new Thread(consumer1,"Consumer 1").start();

        new Thread(producer2,"Producer 2").start();

        new Thread(consumer2,"Consumer 2").start();
    }
}
