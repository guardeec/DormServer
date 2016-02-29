package threads;

import storage.Ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by Guardeec on 29.02.16.
 */
public class PingListner implements Runnable {
    @Override
    public void run() {
        for (;;){
            String answer = "";
            Process p;
            try{
                p = Runtime.getRuntime().exec("ping -c 1 8.8.8.8");
                p.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null){
                    answer +=line;
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            Ping ping = Ping.getInstance();
            System.out.println(answer);
            if (answer.contains("1 packets transmitted, 0 received")){
                ping.addNewPing(new Date(),0,false);
            }
            if (answer.contains("1 packets transmitted, 1 received")){
                ping.addNewPing(new Date(),Double.parseDouble(answer.substring(answer.indexOf("time=")+5, answer.indexOf(" ms"))),true);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
