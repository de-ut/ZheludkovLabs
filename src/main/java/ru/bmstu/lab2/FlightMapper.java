package ru.bmstu.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().replace("\"", "").split(",");
        String delay = fields[18];
        if(!delay.isEmpty() && delay.charAt(0) != '-' && !delay.equals("0.00"))
        context.write(new AirportID(fields[0], true), new Text(fields[1]));
    }
}
