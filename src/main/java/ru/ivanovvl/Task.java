package ru.ivanovvl;

/**
 * Задание с ключем.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface Task extends Runnable {
    String getKey();
}
