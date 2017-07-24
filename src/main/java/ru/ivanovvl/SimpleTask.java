package ru.ivanovvl;

/**
 * Задача, которая ожидает заданное время.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public class SimpleTask implements Task {
    private final String key;
    private final long millis;
    public SimpleTask(final String key, final long millis) {
        this.key = key;
        this.millis = millis;
    }
    @Override
    public String getKey() {
        return key;
    }
    @Override
    public void run() {
        System.out.printf("Start key=%s, time=%d\n", key, millis);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("End key=%s, time=%d\n", key, millis);
    }
}
