package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class FileHandling {
    String fileLink;
    static private int nodes;
//    CountNodes countNodes = new CountNodes();


//    DFS_Graph dfs_graph = new DFS_Graph(nodes);
    DFS_Graph dfs_graph;
    BFS_Graph bfs_graph;
    isCycle cycle;

    public FileHandling(String fileLink, int nodes){
        this.fileLink = fileLink;
        this.nodes = nodes;
        dfs_graph= new DFS_Graph(nodes);
        bfs_graph = new BFS_Graph(nodes);
        cycle = new isCycle(nodes);
    }

    public void createFile(String FileName){
        try {
            File my_file = new File("Word-Meanings.txt");
            if (my_file.createNewFile())
            {
                System.out.println("File Created: "+my_file.getName());
            }else
                System.out.println("Already exist");
        }catch (IOException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    void write_in_DFS_Time (int numOfNodes, long execTime)
    {
        try {
            FileWriter fileWriter = new FileWriter("DFS_Time.txt",true);
            fileWriter.write(numOfNodes+","+execTime+"\n");
            fileWriter.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    void write_in_BFS_Time (int numOfNodes, long execTime)
    {
        try {
            FileWriter fileWriter = new FileWriter("BFS_Time.txt",true);
            fileWriter.write(numOfNodes+","+execTime+"\n");
            fileWriter.close();
        }catch(IOException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
    String[] data;
    String node1;
    String node2;

    public void read() throws IOException {

//        File f = new File(fileLink);
//        ArrayList arr=new ArrayList();
//        HashMap<String, Integer> listOfWords = new HashMap<String, Integer>();
//        Scanner in = new Scanner(f);
//        int j=0;
//        while(in.hasNext())
//        {
//            String s=in.next();
//            arr.add(s);
//        }
//        Iterator itr=arr.iterator();
//        FileReader fileReader = new FileReader(fileLink);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String line;
//        while(((line = bufferedReader.readLine())!=null))
//        {
////            if (((line = bufferedReader.readLine())!=null)) {
////                line = bufferedReader.readLine();
//                j++;
//                listOfWords.put((String) itr.next(), j);
//                data = line.split(" ");
//                node1 = data[0];
//                node2 = data[1];
//                bfs_graph.addEdge(Integer.parseInt(node1), Integer.parseInt((node2)));
//                dfs_graph.addEdge(Integer.parseInt((node1)), Integer.parseInt((node2)));
//                cycle.addEdge(Integer.parseInt(node1), Integer.parseInt(node2));
////            }
//            System.out.println(listOfWords.size());
//
//        }
////        bufferedReader.close();
//        System.out.println("The number of nodes: "+listOfWords.size());
//        menu();




        try {
            FileReader fileReader = new FileReader(fileLink);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i=0;(line = bufferedReader.readLine())!=null;i++)
            {
                data = line.split(" ");
                node1 = data[0];
                node2 = data[1];
                if (((Integer.parseInt(node1))<nodes) && (Integer.parseInt(node2)<nodes)) {
                    bfs_graph.addEdge(Integer.parseInt(node1), Integer.parseInt((node2)));
                    dfs_graph.addEdge(Integer.parseInt((node1)), Integer.parseInt((node2)));
                    cycle.addEdge(Integer.parseInt(node1), Integer.parseInt(node2));
                }
            }
            bufferedReader.close();
            menu();
        }catch (Exception e)
        {
            System.out.println("ERROR: File not readed");
            e.printStackTrace();
        }
    }

    public void menu()
    {
        System.out.println("Welcome to Algo");
        System.out.println("---MENU---");
        System.out.println("Press 1: BFS");
        System.out.println("Press 2: DFS");
        System.out.println("Press 3: is Cyclic");
        System.out.println("Press 4: All (BFS, DFS, Cycle)");
        System.out.println("Press 5: Show Chart");
        System.out.println("Press 0: Exit");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:{
                long exec_time;
                System.out.println("Enter the starting node: ");
                int starting_node = sc.nextInt();
                exec_time = bfs_graph.BFS(starting_node);
                System.out.println();
                System.out.println("The Execution Time in milli seconds for BFS: "+ exec_time);
                System.out.println();
                write_in_BFS_Time(nodes, exec_time);
                menu();
                break;
            }
            case 2:{
                long exec_time;
                System.out.println("Enter the starting node: ");
                int starting_node = sc.nextInt();
                exec_time = dfs_graph.DFS(starting_node);
                System.out.println();
                System.out.println("The Execution Time in milli seconds for DFS: "+ (exec_time));
                System.out.println();
//                write_in_DFS_Time(nodes, exec_time);
//                menu();
                break;
            }
            case 3:{
                long start = System.currentTimeMillis();
                if(cycle.isCyclic()>0)
                    System.out.println("Graph contains cycle(s): "+cycle.isCyclic());
                else
                    System.out.println("Graph doesn't contain cycle");
                long end = System.currentTimeMillis();
                System.out.println();
                System.out.println("The Execution Time in milli seconds for isCyclic: "+ (end-start));
                System.out.println();
                menu();
                break;
            }
            case 4:{
                System.out.println("Enter the starting node: ");
                int starting_node = sc.nextInt();
                for (int i=1; i<=nodes; i++, nodes -= (100)) {

                    long BFS_exec_time;
                    BFS_exec_time = bfs_graph.BFS(starting_node);
                    System.out.println();
                    System.out.println("The Execution Time in milli seconds for BFS: " + BFS_exec_time);
                    System.out.println();
                    write_in_BFS_Time(nodes, BFS_exec_time);


                    long DFS_exec_time = 0;
                    DFS_exec_time = dfs_graph.DFS(starting_node);
                    System.out.println();
                    System.out.println("The Execution Time in milli seconds for DFS: " + (DFS_exec_time));
                    System.out.println();
                    write_in_DFS_Time(nodes, DFS_exec_time);

                    long start_cycle = System.currentTimeMillis();
                    if (cycle.isCyclic() > 0)
                        System.out.println("Graph contains cycle(s): " + cycle.isCyclic());
                    else
                        System.out.println("Graph doesn't contain cycle");
                    long end_cycle = System.currentTimeMillis();
                    System.out.println();
                    System.out.println("The Execution Time in milli seconds for isCyclic: " + (end_cycle - start_cycle));
                    System.out.println();
                    System.out.println("Total Execution Time in milli seconds: " + (BFS_exec_time + DFS_exec_time + (end_cycle - start_cycle)));

                }
                menu();
                break;
            }
            case 5:{
                break;
            }
            case 0:{
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Wrong entry");
                menu();
                break;
            }
        }
    }
}
