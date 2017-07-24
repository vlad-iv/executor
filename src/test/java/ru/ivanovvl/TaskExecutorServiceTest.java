package ru.ivanovvl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * Проверка выполнения задач с разным и одинаковым ключем.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public class TaskExecutorServiceTest {
    @Test
    public void testTaskWithAnyKey() throws Exception {
        final TaskExecutorService executor = new TaskExecutorService(2);
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(stream, true, "UTF-8");
        System.setOut(printStream);
        executor.submit(new SimpleTask("TASK-1", 3000));
        executor.submit(new SimpleTask("TASK-2", 2000));
        Thread.sleep(3500);
        final String executionResult = new String(stream.toByteArray(), "UTF-8");
        Assert.assertEquals("Start key=TASK-1, time=3000\n" +
            "Start key=TASK-2, time=2000\n" +
            "End key=TASK-2, time=2000\n" +
            "End key=TASK-1, time=3000\n", executionResult);
    }

    @Test
    public void testTaskWithSameKey() throws Exception {
        final TaskExecutorService executor = new TaskExecutorService(2);
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(stream, true, "UTF-8");
        System.setOut(printStream);
        executor.submit(new SimpleTask("TASK", 2000));
        executor.submit(new SimpleTask("TASK", 1000));
        Thread.sleep(3500);
        final String executionResult = new String(stream.toByteArray(), "UTF-8");
        Assert.assertEquals("Start key=TASK, time=2000\n" +
            "Wait key=TASK\n" +
            "End key=TASK, time=2000\n" +
            "Start key=TASK, time=1000\n" +
            "End key=TASK, time=1000\n", executionResult);

    }
}