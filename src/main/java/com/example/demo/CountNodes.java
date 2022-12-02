package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CountNodes {
    public int countNumberOfNodes(String fileLink) throws FileNotFoundException {

        File f = new File(fileLink);
        ArrayList arr=new ArrayList();
        HashMap<String, Integer> listOfWords = new HashMap<String, Integer>();
        Scanner in = new Scanner(f);
        int i=0;
        while(in.hasNext())
        {
            String s=in.next();
            arr.add(s);
        }
        Iterator itr=arr.iterator();
        while(itr.hasNext())
        {
            i++;
            listOfWords.put((String) itr.next(), i);
//            System.out.println(listOfWords.size());
        }
        System.out.println("The number of nodes: "+listOfWords.size());
        return listOfWords.size();
    }
}
