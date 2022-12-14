package com.bham.pij.assignments.converters;

public class Tools {
    public static int String2Int(String value){
        int counter = 0;
        int result = 0;
        for (int i = value.length()-1;i>=0;i--){
            int index = 1;
            for (int m = 0;m<counter;m++){
                index = index * 10;
            }
            result = result + (value.charAt(i) - 48)*index;
            counter++;
        }
        return result;
    }
}
