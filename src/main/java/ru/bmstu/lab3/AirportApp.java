package ru.bmstu.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportApp {
    public static void main(String[] args) {
        if(args.length != 3){
            System.err.println("Use: AirportApp <flight path> <airport path> <output path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("AirportApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
    }
}
