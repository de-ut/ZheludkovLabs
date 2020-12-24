package ru.bmstu.lab3;

import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportSparkApp {
    public static final int AIRPORT_CODE = 0;
    public static final int AIRPORT_DESCRIPTION = 1;

    public static final int ORIGIN_AIRPORT_ID = 11;
    public static final int DEST_AIRPORT_ID = 14;
    public static final int DELAY = 18;

    public static final int FLIGHT_SEPARATION_LIMIT = 0;
    public static final int AIRPORT_SEPARATION_LIMIT = 2;

    public static void main(String[] args) {
        if(args.length != 3){
            System.err.println("Use: AirportApp <flight path> <airport path> <output path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("AirportSparkApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flightFile = sparkContext.textFile(args[0]);
        JavaRDD<String> airportFile = sparkContext.textFile(args[1]);

        String flightToSkip = flightFile.first();
        String airportToSkip = airportFile.first();

        JavaPairRDD<Tuple2<String, String>, FlightData> flights = flightFile
                .filter(str -> !str.equals(flightToSkip))
                .mapToPair(s -> {
                    String[] fields = Utilities.separate(s, FLIGHT_SEPARATION_LIMIT);
                    return new Tuple2<Tuple2<String, String>, FlightData>(
                    new Tuple2<String, String>(fields[ORIGIN_AIRPORT_ID], fields[DEST_AIRPORT_ID]), new FlightData(fields[DELAY]));
                })
                .reduceByKey(FlightData::union);

        JavaPairRDD<String, String> airports = airportFile
                .filter(str -> !str.equals(airportToSkip))
                .mapToPair(s -> {
                    String[] fields = Utilities.separate(s, AIRPORT_SEPARATION_LIMIT);
                    return new Tuple2<>(fields[AIRPORT_CODE], fields[AIRPORT_DESCRIPTION]);
                });

        final Broadcast<Map<String,String>> airportsBroadcasted = sparkContext.broadcast(airports.collectAsMap());

        JavaRDD<String> result = flights.map(s -> {
            String formattedStr = "FROM " + airportsBroadcasted.value().get(s._1._1) + " TO " + airportsBroadcasted.value().get(s._1._2) + " - " + s._2.toString();
            return formattedStr;
        });

        result.saveAsTextFile(args[2]);
    }
}
