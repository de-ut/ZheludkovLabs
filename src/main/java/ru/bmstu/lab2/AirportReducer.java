package ru.bmstu.lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportID, Text, Text, Text> {
    @Override
    protected void reduce(AirportID key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        String name = iter.next().toString();

        if(!iter.hasNext()) return;

        int count = 0;
        float min = Float.MAX_VALUE;
        float max = 0;
        float avg = 0;

        while(iter.hasNext()){
            float current = Float.parseFloat(iter.next().toString());
            min = Math.min(min, current);
            max = Math.max(max, current);
            avg = (avg * count + current) / (float)count
        }
    }
}
