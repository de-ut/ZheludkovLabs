package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    AirportGroupingComparator(){
        super(AirportID.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return 
    }
}
