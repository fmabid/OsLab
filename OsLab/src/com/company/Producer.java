/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sudipta
 */
public class Producer implements Runnable{

    Semaphore mutex = null;
    Semaphore empty = null;
    Semaphore full = null;
    Queue<Integer> buffer=null;
    Random rand = new Random();

    public Producer(Semaphore mutex,Semaphore empty,Semaphore full,Queue<Integer> buffer){
        this.mutex=mutex;
        this.empty=empty;
        this.full=full;
        this.buffer=buffer;
    }

    @Override
    public void run() {
        int item;

        while(true){
            try {
                item = rand.nextInt(50) + 1;
                empty.down();
                mutex.down();
                buffer.add(item);
                System.out.println(Thread.currentThread().getName()+" produced: " + item);
                mutex.up();
                full.up();

                Thread.sleep(rand.nextInt(30) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }


}
