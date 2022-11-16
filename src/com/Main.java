package com;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {


        FileOutputStream out = null;
        try {
            out = new FileOutputStream("log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collection<Integer> list =  new LoggableCollection<Integer>(new ArrayList<Integer>(), out);

        list.add(5);
        list.add(6);
        list.add(7);

        for (var el : list) // тут неявно вызывается метод Collection.iterator
        {
            System.out.println(el);
        }

        list.remove(6);

        for (var el : list) // тут неявно вызывается метод Collection.iterator
        {
            System.out.println(el);
        }

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
