package com.hebta.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 工作线程池
 */
public class Worker {

    public static final ExecutorService WORKER = Executors.newFixedThreadPool(10);

    public static void execute(Runnable task) {
        WORKER.execute(task);
    }
}
