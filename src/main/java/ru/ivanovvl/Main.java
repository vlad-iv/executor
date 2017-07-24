package ru.ivanovvl;

/**
 * Очередь заданий с относительным порядком
 *
 * Реализовать очередь заданий, в которой:
 * задания с разными значениями ключа могут выполняться параллельно
 * задания с одинаковыми значениями ключа выполняются последовательно и в порядке поступления в очередь
 *
 *
 * Пример: если значение ключа - это номер счёта в банке, а задание - операция со счётом, то операции над одним и тем же счётом должны выполняться последовательно и в правильном порядке, при этом операции над разными счетами могут выполняться параллельно для увеличения общей производительности.
 *
 * Пример: подход, при котором очередь выполняет все задания последовательно в единственном потоке, удовлетворяет условию на порядок выполнения заданий с одинаковым значением ключа.
 */
public class Main {
    public static void main(String[] args) {
        TaskExecutor executor = new TaskExecutorService(3);
        executor.submit(new SimpleTask("TASK", 5000));
        executor.submit(new SimpleTask("TASK", 2000));
        executor.submit(new SimpleTask("TASK-2", 3000));
        executor.submit(new SimpleTask("TASK-3", 1000));
        executor.submit(new SimpleTask("TASK", 1000));
        executor.submit(new SimpleTask("TASK-2", 1000));
        executor.submit(new SimpleTask("TASK-3", 3000));
        executor.submit(new SimpleTask("TASK-4", 2000));
    }
}
