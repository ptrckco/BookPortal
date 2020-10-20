<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style = "font-family:arial,serif;">
    <div align="center">
  <c:if test="${book != null}">
   <form action="${pageContext.request.contextPath}/BookListServlet" method="post">
    <input type="hidden" name="action" value="update">
        </c:if>
        <c:if test="${book == null}">
   <form action="${pageContext.request.contextPath}/BookListServlet?action=insert" method="post">
        </c:if>     
        
        <table align = 'center' width = '400' height = '80'>
        <h1>Book Information</h1>       
        	
     		

           <h3><p><b>${book.getbooktitle()}<br>
                ${book.getdescription()}
                </p></h3>                     
            
            <tr>
                <th>Author: </th>
                <td>
                   ${book.getauthor()}
                </td>
            </tr>
            
            <tr>
                <th>Published Date: </th>
                <td>
                   ${book.getpublisheddate()}
                </td>
            </tr>
            
            <tr>
                <th>ISBN: </th>
                <td>
                   ${book.getisbn()}
                </td>
            </tr>
                                 
            
            <tr>
                <th>Price: </th>
                <td>
                   ${book.getprice()}              
                </td>
            </tr>
            
            <tr>
                <th>Noofpages: </th>
                <td>
                   ${book.getnoofpages()}
                </td>
            </tr>
               
                       
        </table>
        	
		
        </form>
    </div>
    <h4 align ='center'>
		    <a href="${pageContext.request.contextPath}/BookListServlet?action=list">Return to book list</a>
	</h4>
             
        

</body>
</html>
