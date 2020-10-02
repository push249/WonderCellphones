package wmc.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wmc.domain.Product;
import wmc.services.MobileService;

public class DisplayProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idUser = 0;
        if (request.getParameter("idUser") != null) {
            idUser = Integer.parseInt(request.getParameter("idUser"));
        }
        if (request.getAttribute("idUser") != null) {
            idUser = (int) request.getAttribute("idUser");
        }
        ArrayList<Product> productList = MobileService.listProduct();
        ServletContext context = getServletContext();
        request.setAttribute("productList", productList);
        request.setAttribute("idUser", idUser);
        RequestDispatcher dispatch = context.getRequestDispatcher("/WEB-INF/views/products.jsp");
        dispatch.forward(request, response);
    }
}
