package com.example.demo;

import java.io.*;
import java.util.*;

public class CountNodes {
    String [] data;
    String node1;
    String node2;
    int node_inc = 100;
    public int countNumberOfNodes(String fileLink) throws IOException {

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting node: ");
        int starting_node = sc.nextInt();
        Iterator itr=arr.iterator();
        while(itr.hasNext())
        {
            i++;
            listOfWords.put((String) itr.next(), i);
            System.out.println(listOfWords.size()+" "+i);


            while (listOfWords.size()==(node_inc)){
                node_inc+=100;
                System.out.println("============");

                FileHandling fileHandling = new FileHandling(fileLink, listOfWords.size());
                fileHandling.time_cal(starting_node);
//                fileHandling.calculate_DFS(7);
//                fileHandling.calculate_BFS(7);

//                return listOfWords.size();

            }
//            System.out.println(listOfWords.size());
        }

        System.out.println("The number of nodes: "+listOfWords.size());
        return listOfWords.size();
    }
}
