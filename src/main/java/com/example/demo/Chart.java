package com.example.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Chart extends Application {

    String[] data;
    String nodes;
    String time;
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
        NumberAxis xAxis = new NumberAxis(0, 1100, 100);
        xAxis.setLabel("Number of Nodes");

        NumberAxis yAxis = new NumberAxis   (0, 150, 10);
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
        CountNodes countNodes = new CountNodes();
        int nodes = countNodes.countNumberOfNodes("email-Eu-core.txt");
        FileHandling fileHandling = new FileHandling("email-Eu-core.txt", nodes);
        fileHandling.read();
        launch(args);
    }
}
