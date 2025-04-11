/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.User;

/**
 *
 * @author VuxD4t
 */
@WebServlet(name = "AddAccount", urlPatterns = {"/addAccount"})
public class AddAccount extends HttpServlet {

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
            out.println("<title>Servlet AddAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAccount at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/Views/ViewsManager/addAcc.jsp").forward(request, response);
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
        String username = request.getParameter("signup-user");
        String password = request.getParameter("signup-pass");
        String repeatPassword = request.getParameter("repeat-pass");
        String email = request.getParameter("email");
        String roleIDString = request.getParameter("role"); 
        UserDao userDao = new UserDao();

        if (userDao.getUserByUsername(username) != null) {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!!!");
            request.getRequestDispatcher("/Views/ViewsManager/addAcc.jsp").forward(request, response);
            return;
        }
        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp!!!");
            request.getRequestDispatcher("/Views/ViewsManager/addAcc.jsp").forward(request, response);
            return;
        }

        if (userDao.getUserByEmail(email) != null) {
            request.setAttribute("error", "Email đã được sử dụng!!!");
            request.getRequestDispatcher("/Views/ViewsManager/addAcc.jsp").forward(request, response);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(roleIDString);
              User newUser = new User(0, username, password, email, new Role(id, null));
            userDao.insert(newUser);
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
