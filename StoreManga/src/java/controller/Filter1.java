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
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "Filter1", urlPatterns = {"/filter1"})
public class Filter1 extends HttpServlet {

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
            out.println("<title>Servlet Filter1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Filter1 at " + request.getContextPath() + "</h1>");
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
       String sortOption = request.getParameter("sortOption");
        DaoCategory dc = new DaoCategory();
        List<Product> list = dc.getAllProducts();
        List<Category> categoryList = dc.getAll();
        if (sortOption != null && sortOption.equals("ascending")) {
            dc.sortAscending(list);
        } else {
            dc.sortDescending(list);
        }

        // Phân trang
        int numPerPage = 8; // Số sản phẩm trên mỗi trang
        int numProducts = list.size(); // Số lượng sản phẩm sau khi lọc
        
        // Tính số trang
        int numPages = (int) Math.ceil((double) numProducts / numPerPage);
        
        // Lấy số trang hiện tại từ request, mặc định là 1
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        // Tính vị trí bắt đầu và kết thúc của trang hiện tại
        int start = (currentPage - 1) * numPerPage;
        int end = Math.min(start + numPerPage, numProducts);

        // Lấy danh sách sản phẩm cho trang hiện tại
        List<Product> productsOnPage = list.subList(start, end);


         request.setAttribute("data1", categoryList);
        request.setAttribute("data2", productsOnPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numPages", numPages);

        request.getRequestDispatcher("Views/home.jsp").forward(request, response);
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
