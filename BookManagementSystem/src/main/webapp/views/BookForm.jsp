<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

    <title>${book != null ? 'Edit Book' : 'Add New Book'}</title>
</head>
<body>
    <h1>${book != null ? 'Edit Book' : 'Add New Book'}</h1>
    <form action="${pageContext.request.contextPath}/books/${book != null ? 'update' : 'insert'}" method="post">
        <c:if test="${book != null}">
            <input type="hidden" name="id" value="${book.id}"/>
        </c:if>
        Title: <input type="text" name="title" value="${book != null ? book.title : ''}"/><br/>
        Author: <input type="text" name="author" value="${book != null ? book.author : ''}"/><br/>

        <!-- 借阅情况 -->
        <label for="borrowStatus">Borrow Status:</label>
        <select name="borrowStatus">
            <option value="空闲" ${book != null && book.borrowStatus == '空闲' ? 'selected' : ''}>空闲</option>
            <option value="忙碌" ${book != null && book.borrowStatus == '忙碌' ? 'selected' : ''}>忙碌</option>
        </select><br/>

        <!-- 存放位置（索书号） -->
        <label for="location">Location (索书号):</label>
        <input type="text" id="location" name="location" value="${book != null ? book.location : ''}"/><br/>

        <input type="submit" value="${book != null ? 'Update' : 'Add'} Book"/>
    </form>
</body>
</html>
