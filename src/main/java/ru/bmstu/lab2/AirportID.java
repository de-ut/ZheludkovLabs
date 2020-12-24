package ru.bmstu.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportID implements WritableComparable<AirportID> {
    public static final String DATA_AIRPORT_INDICATOR = "0";
    public static final String DATA_FLIGHT_INDICATOR = "1";

    private String id;
    private String indicator;

    AirportID(String id, String indicator){
        this.id = id;
        this.indicator = indicator;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(AirportID o) {
        int result = id.compareTo(o.id);
        if(result == 0) return indicator.compareTo(o.indicator);
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(indicator);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readUTF();
        indicator = dataInput.readUTF();
    }
}
