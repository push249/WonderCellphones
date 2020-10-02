package wmc.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wmc.services.MobileService;

public class UpdateInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int idUser = MobileService.getUser(username, password);
        if (idUser == 0) {
            String message = "Username/Password is Invalid";
            response.sendRedirect("signin.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        } else {
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/DisplayProductServlet");
            dispatch.forward(request, response);
        }
    }
}
