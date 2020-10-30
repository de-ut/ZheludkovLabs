package ru.bmstu.lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<AirportID, Text> {
    @Override
    public int getPartition(AirportID airportID, Text text, int i) {
        return (airportID.getId().hashCode() &);
    }
}
