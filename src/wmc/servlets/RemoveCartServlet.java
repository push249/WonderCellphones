package wmc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import wmc.services.MobileService;

public class RemoveCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductId = Integer.parseInt(request.getParameter("idUser"));
        int idUser = 0;
        if (request.getParameter("idUser") != null) {
            idUser = Integer.parseInt(request.getParameter("idUser"));
        }
        if (request.getAttribute("idUser") != null) {
            idUser = (int) request.getAttribute("idUser");
        }
        MobileService.removeOrder(idUser, ProductId);
        ServletContext context = getServletContext();
        request.setAttribute("idUser", idUser);
        RequestDispatcher dispatch = context.getRequestDispatcher("/ViewCartServlet?idUser" + idUser);
        dispatch.forward(request, response);

    }
}
