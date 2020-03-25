package edu.rpi.project.examdatabase.examdb;

/**
 * Any object that can be cached should implement this interface
 */
public interface Cachable {
    void setTime();
    long getTime();
}
