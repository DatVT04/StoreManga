/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDao;
import dal.CountDao;
import dal.DaoCategory;
import dal.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.Cart;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "AddCart", urlPatterns = {"/addCart"})
public class AddCart extends HttpServlet {

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
            out.println("<title>Servlet AddCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCart at " + request.getContextPath() + "</h1>");
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
            List<Category> categoryList = cdb.getAll();
            Product p = cdb.getProduct(id);
            CountDao co = new CountDao();
            int quantityProduct = co.getQuantityByProductId(id);
            request.setAttribute("data1", categoryList);
            request.setAttribute("products", p);
            request.setAttribute("stock", quantityProduct);
            request.getRequestDispatcher("/Views/ViewsShoping/AddCart.jsp").forward(request, response);
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
        String productId_raw = request.getParameter("productId");
        String unitPrice = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");

        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);

        double price;
        int quantity;
        int productId;

        HttpSession session = request.getSession(false);
        try {
            User user = (User) session.getAttribute("account");
            String username = (String) user.getUsername();
            System.out.println("username: " + username);
            productId = Integer.parseInt(productId_raw);
            quantity = Integer.parseInt(quantity_raw);
            price = Double.parseDouble(unitPrice);
            CartDao cd = new CartDao();
            CountDao co = new CountDao();

            int quantityCart = cd.countQuantityInCart(productId, username);
            int quantityProduct = co.getQuantityByProductId(productId);
            boolean productExists = cd.isProductInCart(username, productId);
            if (quantity > quantityProduct) {
                request.setAttribute("error", "Số lượng mua của sản phẩm không vượt quá: " + quantityProduct);
                return;
            }
            Cart c = new Cart(0, new User(0, username, null, null, null),
                    new Product(productId), quantity);
            System.out.println(c.toString());

            if (productExists) {
                cd.addOrUpdateCartItem(username, productId, quantity);
            } else {
                cd.insert(c);
            }

            response.sendRedirect("home");

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

}
