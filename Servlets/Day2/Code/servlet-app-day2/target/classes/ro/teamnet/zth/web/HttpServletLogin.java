package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Radu.Furculesteanu on 7/19/2017.
 */
public class HttpServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie[] cookies = req.getCookies();
        if(user.equals("admin") && password.equals("admin"))
        {
            PrintWriter pw = resp.getWriter();
            pw.write("Welcome back, " + user + "!");
            for (Cookie cookie : cookies) {
                pw.write(cookie.getName() + ":" + cookie.getValue());
            }
        }
        else
        {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("session",session);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/loginFail.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
