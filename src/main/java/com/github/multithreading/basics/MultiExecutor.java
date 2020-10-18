package com.github.multithreading.basics;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    List<Runnable> tasks;

    MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Execute all the methods simultaneously.
     */
    public void executeALL() {

        List<Thread> list = new ArrayList<>();

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            list.add(thread);
        }

        for (Thread t : list) {
            t.start();
        }
    }

}
