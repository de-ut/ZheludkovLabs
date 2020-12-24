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

    public FlightData(FlightData one, FlightData two){
        this.maxDelay = Math.max(one.maxDelay, two.maxDelay);
        this.total = one.total + two.total;
        this.late = one.late + two.late;
    }

    @Override
    public String toString() {
        return String.format("maxDelay: %f; arriveRatio: %.2f%;", maxDelay, getPercents());
    }

    private float getPercents(){
        return 100.f*late/total;
    }
}
