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
public class IntSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Hello");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted");
                        break;
                    }
                }
            }
        };
        
        Thread background= new Thread(runnable);
        background.start();
        
        Thread.sleep(2000);
        background.interrupt();        
    }

}
