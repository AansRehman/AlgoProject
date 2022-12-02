package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class DFS_Graph {
    int V;
    LinkedList<Integer>[] adj;
    DFS_Graph(int V) {

        this.V = V;
        adj = new LinkedList[V];

        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<Integer>();

    }

    long write (Stack<Integer> stack)
    {
        long start = System.currentTimeMillis();
        try {
            FileWriter fileWriter = new FileWriter("DFS_Stack.txt",true);
            fileWriter.write(stack+"\n");
            fileWriter.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return (end-start);
    }
    void addEdge(int v, int w) {
        adj[v].add(w);
    }
    long DFS(int s) {

        long writing_time = 0;
        long start = System.currentTimeMillis();


        Vector<Boolean> visited = new Vector<Boolean>(V);
        for (int i = 0; i < V; i++)
            visited.add(false);

        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (stack.empty() == false) {
            s = stack.peek();
            stack.pop();

            if (visited.get(s) == false) {
                System.out.print(s + " ");
                visited.set(s, true);
            }

            Iterator<Integer> itr = adj[s].iterator();

            while (itr.hasNext()) {
                int v = itr.next();
                if (!visited.get(v))
                    stack.push(v);
            }
            writing_time += write(stack);
        }
        long end = System.currentTimeMillis();
        long execution_time =  (end-start);
        System.out.println();
        System.out.println("The Writing Time in milli seconds for DFS: "+ (writing_time));
        System.out.println("The Total Time in milli seconds for DFS: "+ (execution_time));
        return (execution_time-writing_time);
    }
}
