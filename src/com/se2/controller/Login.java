package com.se2.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.se2.daoImpl.LoginDaoImpl;
import com.se2.model.Account;


/**
 * @email Ramesh Fadatare
 */

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDaoImpl loginDao;

    public void init() {
        loginDao = new LoginDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("views/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        try {
            if (loginDao.validate(account)) {
            	request.getSession().setAttribute("islogin", true);
            	request.getSession().setAttribute("username", account.getUsername());
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                 session.setAttribute("username", username);
                 session.setAttribute("islogin", false);
                 response.sendRedirect("views/login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}