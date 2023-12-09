package bookmanagementsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
    // 管理员的凭据
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    // 用户的凭据（硬编码，用于测试）
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "user123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        // 检查是否是管理员登录
        if ("admin".equals(userType)) {
            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                // 登录成功，重定向到管理员书籍列表
                HttpSession session = request.getSession();
                session.setAttribute("userType", userType);
                response.sendRedirect(request.getContextPath() + "/books");
            } else {
                // 登录失败，重定向回管理员登录页面或显示错误消息
                request.setAttribute("loginError", "Invalid admin username or password");
                request.getRequestDispatcher("/views/adminLogin.jsp").forward(request, response);
            }
        } else {
            // 检查用户凭据（硬编码）
            if (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password)) {
                // 登录成功，设置会话并重定向到用户书籍页面
                HttpSession session = request.getSession();
                session.setAttribute("userType", userType);
                response.sendRedirect(request.getContextPath() + "/userBooks/search");
            } else {
                // 登录失败，重定向回用户登录页面或显示错误消息
                request.setAttribute("loginError", "Invalid user username or password");
                request.getRequestDispatcher("/views/userLogin.jsp").forward(request, response);
            }
        }
    }
}
