package ru.bmstu.lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, Text>{
}
