package services;

import storage.Login;
import storage.Ping;

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
@WebServlet(name = "GetPingStatistic")
public class GetPingStatistic extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean check = Login.getInstance().check(request);
        String message;

        if (check){
            message =   "median="+ Double.toString(Ping.getInstance().median())
                        +"&average="+Double.toString(Ping.getInstance().statisticsInAverage())
            ;
        }else {
            message = "Because Fuck You";
        }

        PrintWriter out = response.getWriter();
        out.println(message);
    }
}
