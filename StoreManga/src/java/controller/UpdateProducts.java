/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CountDao;
import dal.DaoCategory;
import dal.OrderDao;
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
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "UpdateProducts", urlPatterns = {"/updateProducts"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 11 // 11MB
)
public class UpdateProducts extends HttpServlet {

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
            out.println("<title>Servlet UpdateProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProducts at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(productId);
            DaoCategory cdb = new DaoCategory();
            Product p = cdb.getProduct(id);

            request.setAttribute("dataProduct", p);
            List<Category> list = cdb.getAll();
            request.setAttribute("categoryID", list);
            request.getRequestDispatcher("/Views/ViewsManager/updateProducts.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

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
        String productId = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String oldimg = request.getParameter("oldimg");
        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));

        Part part = request.getPart("image");
        String fileName = extractFileName(part);

        String savePath = "D:\\Documents\\Kì 4\\PRJ301\\Project\\Store_Manga\\StoreManga\\web\\Images\\newproducts";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println(oldimg);
        try {

            String imageLink = "";
            if (fileName.isEmpty()) {

                imageLink = oldimg;
            } else {

                part.write(savePath + File.separator + fileName);
                imageLink = "Images/newproducts/" + fileName;
            }
            int id = Integer.parseInt(productId);
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));

            int categoryId = Integer.parseInt(request.getParameter("typename"));

            OrderDao dao = new OrderDao();
            DaoCategory cdb = new DaoCategory();
            CountDao co = new CountDao();
            Product updatedP = new Product(id, name, quantity, price, releaseDate,
                    description, imageLink, new Category(categoryId, null, null));

            int quantityProduct = co.getQuantityByProductId(id);
            if (quantityProduct == 0) {
                cdb.updateProduct(updatedP);
                response.sendRedirect("managerProduct");
            }
            
            
            if (dao.checkProductExist(id)) {
                // hiện thị lại value sau khi bị đẩy msg error

                Product p = cdb.getProduct(id);
                request.setAttribute("dataProduct", p);
                List<Category> list = cdb.getAll();
                request.setAttribute("categoryID", list);

                request.setAttribute("error", "Sản phẩm: " + name + " đang có giao dịch không thể cập nhật.");
                request.getRequestDispatcher("/Views/ViewsManager/updateProducts.jsp").forward(request, response);
                return;
            }

            System.out.println(updatedP.toString());
            cdb.updateProduct(updatedP);

            response.sendRedirect("managerProduct");
        } catch (NumberFormatException e) {
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
