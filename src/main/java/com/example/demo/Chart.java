package com.example.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Chart extends Application {

    String[] data;
    String nodes;
    String time;
    String [] data_bfs;
    String [] data_dfs;
    public static int Nodes(String fileLink) throws FileNotFoundException {
        File f = new File(fileLink);
        ArrayList arr=new ArrayList();
        HashMap<String, Integer> listOfNodes = new HashMap<String, Integer>();
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
            listOfNodes.put((String) itr.next(), i);
        }
        System.out.println("The number of nodes: "+listOfNodes.size());
        return listOfNodes.size();
    }
    public void read(String fileLink)
    {
        try {
            FileReader fileReader = new FileReader(fileLink);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i=0;(line = bufferedReader.readLine())!=null;i++)
            {
                data = line.split(",");
                nodes = data[0];
                time = data[1];

            }
            bufferedReader.close();
        }catch (Exception e)
        {
            System.out.println("ERROR: File not readed");
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) {

        try {
            FileReader dfs = new FileReader("DFS_Time.txt");
            FileReader bfs = new FileReader("BFS_Time.txt");

            BufferedReader bufferedReader_dfs = new BufferedReader(dfs);
            BufferedReader bufferedReader_bfs = new BufferedReader(bfs);

            String line_bfs, line_dfs;
            for (int i=0;(((line_dfs = bufferedReader_bfs.readLine())!=null)&&((line_bfs = bufferedReader_dfs.readLine())!=null));i++)
            {
                data_bfs = line_bfs.split(",");
                data_dfs = line_dfs.split(",");

                    nodes = data_dfs[0];
                    time = data_dfs[1];
            }
            bufferedReader_bfs.close();
            bufferedReader_dfs.close();
        }catch (Exception e)
        {
            System.out.println("ERROR: File not readed");
            e.printStackTrace();
        }

        System.out.println(nodes+" "+time);


        NumberAxis xAxis = new NumberAxis(0, (Double.valueOf(nodes)+100), 100);
        xAxis.setLabel("Number of Nodes");

        NumberAxis yAxis = new NumberAxis   (0, (Double.valueOf(time)*10), 500);
        yAxis.setLabel("Running Time (Milli Seconds)");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Chart for DFS");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Chart for BFS");



        try {
            FileReader fileReader = new FileReader("DFS_Time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            series1.getData().add(new XYChart.Data(0, 0));
            for (int i=0;(line = bufferedReader.readLine())!=null;i++)
            {
                data = line.split(",");
                nodes = data[0];
                time = data[1];
                series1.getData().add(new XYChart.Data(Integer.parseInt(nodes), Integer.parseInt(time)));
            }
            bufferedReader.close();
        }catch (Exception e)
        {
            System.out.println("ERROR: File not readed");
            e.printStackTrace();
        }


        try {
            FileReader fileReader = new FileReader("BFS_Time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            series2.getData().add(new XYChart.Data(0, 0));
            for (int i=0;(line = bufferedReader.readLine())!=null;i++)
            {
                data = line.split(",");
                nodes = data[0];
                time = data[1];
                series2.getData().add(new XYChart.Data(Integer.parseInt(nodes), Integer.parseInt(time)));
            }
            bufferedReader.close();
        }catch (Exception e)
        {
            System.out.println("ERROR: File not readed");
            e.printStackTrace();
        }


        linechart.getData().add(series1);

        linechart.getData().add(series2);

        Group root = new Group(linechart);

        Scene scene = new Scene(root, 550, 400);

        stage.setTitle("Line Chart ");

        stage.setScene(scene);

        stage.show();
    }
    public static void main(String args[]) throws IOException {
        String fileLink = "Wiki-Vote.txt";
        File file1 = new File("BFS_Time.txt");
        File file2 = new File("DFS_Time.txt");
        int nodes = Nodes(fileLink);
        if((file1.length()==0)||(file2.length()==0)){
            CountNodes countNodes = new CountNodes();
            nodes = countNodes.countNumberOfNodes(fileLink);
            System.out.println("Enter the starting node: ");
            Scanner sc= new Scanner(System.in);
            int starting_node = sc.nextInt();
            FileHandling fileHandling = new FileHandling(fileLink, nodes);
            fileHandling.calculate_BFS(starting_node);
            fileHandling.calculate_DFS(starting_node);
        }
        FileHandling fileHandling = new FileHandling(fileLink, nodes);
        fileHandling.read();
        launch(args);
        while (true)
        {
            fileHandling.read();
            launch(args);
        }
    }
}
