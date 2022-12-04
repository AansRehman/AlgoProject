package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class isCycle {
    private int V;
    private List<List<Integer>> adj;

    void write_in_cycle (int i)
    {
        try {
            FileWriter fileWriter = new FileWriter("Cycle.txt",true);
            File file = new File("Cycle.txt");
            System.out.println(file.length());
            if(file.length()==0){
                if(i==0)
                {
                    fileWriter.write("0");
                }
                else
                    fileWriter.write(i);
            }
            else {
                if(i==0)
                {
                    fileWriter.write("0");
                }
                else
                    fileWriter.write("\n"+i);
            }
//            fileWriter.write(i+"\n");
            fileWriter.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
    public isCycle(int V)
    {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            adj.add(new LinkedList<>());
    }
    public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack)
    {
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;

        recStack[i] = true;
        List<Integer> children = adj.get(i);

        System.out.println("Cycle at node: "+i);
        write_in_cycle(i);
        for (Integer c: children)
            if (isCyclicUtil(c, visited, recStack))
                return true;

        recStack[i] = false;

        return false;
    }

    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }
    public int isCyclic()
    {
        int count = 0;
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
       for (int i = 0; i < V; i++) {
           if (isCyclicUtil(i, visited, recStack)) {
//               System.out.println("Cycle at node: "+i);
//               write_in_cycle(i);
               count++;
           }
//               return count;
       }

       return count;
//        return count;
    }
}
