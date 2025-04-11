/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDao;
import dal.CountDao;
import dal.DaoCategory;
import dal.OrderDao;
import dal.ProfileDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.Cart;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Profile;
import model.User;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);

        try {
            User user = (User) session.getAttribute("account");
            String username = (String) user.getUsername();
            System.out.println(username);

            String[] arr = request.getParameterValues("checkboxName");
            if (arr == null) {
                request.setAttribute("error", "Không có sản phẩm để thanh toán");
                request.getRequestDispatcher("/Views/ViewsShoping/Checkout.jsp").forward(request, response);
                return;
            }
            int[] intArr = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                intArr[i] = Integer.parseInt(arr[i]);
            }

            List<Cart> listc = new ArrayList<>();
            CartDao cd = new CartDao();
            for (int cartId : intArr) {
                Cart cart = cd.getCartById(cartId);
                if (cart != null) {
                    listc.add(cart);
                }
            }
            if (listc.isEmpty()) {
                request.setAttribute("error", "Không có sản phẩm để thanh toán");
            } else {
                request.setAttribute("cartList", listc);

            }
            ProfileDao pdao = new ProfileDao();
            Profile p = pdao.getProfileByUsername(username);
            request.setAttribute("dataProfile", p);

            double totalAmount = 0.0;
            for (Cart cart : listc) {
                double price = cart.getProduct().getPrice();
                int quantity = cart.getQuantity();
                totalAmount += price * quantity;
            }
            request.setAttribute("total", getFormattedPrice(totalAmount));
            session.setAttribute("cartList", listc);
            request.getRequestDispatcher("/Views/ViewsShoping/Checkout.jsp").forward(request, response);
        } catch (ArithmeticException e) {
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

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);

        HttpSession session = request.getSession(false);
        try {
            User user = (User) session.getAttribute("account");
            String username = (String) user.getUsername();
            System.out.println("username: " + username);
            OrderDao dao = new OrderDao();
            Order o = new Order(0, date, new User(username), 0.0, false);

            if (session.getAttribute("username") != null || dao.isOrderTableEmpty()) {
                dao.insertOrder(o);

            }

            int id = dao.getLastOrderId();

            OrderDao od = new OrderDao();
            CountDao co = new CountDao();
            CartDao ca = new CartDao();

            List<Cart> listc = (List<Cart>) session.getAttribute("cartList");
            for (Cart cart : listc) {
                int productId = cart.getProduct().getId();
                int quantity = cart.getQuantity();
                double price = cart.getProduct().getPrice();
                int quantityProduct = co.getQuantityByProductId(productId);

                if (quantity > quantityProduct) {
                    request.setAttribute("error", "Hiện tại mặt hàng còn: " + quantityProduct + " bạn không thể mua vượt quá!");
                    od.deleteOrder(id);
                    request.getRequestDispatcher("/Views/ViewsShoping/Checkout.jsp").forward(request, response);

                } else {

                    OrderDetail orderDetail = new OrderDetail(0, new Order(id, null, null, null, false), productId, quantity, price);
                    od.saveOrderDetail(orderDetail);
                    ca.deleteCartItemByProductId(username, productId);
                }
            }

            System.out.println(name);
            
            int totalCartbyUsername = co.countProductsInCartByUsername(username);
            session.setAttribute("countCart", totalCartbyUsername);
            request.setAttribute("name", name);
            request.getRequestDispatcher("/Views/ViewsShoping/OrderStatus.jsp").forward(request, response);

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

    public String getFormattedPrice(double price) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(price);
    }

}
