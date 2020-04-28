package edu.rpi.project.examdatabase.examdb.Objects;

/**
 * Any object that can be cached should implement this interface
 */
public interface Cachable {
    void setTime();
    long getTime();
}
