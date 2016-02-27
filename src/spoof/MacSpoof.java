package spoof;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by guardeec on 01.07.15.
 */
public class MacSpoof implements Runnable {
    @Override
    public void run() {
        Map logs = Parsers.parseInMap(new File("/root/Desktop/LOGS"));
        Map names = Parsers.parseNamesInMap(new File("/root/Desktop/LOGS"));
        List<String> logsIp = Parsers.parseInList(new File("/root/Desktop/LOGS"));
        List<String> scan = Parsers.parseInList(new File("/root/Desktop/ScanResult"));
        for(int i=0; i<scan.size(); i++){
            for(int q=0; q<logsIp.size(); q++){
                if(scan.get(i).contains(logsIp.get(q))){
                    logsIp.remove(q);
                }
            }
        }
        for (int i=0; i<logsIp.size(); i++){
            System.out.println(logsIp.get(i) + " " + logs.get(logsIp.get(i))+" "+ names.get(logsIp.get(i)));
            Macs.macs.add((String) logs.get(logsIp.get(i)));
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new File("/root/Desktop/possibleHosts"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i=0; i<logsIp.size(); i++){
            out.write(logs.get(logsIp.get(i)) + " " + names.get(logsIp.get(i)) + "\n");
        }
        out.close();


        System.out.println("\n\n\n--------------------------------");
        Thread changeMac = new Thread(new ChangeMac());
        changeMac.start();
    }
}
