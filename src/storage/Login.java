package storage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Guardeec on 27.02.16.
 */
public class Login {
    private static Login ourInstance = new Login();

    public static Login getInstance() {
        return ourInstance;
    }

    private String password = "L33TsupaH4X0R";
    private String name = "GodSaveTheQuinn";

    private Login() {
    }

    public Boolean check(String name, String password){
        return this.password.compareTo(password)==0 && this.name.compareTo(name)==0;
    }

    public Boolean check(HttpServletRequest request){
        Map params = request.getParameterMap();
        Boolean check;
        try {
            String name = ((String[]) params.get("name"))[0];
            String pass = ((String[]) params.get("pass"))[0];
            check = Login.getInstance().check(name, pass);
        }catch (NullPointerException ex){
            check = false;
        }
        return check;
    }
}
