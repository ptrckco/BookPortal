<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Form</title>
</head>
<body>

<center>
        <h4>
         <a href="adminbooklist.jsp">Admin List All Books</a>
        </h4>
 </center>
    <div align="center">
  <c:if test="${book != null}">
   <form action="${pageContext.request.contextPath}/AdminBookListServlet" method="post">
    <input type="hidden" name="action" value="update">
        </c:if>
        <c:if test="${book == null}">
   <form action="${pageContext.request.contextPath}/AdminBookListServlet?action=insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${book != null}">
               Edit Book
              </c:if>
              <c:if test="${book == null}">
               Add New Book
              </c:if>
             </h2>
            </caption>
          <c:if test="${book != null}">
           <input type="hidden" name="id" value="<c:out value='${book.getbid()}' />" />
          </c:if>  
          	
          	<tr>
                <th>Bid: </th>
                <td>
                 <input type="number" name="bid" size="10" required 
                   value="<c:out value='${book.getbid()}' />"
                 />
                </td>
            </tr>
            
            <tr>
                <th>CID: </th>
                <td>
                 <input type="number" name="cid" size="10" required 
                   value="<c:out value='${book.getcid()}' />"
                 />
                </td>
            </tr>
    
            <tr>
                <th>Book Title: </th>
                <td>
                 <input type="text" name="booktitle" size="45" required
                   value="<c:out value='${book.getbooktitle()}' />"
                  />
                </td>
            </tr>
            
            <tr>
                <th>Description: </th>
                <td>
                 <input type="text" name="description" size="60" required 
                   value="<c:out value='${book.getdescription()}' />"
                 />
                </td>
            </tr>                       
            
            <tr>
                <th>Author: </th>
                <td>
                 <input type="text" name="author" size="45" required
                   value="<c:out value='${book.getauthor()}' />"
                  />
                </td>
            </tr>
            
            <tr>
                <th>Published Date: </th>
                <td>
                 <input type="timestamp" name="publisheddate" size="45" required
                   value="<c:out value='${book.getpublisheddate()}' />"
                  />
                </td>
            </tr>
            
            <tr>
                <th>ISBN: </th>
                <td>
                 <input type="number" name="isbn" size="15" required
                   value="<c:out value='${book.getisbn()}' />"
                  />
                </td>
            </tr>
                                 
            
            <tr>
                <th>Price: </th>
                <td>
                 <input type="number" name="price" required 
                   value="<c:out value='${book.getprice()}' />"
                 />
                </td>
            </tr>
            
            <tr>
                <th>Noofpages: </th>
                <td>
                 <input type="number" name="noofpages" size="10" required 
                   value="<c:out value='${book.getnoofpages()}' />"
                 />
                </td>
            </tr>
                
            
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div>

</body>
</html>
