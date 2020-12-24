package ru.bmstu.lab3;

import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class AirportApp {
    public static final int AIRPORT_CODE = 0;
    public static final int AIRPORT_DESCRIPTION = 1;

    public static final int ORIGIN_AIRPORT_ID = 11;
    public static final int DEST_AIRPORT_ID = 14;
    public static final int DELAY = 18

    public static final int FLIGHT_SEPARATION_LIMIT = 0;
    public static final int AIRPORT_SEPARATION_LIMIT = 2;

    public static void main(String[] args) {
        if(args.length != 3){
            System.err.println("Use: AirportApp <flight path> <airport path> <output path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("AirportApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flightFile = sparkContext.textFile(args[0]);
        JavaPairRDD<Tuple2<String, String>, FlightData> flights = flightFile.mapToPair(s -> {
            String[] fields = Utilities.separate(s, FLIGHT_SEPARATION_LIMIT);
            return new Tuple2<Tuple2<String, String>, FlightData>(
                    new Tuple2<String, String>(fields[ORIGIN_AIRPORT_ID], fields[DEST_AIRPORT_ID]), new FlightData(fields[DELAY]));
        });
        flights.reduceByKey(FlightData::union);

        JavaRDD<String> airportFile = sparkContext.textFile(args[1]);
        JavaPairRDD<String, String> airports = airportFile.mapToPair(s -> {
            String[] fields = Utilities.separate(s, AIRPORT_SEPARATION_LIMIT);
            return new Tuple2<>(fields[AIRPORT_CODE], fields[AIRPORT_DESCRIPTION]);
        });



    }
}
