package services;

import storage.Login;
import storage.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 27.02.16.
 */
@WebServlet(name = "CheckStatus")
public class CheckStatus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean check = Login.getInstance().check(request);
        String message;

        if (check){
            message = Status.getInstance().getStatus()+"\n"+Status.getInstance().getMac();
        }else {
            message = "Because Fuck You";
        }
        PrintWriter out = response.getWriter();
        out.println(message);
    }
}
