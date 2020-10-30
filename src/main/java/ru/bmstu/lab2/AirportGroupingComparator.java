package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    AirportGroupingComparator(){
        super(AirportID.class)
    }
    @Override
    public int compare(Object a, Object b) {
        return super.compare(a, b);
    }
}
