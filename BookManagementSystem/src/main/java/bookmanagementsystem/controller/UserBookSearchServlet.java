package bookmanagementsystem.controller;

import bookmanagementsystem.dao.BookDAO;
import bookmanagementsystem.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userBooks/search")
public class UserBookSearchServlet extends HttpServlet {
    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String query = request.getParameter("query");
        if (query != null && !query.trim().isEmpty()) {
            try {
                List<Book> searchResults = bookDAO.searchBooks(query);
                request.setAttribute("searchResults", searchResults);
                request.setAttribute("searchPerformed", true); // 只在搜索后设置
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/userSearch.jsp");
        dispatcher.forward(request, response);
    }

}
