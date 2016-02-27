package spoof;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 01.11.15.
 */
public class Macs {
    private static Macs ourInstance = new Macs();
    public static List<String> macs = new LinkedList<String>();

    public static Macs getInstance() {
        return ourInstance;
    }

    private Macs() {
    }


}
