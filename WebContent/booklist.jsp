<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store List</title>
</head>

<body style = "font-family:arial,serif;">
    <div align="center" cellpadding=10>
    
        <table width = '200' height = '80' align = 'left' >
         <h2>Book Store List</h2>
            <c:forEach var="book" items="${listBook}">
            
            	<tr>

                <td>
                 ${book.getbid()}
                </td>
            	</tr>
            
    
            	
                <tr fontstyle = "bold">
                	
                   	
                    <td ><b><a href = "${pageContext.request.contextPath}/BookListServlet?action=edit&id=<c:out value='${book.getbid()}'/>"><c:out value="${book.getbooktitle()}" /></a></b></td>
                    </tr>
                    
                    
					<tr>
                    <td><c:out value="${book.getauthor()}" /></td>
               		</tr>
               		
               		
               		<tr>
                    <td><c:out value="${book.getdescription()}" /><hr></td>
               		</tr>
                	
            </c:forEach>
					
					<tr>
						<td><h4 align = "left">
         				<a href="bookindex.jsp">Back</a>
        				</h4></td>
					</tr>
					
        </table>
    </div> 
		
		<form action="BookListServlet" method="get">
		<p align = "right">
		
			<input type="text" placeholder="Enter Book Title" name="booktitle">
			<input type="hidden" name="action" value="search" />
			<input type="submit" value="Search" />
			
		</p>
		</form>

             

</body>
</html>
<meta charset="ISO-8859-1">
<title>Book Store List</title>
</head>

<body style = "font-family:arial,serif;">
    <div align="center" cellpadding=10>
    
        <table width = '200' height = '80' align = 'left' >
         <h2>Book Store List</h2>
            <c:forEach var="book" items="${listBook}">
            
            	<tr>

                <td>
                 ${book.getbid()}
                </td>
            	</tr>
            
    
            	
                <tr fontstyle = "bold">
                	
                   	
                    <td ><b><a href = "${pageContext.request.contextPath}/BookListServlet?action=edit&id=<c:out value='${book.getbid()}'/>"><c:out value="${book.getbooktitle()}" /></a></b></td>
                    </tr>
                    
                    
					<tr>
                    <td><c:out value="${book.getauthor()}" /></td>
               		</tr>
               		
               		
               		<tr>
                    <td><c:out value="${book.getdescription()}" /><hr></td>
               		</tr>
                	
            </c:forEach>
					
					<tr>
						<td><h4 align = "left">
         				<a href="bookindex.jsp">Back</a>
        				</h4></td>
					</tr>
					
        </table>
    </div> 

		<p align = "right">
			<input type="text" placeholder="Enter Book Title" name="booktitle">
			<a href="${pageContext.request.contextPath}/BookListServlet?action=search&title=<c:out value='${book.getbooktitle()}' />">Search</a>
		</p>

             

</body>
</html>
