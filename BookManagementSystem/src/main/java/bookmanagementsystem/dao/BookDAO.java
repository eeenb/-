package bookmanagementsystem.dao;

import bookmanagementsystem.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/new_database_book";
    private String jdbcUsername = "root";
    private String jdbcPassword = "lijiaye3669";
    private Connection jdbcConnection;

    // 在每个方法中调用此方法以连接到数据库
    protected void connect() throws SQLException, ClassNotFoundException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("BookID"));
            book.setTitle(resultSet.getString("Title"));
            book.setAuthor(resultSet.getString("Author"));
            book.setBorrowStatus(resultSet.getString("borrow_status"));
            book.setLocation(resultSet.getString("location"));
            // ... 设置其他属性
            books.add(book);
        }


        resultSet.close();
        statement.close();
        disconnect();

        return books;
    }

    public void addBook(Book book) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO books (Title, Author, ISBN, borrow_status, location) VALUES (?, ?, ?, ?, ?)";
        connect();
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getBorrowStatus()); // "空闲" 或 "忙碌"
            statement.setString(5, book.getLocation()); // 索书号
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void updateBook(Book book) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE books SET Title = ?, Author = ?, ISBN = ?, borrow_status = ?, location = ? WHERE BookID = ?";
        connect();
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getBorrowStatus()); // "空闲" 或 "忙碌"
            statement.setString(5, book.getLocation()); // 索书号
            statement.setInt(6, book.getId());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }



    public void deleteBook(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM books WHERE BookID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        statement.executeUpdate();
        statement.close();
        disconnect();
    }
    public Book getBook(int id) throws SQLException, ClassNotFoundException {
        Book book = null;
        String sql = "SELECT * FROM books WHERE BookID = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("Title");
            String author = resultSet.getString("Author");
            // ... 获取其他属性
            book = new Book(id, title, author);
            // ... 设置其他属性
        }

        resultSet.close();
        statement.close();
        disconnect();

        return book;
    }
    public List<Book> searchBooks(String query) throws SQLException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return books; // 返回空列表，不执行搜索
        }
        String sql = "SELECT * FROM books WHERE Title LIKE ? OR Author LIKE ? OR location LIKE ?";
        
        connect();
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            statement.setString(2, "%" + query + "%");
            statement.setString(3, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("BookID"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setBorrowStatus(resultSet.getString("borrow_status")); // 根据您的数据库实际字段调整
                book.setLocation(resultSet.getString("Location")); // 根据您的数据库实际字段调整
                // ... 根据需要设置其他属性 ...
                books.add(book);
            }
        }catch (SQLException e) {
            e.printStackTrace(); // 打印异常信息
        }
        finally {
            disconnect();
        }
        return books;
    }



    

    // ... 其他CRUD方法
}
