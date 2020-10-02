package wmc.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import wmc.services.MobileService;

public class AddCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductId = Integer.parseInt(request.getParameter("ProductId"));
        int idUser = 0;
        if (request.getParameter("idUser") != null) {
            idUser = Integer.parseInt(request.getParameter("idUser"));
        }
        if (request.getAttribute("idUser") != null) {
            idUser = (int) request.getAttribute("idUser");
        }
        int update = MobileService.addOrder(idUser, ProductId);
        if (update == 1) {
            ServletContext context = getServletContext();
            request.setAttribute("idUser", idUser);
            RequestDispatcher dispatch = context.getRequestDispatcher("/DisplayProductServlet");
            dispatch.forward(request, response);
        }
        if (update == 0) {
            String message = "Order Already Exists";
            response.sendRedirect("products.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        }

    }
}
