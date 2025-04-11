/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDao;
import dal.OrderDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccount at " + request.getContextPath() + "</h1>");
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
        String userId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(userId);
            UserDao ud = new UserDao();
            User u = ud.getUser(id);

            request.setAttribute("dataUser", u);
            List<User> list = ud.getAll();
            request.setAttribute("role", list);
            request.getRequestDispatcher("/Views/ViewsManager/updateAcc.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("signup-pass");
        String repeatPassword = request.getParameter("repeat-pass");
        String email = request.getParameter("email");
        String roleID = request.getParameter("role");
        String userId_raw = request.getParameter("userId");
        UserDao userDao = new UserDao();

        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp!!!");
            request.getRequestDispatcher("/Views/ViewsManager/updateAcc.jsp").forward(request, response);
            return;
        }

        int id;
        int userId;
        try {

            userId = Integer.parseInt(userId_raw);
            id = Integer.parseInt(roleID);
            OrderDao dao = new OrderDao();
            CartDao cd = new CartDao();
            
            if (dao.checkUserExist(username) ||cd.checkUsernameExistsInCart(username)) {
                // hiện thị lại value sau khi bị đẩy msg error
                UserDao ud = new UserDao();
                User u = ud.getUser(userId);
                request.setAttribute("dataUser", u);
                List<User> list = ud.getAll();
                request.setAttribute("role", list);
                request.setAttribute("error", "Tài khoản: " + username + " hiện đang có giao dịch không thể xóa.");
                request.getRequestDispatcher("/Views/ViewsManager/updateAcc.jsp").forward(request, response);
                return;
            }

//            System.out.println(roleID);
//            System.out.println(password);
            User newUser = new User(userId, null, password, email, new Role(id, null));
//            System.out.println(newUser.toString());
            userDao.updateUser(newUser);
            response.sendRedirect("account");
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
