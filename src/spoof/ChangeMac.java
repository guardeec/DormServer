package spoof;

import storage.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Collections;

/**
 * Created by root on 01.11.15.
 */
public class ChangeMac implements Runnable {
    public void run() {

        Boolean inet = false;
        Collections.shuffle(Macs.macs);
        FUCK: for (int i = 0; i< Macs.macs.size(); i++){
            String[] command = makeChangeMacCommand(Macs.macs.get(i));
            executeLinuxComand(command[0]);
            System.out.println("ждём пока отрубится инет");
            try {
                Thread thread = Thread.currentThread();
                thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeLinuxComand(command[1]);
            executeLinuxComand(command[2]);
            System.out.println("врубаем инет");
            try {
                Thread thread = Thread.currentThread();
                thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inet = isInetIsAvailable();
            if (inet){
                System.out.println("Поздравляю! Вы успещно пиздите инет с мака " + Macs.macs.get(i));
                Status.getInstance().setMac(Macs.macs.get(i));
                Status.getInstance().setEnable();
                break FUCK;
            }
            else {
                System.out.println("Этот мак ("+ Macs.macs.get(i)+") не подходит");
                System.out.println();
            }
        }
        if (!inet){
            System.out.println("в этой сраной общаге либо все сейчас в сети, либо никто не оплатил инет. Попробуй через минут 3-ещё раз =)");
            Status.getInstance().setDisable();
        }

    }

    public static String executeLinuxComand(String comand){
        String answer = "";
        StringBuffer output = new StringBuffer();
        Process p;
        try{
            p = Runtime.getRuntime().exec(comand);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                answer +=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static String[] makeChangeMacCommand(String mac){
        String down = "sudo ifconfig eth0 down";
        String change = "sudo ifconfig eth0 hw ether " + mac;
        String up = "sudo ifconfig eth0 up";
        String check = "sudo ifconfig";
        return new String[]{down, change, up, check};
    }

    public static Boolean isInetIsAvailable(){
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            if (address.isReachable(10000)){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
