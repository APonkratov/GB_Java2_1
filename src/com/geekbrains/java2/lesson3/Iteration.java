package com.geekbrains.java2.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Iteration {
    public static void main(String[] args) {
        Comparable c;
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "C", "A", "A", "B", "C", "B"));
//        Iterator<String> iter = list.iterator();
//        while (iter.hasNext()) {
//            String str = iter.next();
//            if (str.equals("A")) {
//                iter.remove();
//            }
//        }
        List newList = new ArrayList();
//        iter = newList.iterator();
        System.out.println(list);
        for(String element: list) {
            if(element.equals("A")) list.remove(element);
        }
        System.out.println(list);
    }

}
