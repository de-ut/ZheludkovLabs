package ru.bmstu.lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AirportApp {
    public static void main(String[] args) throws Exception{
        if(args.length != 3){
            System.err.println("Use: AirportApp <flight path> <airport path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(AirportApp.class);
        job.setJobName("AirportApp");

        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, FlightMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, AirportMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setMapOutputKeyClass(AirportID.class);
        job.setMapOutputValueClass(Text.class);

        job.setPartitionerClass(AirportPartitioner.class);
        job.setGroupingComparatorClass(AirportGroupingComparator.class);
        job.setReducerClass(AirportReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(2);
        System.exit(job.waitForCompletion());
    }
}
