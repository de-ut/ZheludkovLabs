package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportID implements WritableComparable<AirportID> {
    private String id;
    private Boolean indicator;

    AirportID() {}
    AirportID(String id, boolean indicator){
        this.id = id;
        this.indicator = indicator;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(AirportID o) {
        int result = id.compareTo(o.id);
        if(result == 0) return indicator ? -1 : 1;
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeBoolean(indicator);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readUTF();
        indicator = dataInput.readBoolean();
    }
}
