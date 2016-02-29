package services;

import com.google.gson.Gson;
import storage.Login;
import storage.Ping;
import storage.pojo.PojoPing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 29.02.16.
 */
@WebServlet(name = "GetWholePingStatistics")
public class GetWholePingStatistics extends HttpServlet {
    Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean check = Login.getInstance().check(request);
        String message;

        if (check){
            message = gson.toJson(Ping.getInstance().getPojoPings(), PojoPing[].class);
        }else {
            message = "Because Fuck You";
        }

        PrintWriter out = response.getWriter();
        out.println(message);
    }
}
