package storage;

/**
 * Created by Guardeec on 27.02.16.
 */
public class Status {
    private static Status ourInstance = new Status();

    public static Status getInstance() {
        return ourInstance;
    }

    private int status=0;
    private String mac = "";

    private Status() {
    }

    public void setDisable(){
        this.status = 0;
    }

    public void setEnable(){
        this.status = 2;
    }

    public void inProgress(){
        this.status = 1;
    }

    public String getStatus(){
        return status==2 ? "enable" : status==1 ? "inProgress" : "disable";
    }

    public void setMac(String mac){
        this.mac = mac;
    }

    public String getMac(){
        return this.mac;
    }
}
