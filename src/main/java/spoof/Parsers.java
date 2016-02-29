package spoof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Guardeec on 27.02.16.
 */
public class Parsers {
    public static Map<String, String> parseInMap(File input){
        List<String> pasingData = new ArrayList<String>();
        try {
            Scanner in = new Scanner(input);
            while (in.hasNext()){
                String message = in.nextLine();
                if(message.contains("Nmap scan report for")){
                    message = message.substring(21);
                    pasingData.add(message);
                }
                if(message.contains("MAC Address:")){
                    message = message.substring(13, 31);
                    pasingData.add(message);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i>pasingData.size(); i++){
            System.out.println(pasingData.get(i));
        }

        Map hosts = new HashMap<String, String>();
        for(int i=0; i<pasingData.size()-1; i+=2){
            hosts.put(pasingData.get(i), pasingData.get(i + 1));
        }
        return hosts;
    }

    public static Map<String, String> parseNamesInMap(File input){
        List<String> pasingData = new ArrayList<String>();
        try {
            Scanner in = new Scanner(input);
            while (in.hasNext()){
                String message = in.nextLine();
                if(message.contains("Nmap scan report for")){
                    message = message.substring(21);
                    pasingData.add(message);
                }
                if(message.contains("MAC Address:")){
                    message = message.substring(31);
                    pasingData.add(message);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i>pasingData.size(); i++){
            System.out.println(pasingData.get(i));
        }

        Map hosts = new HashMap<String, String>();
        for(int i=0; i<pasingData.size()-1; i+=2){
            hosts.put(pasingData.get(i), pasingData.get(i + 1));
        }
        return hosts;
    }

    public static List<String> parseInList(File input){
        List<String> hosts = new ArrayList<String>();
        try {
            Scanner in = new Scanner(input);
            while (in.hasNext()){
                String message = in.nextLine();
                if(message.contains("Nmap scan report for")){
                    message = message.substring(21);
                    hosts.add(message);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hosts;
    }
}
