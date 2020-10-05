package ru.itis.hw1;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 10.09.2020
 * Threads
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ThreadPool {
    private Deque<Runnable> tasks;


    private PoolWorker[] pool;

    public static ThreadPool newPool(int threadsCount) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.tasks = new ConcurrentLinkedDeque<>();
        threadPool.pool = new PoolWorker[threadsCount];

        for (int i = 0; i < threadPool.pool.length; i++) {
            threadPool.pool[i] = threadPool.new PoolWorker();
            threadPool.pool[i].start();
        }

        return threadPool;
    }


    public void submit(Runnable task) {
        // TODO: реализовать
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            while (true) {
                // TODO: реализовать
                synchronized (tasks) {
                    if (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
                Runnable task = tasks.pollFirst();
                task.run();
            }
        }
    }
}
