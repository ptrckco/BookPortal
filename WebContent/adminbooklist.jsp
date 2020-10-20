<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Management</title>
</head>
<body style = "font-family:arial,serif;">
    <div align="center" cellpadding=10>
    
        <table  >
            <h2>Admin List of Books</h2>
 		<center>
        <h4>
         <a href="adminbookform.jsp">Add New Book</a>
        </h4>
 		</center>
 
            <tr align=center>
                <th>Book Title</th>
                <th>Description</th>
                <th>Author</th>
                <th>Published Date</th>
                <th>ISBN</th>
                <th>Price</th>
                <th>Pages</th>
                
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr align=center>
                    <td><c:out value="${book.getbooktitle()}" /></td>
                    <td><c:out value="${book.getdescription()}" /></td>
                    <td><c:out value="${book.getauthor()}" /></td>
                    <td><c:out value="${book.getpublisheddate()}" /></td>
                    <td><c:out value="${book.getisbn()}" /></td>
                    <td><c:out value="${book.getprice()}" /></td>
                    <td><c:out value="${book.getnoofpages()}" /></td>
                    <td>
                     |<a href="${pageContext.request.contextPath}/AdminBookListServlet?action=edit&id=<c:out value='${book.getbid()}' />">Edit</a>|
                     |<a href="${pageContext.request.contextPath}/AdminBookListServlet?action=delete&id=<c:out value='${book.getbid()}' />">Delete</a>|                     
                    </td>
                </tr>
            </c:forEach>
        </table>
                    <a align = "center" href = "${pageContext.request.contextPath}/BookListServlet">Logout</a>
    </div> 



</body>
</html>
