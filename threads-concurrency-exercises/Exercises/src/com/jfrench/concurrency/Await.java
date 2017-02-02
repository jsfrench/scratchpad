/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfrench.concurrency;

/**
 *
 * @author french2
 */
public class Await {

    static volatile int count = 0;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Waiting...");
                synchronized (Await.class) {
                    count++;
                    try {
                        Thread.sleep(2000);
                        while (count < 3) {
                            Await.class.wait();
                        }
                    } catch (InterruptedException ex) {
                    }
                }
                System.out.println("Terminating...");
            }
        };

        Runnable conditionRunnable = new Runnable() {
            @Override
            public void run() {
                while (count < 3) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                    }
                }
                synchronized (Await.class) {
                    Await.class.notifyAll();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(conditionRunnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}
