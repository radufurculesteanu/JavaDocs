package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Radu.Furculesteanu on 7/19/2017.
 */
public class HelloWorldServletForward extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.write("Hello, <b>" + request.getParameter("user") + "</b> from the forward servlet!"
        + " " + request.getAttribute("testAttribute"));



    }

}
