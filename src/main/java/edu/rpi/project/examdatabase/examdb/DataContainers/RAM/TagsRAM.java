package edu.rpi.project.examdatabase.examdb.DataContainers.RAM;

import java.util.Enumeration;
import java.util.Vector;

public class TagsRAM {
    private Vector<String> tags;
    private static TagsRAM instance = null;

    /** Constructor **/
    private TagsRAM(){
        tags = new Vector<>();
    }

    /** Getters **/

    public static TagsRAM getInstance(){
        if (instance == null){
            instance = new TagsRAM();
        }
        return instance;
    }

    public Enumeration<String> getAllTags() {
        return tags.elements();
    }
}
