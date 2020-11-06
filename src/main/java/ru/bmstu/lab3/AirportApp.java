package ru.bmstu.lab3;

import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class AirportApp {
    public static void main(String[] args) {
        if(args.length != 3){
            System.err.println("Use: AirportApp <flight path> <airport path> <output path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("AirportApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flightFile = sparkContext.textFile(args[0]);
        JavaRDD<String> airportFile = sparkContext.textFile(args[1]);
        JavaPairRDD<Text, Text> airports = airportFile.mapToPair(s -> {
            String[] fields = Utilities.separate(s, 2);
            return new Tuple2<>(fields[0], fields[1])
        });


    }
}
