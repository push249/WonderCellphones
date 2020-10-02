package wmc.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import wmc.services.MobileService;

public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String userName = request.getParameter("username");
        String eMail = request.getParameter("emailid");
        String password = request.getParameter("password1");
        String address = request.getParameter("address");
        String contactNo = request.getParameter("contactno");
        int update = MobileService.addUser(firstName, lastName, userName, eMail, password, address, contactNo);
        if (update == 1) {
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/signin.jsp");
            dispatch.forward(request, response);
        }
        if (update == 0) {
            String message = "User Already Exists";
            response.sendRedirect("signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        }

    }
}
