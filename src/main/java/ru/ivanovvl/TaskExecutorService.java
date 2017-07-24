package ru.ivanovvl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Реализация очереди задач с относительным порядком:
 * 1. задания с разными значениями ключа могут выполняться параллельно
 * 2. задания с одинаковыми значениями ключа выполняются последовательно и в порядке поступления в очередь
 *
 * Следует понимать, что в худшем случае половина потоков будет ожидать окончания выполнения
 * задач с одинаковым ключем.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public class TaskExecutorService implements TaskExecutor {
    private final ExecutorService executor;
    private final Map<String, Future<?>> keyInProgress;
    public TaskExecutorService(final int countThreads) {
        executor = Executors.newFixedThreadPool(countThreads);
        keyInProgress = new ConcurrentHashMap<>();
    }
    @Override
    public void submit(final Task task) {
        final Future<?> future = keyInProgress.compute(task.getKey(),
            (key, prevTask) -> {
                if (prevTask == null) { // Задача с таким ключем не исполняется
                    return executor.submit(() -> {
                        try {
                            task.run();
                        } finally {
                            keyInProgress.remove(task.getKey());
                        }
                    });
                }
                // Выполняется задача с таким ключем
                return executor.submit(() -> {
                    System.out.printf("Wait key=%s\n", task.getKey());
                    try {
                        prevTask.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        task.run();
                    } finally {
                        keyInProgress.remove(task.getKey());
                    }
                });
            });
    }
}

