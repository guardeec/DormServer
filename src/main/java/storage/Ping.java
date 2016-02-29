package storage;

import storage.pojo.PojoPing;

import java.util.Date;

/**
 * Created by Guardeec on 29.02.16.
 */
public class Ping {
    private static Ping ourInstance = new Ping();

    public static Ping getInstance() {
        return ourInstance;
    }

    private int number=0;
    private PojoPing[] pojoPings = new PojoPing[100];

    private Ping() {
    }

    public PojoPing[] getPojoPings() {
        return pojoPings;
    }

    public void addNewPing(Date date, double ping, boolean type){
        PojoPing pojoPing = new PojoPing(date, ping, type);
        pojoPings[number] = pojoPing;
        number=(number+1)%100;
    }

    public Double median(){
        double medaiana = 0;
        int number = 0;
        for (int i=0; i<pojoPings.length && pojoPings[i]!=null; i++){
            if (pojoPings[i].getType()){
                medaiana+=pojoPings[i].getPing();
                number++;
            }
        }
        return number==0 ? 0 : medaiana/number;
    }

    public Integer[] statistics(){
        Integer[] statistics = {0,0};
        for (int i=0; i<pojoPings.length && pojoPings[i]!=null; i++){

            if (pojoPings[i].getType()){
                statistics[0]++;
            }else {
                statistics[1]++;
            }
        }
        return statistics;
    }

    public double statisticsInAverage(){
        Integer[] statistics = statistics();
        if (statistics[0]==0 && statistics[1]==0){
            return 0;
        }else {
            System.out.println(statistics[0]);
            System.out.println((statistics[0] + statistics[1]));
            return (double) (statistics[0])/ (double) (statistics[0]+statistics[1]);
        }
    }



}
