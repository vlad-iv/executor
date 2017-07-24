package ru.ivanovvl;

/**
 * Очередь заданий.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface TaskExecutor {
    void submit(Task task);
}
