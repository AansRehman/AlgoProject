package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Graph {
    private int V;
    private LinkedList<Integer> adj[];

    BFS_Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v,int w)
    {
//        System.out.println("Edge adding");
        adj[v].add(w);
    }

    long write (Queue<Integer> queue)
    {
        long start = System.currentTimeMillis();
        try {
            FileWriter fileWriter = new FileWriter("BFS_Queue.txt",true);
            fileWriter.write(queue+"\n");
            fileWriter.close();
//            System.out.println("File Written");
        }catch(IOException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long writing_time = end-start;
        return writing_time;
    }
    long BFS(int s) {
        long writing_time = 0;
        long start = System.currentTimeMillis();

        boolean visited[] = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.remove();
            System.out.print(s + " ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
             writing_time += write(queue);
        }
        long end = System.currentTimeMillis();
        long execution_time =  (end-start);
        System.out.println();
        System.out.println("The Writing Time in milli seconds for BFS: "+ (writing_time));
        System.out.println("The Total Time in milli seconds for BFS: "+ (execution_time));
        return (execution_time);
    }
}
