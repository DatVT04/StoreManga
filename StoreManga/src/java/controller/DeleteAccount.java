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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "DeleteAccount", urlPatterns = {"/deleteAccount"})
public class DeleteAccount extends HttpServlet {

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
            out.println("<title>Servlet DeleteAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAccount at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("name");
        int id;

        try {
            UserDao us = new UserDao();
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("account");
            OrderDao dao = new OrderDao();
              CartDao cd = new CartDao();
            if (dao.checkUserExist(username) || cd.checkUsernameExistsInCart(username)) {

                request.setAttribute("error", "Tài khoản: " + username + " hiện đang có giao dịch không thể xóa.");
                request.getRequestDispatcher("/Views/ViewsManager/accountManager.jsp").forward(request, response);
                return;
            }

          
            if (user != null) {
                int roleId = user.getRole().getRoleID();
                int count = us.countAdmins(roleId);
                if (count <= 1) {
                    request.setAttribute("error", "Bạn không thể xóa!!! Tối thiểu còn 1 quản trị viên trong hệ thống.");
                    request.getRequestDispatcher("/Views/ViewsManager/accountManager.jsp").forward(request, response);
                    return;
                }
            }

            id = Integer.parseInt(userId);
            UserDao d = new UserDao();
            d.deleteUser(id);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        response.sendRedirect("account");
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
