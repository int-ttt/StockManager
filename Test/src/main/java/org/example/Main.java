package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Main m = new Main();

        Thread.sleep(100);

        System.out.print("\\033[F\\r");
    }
}