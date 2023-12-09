package bookmanagementsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/userLogin")
public class UserLoginController extends HttpServlet {
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "user123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userType", "user");
            response.sendRedirect(request.getContextPath() + "/userBooks/search");
        } else {
            request.setAttribute("loginError", "Invalid username or password");
            request.getRequestDispatcher("/views/userLogin.jsp").forward(request, response);
        }
    }
}
