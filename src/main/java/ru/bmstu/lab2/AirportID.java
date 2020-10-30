package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportID implements WritableComparable<AirportID> {
    private int id;
    private boolean indicator;

    AirportID() {}
    AirportID(int id, boolean indicator){
        this.id = id;
        this.indicator = indicator;
    }

    @Override
    public int compareTo(AirportID o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
