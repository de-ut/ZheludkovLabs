package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportID implements WritableComparable<AirportID> {
    private Integer id;
    private boolean indicator;

    AirportID() {}
    AirportID(int id, boolean indicator){
        this.id = id;
        this.indicator = indicator;
    }

    @Override
    public int compareTo(AirportID o) {
        int result = id.compareTo(o.id);
        if(result == 0) return 
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeBoolean(indicator);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        indicator = dataInput.readBoolean();
    }
}
