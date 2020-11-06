package ru.bmstu.lab3;

import java.io.Serializable;

public class FlightData implements Serializable {
    private float maxDelay;
    private int total, late;
    public FlightData() {};

    public FlightData(String delay){
        total = 1;
        if (delay.isEmpty()){
            maxDelay = 0;
            late = 1;
        } else{
            maxDelay = Float.parseFloat(delay);
            late = maxDelay == 0 ? 0 : 1;
        }
    }
}
