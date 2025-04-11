/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DaoCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Date;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "addProducts", urlPatterns = {"/addProducts"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 11 // 11MB
)
public class AddProducts extends HttpServlet {

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
            out.println("<title>Servlet addProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProducts at " + request.getContextPath() + "</h1>");
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

        DaoCategory d = new DaoCategory();
        List<Category> list = d.getAll();
        request.setAttribute("product1", list);
        request.getRequestDispatcher("/Views/ViewsManager/addProducts.jsp").forward(request, response);
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

        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
        String description = request.getParameter("description");

        Part part = request.getPart("image");
        String fileName = extractFileName(part);

        String savePath = "D:\\Documents\\Kì 4\\PRJ301\\Project\\Store_Manga\\StoreManga\\web\\Images\\newproducts";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {

            fileSaveDir.mkdirs();
        }
        part.write(savePath + File.separator + fileName);

        int categoryId = Integer.parseInt(request.getParameter("typename"));
        try {
            DaoCategory dca = new DaoCategory();
            Product p = dca.checkProductExists(name, description);
            String imageLink = "Images/newproducts/" + fileName;
            if (p == null) {

                Product newP = new Product(0, name, quantity, price, releaseDate, description, imageLink, new Category(categoryId, null, null));
                dca.insertProduct(newP);
                response.sendRedirect("managerProduct");
            } else {
                request.setAttribute("error", "Sản phẩm " + name + "hiện đã tồn tại! Vui lòng nhập lại.");
                request.getRequestDispatcher("/Views/ViewsManager/addProducts.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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

    private String extractFileName(Part part) {
        // Lấy header của part chứa file
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // Trích xuất tên file từ header
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
