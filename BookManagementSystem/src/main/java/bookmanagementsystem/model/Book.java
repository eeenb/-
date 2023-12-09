package bookmanagementsystem.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    // 假设还有其他属性如 ISBN, publishDate 等

    // 默认构造函数
    public Book() {
    }

    public Book(int id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn; // 设置 ISBN 属性
    }

    // ... 其他 getter 和 setter 方法

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // 带有所有参数的构造函数
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        // 初始化其他属性
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    private String borrowStatus;
    private String location;

    // ... 构造函数，其他 getter 和 setter ...

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public String getLocation() {
        return location;
    }
    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // ... 其他属性的 getter 和 setter
    // 例如，如果您有 ISBN 和 publishDate 属性，请为它们也添加 getter 和 setter 方法
}
