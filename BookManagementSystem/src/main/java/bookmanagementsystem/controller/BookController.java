package bookmanagementsystem.controller;

import bookmanagementsystem.dao.BookDAO;
import bookmanagementsystem.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.util.ArrayList;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/books/*")
public class BookController extends HttpServlet {
    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        try {
            if (action != null) {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/delete":
                        deleteBook(request, response);
                        break;
                    case "/search":
                        searchBooks(request, response);
                        break;
                    default:
                        listBooks(request, response);
                        break;
                }
            } else {
                listBooks(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        try {
            switch (action) {
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<Book> listBooks = bookDAO.getAllBooks();
        request.setAttribute("books", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listBooks.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/BookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn"); 
        // Retrieve other fieldsisbn
        Book newBook = new Book(0, title, author,isbn);
        bookDAO.addBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ClassNotFoundException {
        // 获取表单数据
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String borrowStatus = request.getParameter("borrowStatus");
        String location = request.getParameter("location"); // 获取索书号

        // 创建并更新书籍对象
        Book book = new Book(id, title, author, isbn);
        book.setBorrowStatus(borrowStatus);
        book.setLocation(location); // 设置索书号
        bookDAO.updateBook(book);

        response.sendRedirect("list");
    }
    

    private void searchBooks(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setAttribute("searchPerformed", true); // 在执行搜索后设置
        String query = request.getParameter("query");
        List<Book> result = new ArrayList<>();
        try {
            result = bookDAO.searchBooks(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // 更合适的错误处理
        }
        request.setAttribute("searchResults", result);
        request.setAttribute("searchQuery", query);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listBooks.jsp");
        dispatcher.forward(request, response);
    }







    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("list");
    }
}