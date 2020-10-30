package ru.bmstu.lab2;

public class AirportApp {
    public static void main(String[] args) throws Exception{
        if(args.length != 3){
            System.err.println("Use: AirportApp <flights path> <airport path> <output path>");
            System.exit(-1);
        }
        Job job = Job.getInstance();
    }
}
