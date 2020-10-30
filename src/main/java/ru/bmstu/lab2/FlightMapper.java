package ru.bmstu.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get() == 0)
        String[] fields = value.toString().replace("\"", "").split(",");
        String delay = fields[19];
        if(!delay.isEmpty() && !delay.equals("0.00"))
            context.write(new AirportID(fields[14], true), new Text(delay));
    }
}
