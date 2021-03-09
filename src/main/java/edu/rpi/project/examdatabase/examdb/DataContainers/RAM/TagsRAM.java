package edu.rpi.project.examdatabase.examdb.DataContainers.RAM;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagsRAM {
    private static List<String> tags;

    public static Iterator<String> dummy_tag_itr() {
        /* Create dummy list */
        List<String> tag_list = new ArrayList<>();
        tag_list.add("chem 1");
        tag_list.add("chem 2");
        tag_list.add("carbon");
        tag_list.add("bonds");
        tag_list.add("test 1");
        tag_list.add("test 2");
        tag_list.add("test 3");

        /* Return an iterator to the list */
        return tag_list.iterator();
    }
}
