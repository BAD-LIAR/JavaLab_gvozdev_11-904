package ru.itis.hw1;

public class Main {

    public static void main(String[] args) {

        ThreadPool threadPool = ThreadPool.newPool(3);

        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("A " + Thread.currentThread().getName());
            }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println("B " + Thread.currentThread().getName());
            }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("C " + Thread.currentThread().getName());
            }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("D " + Thread.currentThread().getName());
            }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("E " + Thread.currentThread().getName());
            }
        });

    }
}
