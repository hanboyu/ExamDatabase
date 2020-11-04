package edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity;


import java.util.LinkedList;
import java.util.List;

public class StringHelperFunctions {

    /**
     * The function split a string by then given delimiter(s). The function
     * does NOT put empty string at front or back when the delimiter is at the
     * front or the back of the string. The function does NOT add empty string
     * when two or more delimiters are next to each other. If no delimiter(s)
     * is given, it will use a single space as delimiter
     * @param str The source string to split
     * @param delimiters zero or more delimiters. A delimiter is a single
     *                   character
     * @return a list of split string
     */
    public static List<String> split(String str, char ... delimiters ){
        if (delimiters.length == 0){ // Default delimiter
            delimiters = new char[]{' '};
        }
        if (str.equals("")){ // Empty string
            return new LinkedList<>();
        }

        List<String> res = new LinkedList<>();
        char[] char_array = str.toCharArray();
        int start = 0;
        int end = 0;
        for (int i = 0; i < char_array.length; i++){
            boolean match = false;
            for (int j = 0; j < delimiters.length; j++){
                if (char_array[i] == delimiters[j]){
                    match = true;
                    break;
                }
            }
            if (match || i == char_array.length - 1){
                    if (match && i != char_array.length - 1){
                        if (start != i) { res.add(str.substring(start, i)); }
                    }
                    else if (!match && i == char_array.length - 1){
                       res.add(str.substring(start, i + 1));
                    }

                    if (match && i == char_array.length - 1){
                        if (start != i) { res.add(str.substring(start, i)); }
                    }
                start = i + 1;
            }
        }
        return res;
    }

}
