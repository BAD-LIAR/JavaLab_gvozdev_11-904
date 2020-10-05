package ru.itis.hw1;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Bulat_vs_Ildar {
    static Deque<String> tasks = new ConcurrentLinkedDeque<>();

    public static void main(String[] args) {
        tasks.add("Hello");

        if(!tasks.isEmpty())
        new A().start();
        if(!tasks.isEmpty())
        new A().start();
        if(!tasks.isEmpty())
        new A().start();
        if(!tasks.isEmpty())
        new A().start();
        if(!tasks.isEmpty())
        new A().start();
    }

}

class A extends Thread{
    @Override
    public void run() {
        System.out.println(Bulat_vs_Ildar.tasks.pollFirst());
    }
}
