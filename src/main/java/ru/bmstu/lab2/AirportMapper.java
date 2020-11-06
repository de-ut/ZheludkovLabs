package ru.bmstu.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get() == 0) return;
        String[] fields = Utilities.separate(value, 2);
        context.write(new AirportID(fields[AIRPORT_ID], AirportID.DATA_AIRPORT_INDICATOR), new Text(fields[AIRPORT_NAME]));
    }
}
