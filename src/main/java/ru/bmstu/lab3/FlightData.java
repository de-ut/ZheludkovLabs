package ru.bmstu.lab3;

import java.io.Serializable;

public class FlightData implements Serializable {
    private float maxDelay;
    private int total, late, canceled;
    public FlightData() {};

    public FlightData(String delay){
        total = 1;
        if (delay.isEmpty()){
            canceled = 1;
            maxDelay = 0.f;
            late = 0;
        } else{
            canceled = 0;
            maxDelay = Float.parseFloat(delay);
            late = maxDelay == 0.f ? 0 : 1;
        }
    }

    public FlightData(float maxDelay, int total, int late, int canceled){
        this.maxDelay = maxDelay;
        this.total = total;
        this.late = late;
        this.canceled = canceled;
    }

    public FlightData union(FlightData other){
        return new FlightData(Math.max(maxDelay, other.maxDelay), total + other.total, late + other.late, canceled + other.canceled);
    }

    @Override
    public String toString() {
        return String.format("late: %.2f%%; maxDelay: %.2f; canceled: %.2f%%;", getPercents(late), maxDelay, getPercents(canceled));
    }

    private float getPercents(int count){
        return 100.f*count/total;
    }
}
