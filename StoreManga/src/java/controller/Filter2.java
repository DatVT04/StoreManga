/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DaoCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "Filter2", urlPatterns = {"/filter2"})
public class Filter2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Filter2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Filter2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String priceRange = request.getParameter("priceRange");
      
        double minPrice = 0;
        double maxPrice = 0;
        switch (priceRange) {
            case "1":
                minPrice = 10000;
                maxPrice = 30000;
                break;
            case "2":
                minPrice = 30000;
                maxPrice = 50000;
                break;
            case "3":
                minPrice = 50000;
                maxPrice = 100000;
                break;
            case "4":
                minPrice = 100000;
                maxPrice = 300000;
                break;
            default:

                return;
        }

        DaoCategory dc = new DaoCategory();
        List<Product> productList = dc.selectProductsByPriceRange(minPrice, maxPrice);
        List<Category> categoryList = dc.getAll();
        int count = dc.countProductsByPriceRange(minPrice, maxPrice);
        request.setAttribute("data1", categoryList);
        request.setAttribute("count", count);
        request.setAttribute("data2", productList);
        request.getRequestDispatcher("Views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
