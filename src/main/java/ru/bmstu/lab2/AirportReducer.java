package ru.bmstu.lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AirportReducer extends Reducer<AirportID, Text, Text, Text> {
    @Override
    protected void reduce(AirportID key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        

        int count = 0;
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        float avg = 0;

    }
}
