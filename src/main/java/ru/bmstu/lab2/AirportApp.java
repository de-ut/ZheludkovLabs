package ru.bmstu.lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AirportApp {
    public static void main(String[] args) throws Exception{
        if(args.length != 3){
            System.err.println("Use: AirportApp <flights path> <airport path> <output path>");
            System.exit(-1);
        }
        Job job = Job.getInstance();
        job.setJarByClass(AirportApp.class);
        job.setJobName("AirportApp");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, );
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, AirportMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
    }
}
