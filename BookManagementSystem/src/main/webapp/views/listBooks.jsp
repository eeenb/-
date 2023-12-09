<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
    <title>图书管理系统</title>
</head>
<body>
    <h1>图书管理系统</h1>

    <!-- 只在没有执行搜索时显示添加书籍的链接 -->
    <c:if test="${empty searchPerformed}">
        <a href="${pageContext.request.contextPath}/books/new">Add New Book</a>
    </c:if>

    <!-- 搜索书籍表单 -->
    <form action="${pageContext.request.contextPath}/books/search" method="get" onsubmit="return validateSearchForm()">
        Search: <input type="text" id="searchQuery" name="query" />
        <input type="submit" value="Search" />
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

    <!-- 根据是否执行了搜索来显示搜索结果或书籍列表 -->
    <c:choose>
        <c:when test="${not empty searchPerformed}">
            <!-- 如果执行了搜索，显示搜索结果 -->
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
                            <td>${book.borrowStatus}</td>
                            <td>${book.location}</td>
                        </tr>
                    </c:forEach>
                </table>
                <!-- 添加返回到书籍列表的按钮 -->
<c:choose>
    <c:when test="${sessionScope.userType == 'admin'}">
        <!-- 管理员用户看到的返回按钮 -->
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/books'">返回书籍列表</button>
    </c:when>
    <c:when test="${sessionScope.userType == 'user'}">
        <!-- 普通用户看到的返回按钮 -->
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/userBooks/search'">返回搜索页面</button>
    </c:when>
    <c:otherwise>
        <!-- 如果用户类型未知或未登录，可以选择不显示按钮或显示通用返回按钮 -->
    </c:otherwise>
</c:choose>
                
            </c:if>
            <c:if test="${empty searchResults}">
                <p>没有找到与搜索词 '${searchQuery}' 相关的书籍。</p>
            </c:if>
        </c:when>
        <c:otherwise>
            <!-- 如果没有执行搜索，显示书籍列表和添加书籍的表单 -->
            <table border="1">
                <!-- 表头 -->
                <tr>
               	   	<th>序号</th>
               	    <th>ID</th>
                     <th>书名</th>
                      <th>作者</th>
                      <th>借阅状态</th>
                      <th>位置</th>
                </tr>
                <!-- 显示所有书籍 -->
                <c:forEach items="${books}" var="book" varStatus="status">
                    <tr>
                  		<td>${status.index + 1}</td> <!-- 显示序号 -->
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.borrowStatus}</td>
                        <td>${book.location}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/books/edit?id=${book.id}">Edit</a>
                            <a href="${pageContext.request.contextPath}/books/delete?id=${book.id}" onclick="return confirm('Are you sure?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
