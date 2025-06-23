<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="ISO-8859-1">
	<title>Aplicativo da Livraria</title>
	
</head>
<body>


	<header>
		<center>
		<nav class="menu">
			<a href="/Bookstore">
				<h1>Sistemas web - TP03</h1>
			</a>
			<div>
				<a href="/Bookstore/new">Adicionar Livro</a>
				<a href="/Bookstore/list">Lista de livros</a>
				<a href="/Bookstore/creditos.jsp">Créditos</a>
			</div>
		</nav>
		</center>
	</header>
	
    <center><h1>Books Management</h1></center>
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                        <a href="/Bookstore/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Bookstore/delete?id=<c:out value='${book.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>