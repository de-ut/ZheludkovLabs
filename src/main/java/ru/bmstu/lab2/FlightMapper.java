package ru.bmstu.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    private static final String QUOTE_PATTERN = "\"";
    private static final String COMMA_SEPARATOR = ",";
    private static final int AIRPORT_ID = 14;
    private static final int ARRIVAL_DELAY = 18;
    private static final String FLOAT_ZERO_STRING = "0.00";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get() == 0) return;
        String[] fields = Utilities.separate(value, 0);
        String delay = fields[ARRIVAL_DELAY];
        if(!delay.isEmpty() && !delay.equals(FLOAT_ZERO_STRING))
            context.write(new AirportID(fields[AIRPORT_ID], AirportID.DATA_FLIGHT_INDICATOR), new Text(delay));
    }
}
