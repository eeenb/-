<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
<head>
    <title>用户查询 - 图书管理系统</title>
</head>
<body>
    <h1>用户查询 - 图书管理系统</h1>

    <!-- 搜索书籍表单 -->
    <form action="${pageContext.request.contextPath}/books/search" method="get" onsubmit="return validateSearchForm()">
        Search: <input type="text" id="searchQuery" name="query" />
        <input type="submit" value="搜索" />
    </form>
    
    <script>
        function validateSearchForm() {
            var query = document.getElementById('searchQuery').value.trim();
            if (query === "") {
                alert("请输入关键词");
                return false;
            }
            return true;
        }
    </script>

    <!-- 显示搜索结果 -->
    <c:if test="${not empty searchResults}">
        <h2>搜索结果: '${searchQuery}'</h2>
        <table border="1">
            <!-- 表头 -->
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>作者</th>
                <th>借阅状态</th>
                <th>位置</th>
            </tr>
            <!-- 显示搜索到的书籍 -->
            <c:forEach items="${searchResults}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.borrowStatus ? '已借出' : '可借阅'}</td>
                    <td>${book.location}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty searchResults and not empty searchPerformed}">
        <p>没有找到与搜索词 '${searchQuery}' 相关的书籍。</p>
    </c:if>
</body>
</html>
