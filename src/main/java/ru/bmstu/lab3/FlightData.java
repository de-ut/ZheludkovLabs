package ru.bmstu.lab3;

import java.io.Serializable;

public class FlightData implements Serializable {
    private float maxDelay;
    private int total, late;
    public FlightData() {};

    public FlightData(float maxDelay, boolean isLate){
        this.maxDelay = maxDelay;
        total = 1;
        late = isLate ? 1 : 0;
    }
}
