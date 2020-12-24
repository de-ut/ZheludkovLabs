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

    public FlightData(float maxDelay, int total, int late){
        this.maxDelay = maxDelay;
        this.total = total;
        this.late = late;
    }

    public FlightData union(FlightData other){
        return new FlightData(Math.max(maxDelay, other.maxDelay), total + other.total, late + other.late);
    }

    @Override
    public String toString() {
        return String.format("maxDelay: %f; arriveRatio: %.2f%;", maxDelay, getPercents());
    }

    private float getPercents(){
        return 100.f*late/total;
    }
}
