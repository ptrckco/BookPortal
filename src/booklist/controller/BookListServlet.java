package booklist.controller;

import booklist.model.bean.BookList;package booklist.model.dao;
package booklist.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;package booklist.controller;

import booklist.model.bean.BookList;
import booklist.model.dao.BookListDAO;



import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookListDAO booklistDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub     
    }
    public void init() {
        booklistDAO = new BookListDAO();
    }
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action==null) {
			action="No action";
		}
		RequestDispatcher dispatcher;
		try {
            switch (action) {     
            case "edit":
                showEditBook(request, response);
                break;
            case "new":
                showNewBook(request, response);
                break;
            case "search":
                showSearchBook(request, response);
                break;
            case "select":
				selectBookTitle(request, response);
			break;
            default:
                listBook(request, response);
                break;
            }
				} catch (Exception ex) {
	            throw new ServletException(ex);
	        }

	    }//End of doPost method
	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < BookList > listBook = booklistDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
		dispatcher.forward(request, response);
		}
	
	private void showNewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		dispatcher.forward(request, response);
		}
	
	private void showEditBook(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   int id = Integer.parseInt(request.getParameter("id"));
		   BookList existingBook = booklistDAO.selectBookList(id);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		 request.setAttribute("book", existingBook);
		 dispatcher.forward(request, response);
		}
	
	private void showSearchBook(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   String title = request.getParameter("booktitle");
		   List < BookList > existingBook = booklistDAO.searchBookTitle(title);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
		 request.setAttribute("listBook", existingBook);
		 dispatcher.forward(request, response);
		}
	private void selectBookTitle(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   int id = Integer.parseInt(request.getParameter("id"));
		   BookList existingBook = booklistDAO.selectBookList(id);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		 request.setAttribute("book", existingBook);
		 dispatcher.forward(request, response);
		}
	
	
	
}


import booklist.model.bean.BookList;

public class BookListDAO {
	//Define instance variables	
	private String DBURL = "jdbc:mysql://localhost:3306/bookstore";
    private String DBUsername = "root";
    private String DBPassword = "mysql";


    private String SELECTBOOKID = "select bid,cid,booktitle,description,author,publisheddate,isbn,price,noofpages from books where bid =?";
    private String SELECTALLBOOKS = "select * from books ";
    private String SEARCHBOOK = "select * from books where booktitle LIKE (?)";



    //constructor
    public BookListDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    

    

    public BookList selectBookList(int bid) {
    	BookList book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // Step 1: Establishing a Connection
        try {
        	connection = getConnection();
          // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SELECTBOOKID);
            preparedStatement.setInt(1, bid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String booktitle = rs.getString("booktitle");
                int cid = rs.getInt("cid");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
                book = new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return book;
    }

    public List < BookList > selectAllBooks() {
    	//Book book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < BookList > books = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SELECTALLBOOKS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int bid = rs.getInt("bid");
                int cid = rs.getInt("cid");
                String booktitle = rs.getString("booktitle");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
              
                books.add(new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return books;
    }
    
    public List < BookList > searchBookTitle(String search) {
    	//Book book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < BookList > books = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SEARCHBOOK);

            preparedStatement.setString(1, "%" + search + "%");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int bid = rs.getInt("bid");
                int cid = rs.getInt("cid");
                String booktitle = rs.getString("booktitle");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
              
                books.add(new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return books;
    }

   

   

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
    	 if (r != null)	{
             try {
                r.close();
             } catch (Exception e) {}
                r = null;
             }
 	
          if (p != null) {
             try {
                p.close();
             } catch (Exception e) {}
                p = null;
             }
 	
          if (c != null) {
             try {
                c.close();
             } catch (Exception e) {
           	  c = null;
             }

          }
    }

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import booklist.model.bean.BookList;

public class BookListDAO {
	//Define instance variables	
	private String DBURL = "jdbc:mysql://localhost:3306/bookstore";
    private String DBUsername = "root";
    private String DBPassword = "mysql";


    private String SELECTBOOKID = "select bid,cid,booktitle,description,author,publisheddate,isbn,price,noofpages from books where bid =?";
    private String SELECTALLBOOKS = "select * from books ";
    private String SEARCHBOOK = "select * from books where booktitle LIKE (?)";



    //constructor
    public BookListDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    

    

    public BookList selectBookList(int bid) {
    	BookList book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // Step 1: Establishing a Connection
        try {
        	connection = getConnection();
          // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SELECTBOOKID);
            preparedStatement.setInt(1, bid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String booktitle = rs.getString("booktitle");
                int cid = rs.getInt("cid");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
                book = new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return book;
    }

    public List < BookList > selectAllBooks() {
    	//Book book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < BookList > books = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SELECTALLBOOKS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int bid = rs.getInt("bid");
                int cid = rs.getInt("cid");
                String booktitle = rs.getString("booktitle");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
              
                books.add(new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return books;
    }
    
    public List < BookList > searchBookTitle(String search) {
    	//Book book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < BookList > books = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SEARCHBOOK);

            preparedStatement.setString(1, "%" + search + "%");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int bid = rs.getInt("bid");
                int cid = rs.getInt("cid");
                String booktitle = rs.getString("booktitle");
                String description = rs.getString("description");
                String author = rs.getString("author");
                Timestamp publisheddate = rs.getTimestamp("publisheddate");
                String isbn = rs.getString("isbn");
                float price = rs.getFloat("price");
                int noofpages = rs.getInt("noofpages");
              
                books.add(new BookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return books;
    }

   

   

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
    	 if (r != null)	{
             try {
                r.close();
             } catch (Exception e) {}
                r = null;
             }
 	
          if (p != null) {
             try {
                p.close();
             } catch (Exception e) {}
                p = null;
             }
 	
          if (c != null) {
             try {
                c.close();
             } catch (Exception e) {
           	  c = null;
             }

          }
    }

}

import booklist.model.dao.BookListDAO;



import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookListDAO booklistDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub     
    }
    public void init() {
        booklistDAO = new BookListDAO();
    }
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action==null) {
			action="No action";
		}
		RequestDispatcher dispatcher;
		try {
            switch (action) {     
            case "edit":
                showEditBook(request, response);
                break;
            case "new":
                showNewBook(request, response);
                break;
            case "search":
                showSearchBook(request, response);
                break;
            case "select":
				selectBookTitle(request, response);
			break;
            default:
                listBook(request, response);
                break;
            }
				} catch (Exception ex) {
	            throw new ServletException(ex);
	        }

	    }//End of doPost method
	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < BookList > listBook = booklistDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
		dispatcher.forward(request, response);
		}
	
	private void showNewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		dispatcher.forward(request, response);
		}
	
	private void showEditBook(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   int id = Integer.parseInt(request.getParameter("id"));
		   BookList existingBook = booklistDAO.selectBookList(id);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		 request.setAttribute("book", existingBook);
		 dispatcher.forward(request, response);
		}
	
	private void showSearchBook(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   String title = request.getParameter("search");
		   List < BookList > existingBook = booklistDAO.searchBookTitle(title);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
		 request.setAttribute("book", existingBook);
		 dispatcher.forward(request, response);
		}
	private void selectBookTitle(HttpServletRequest request, HttpServletResponse response)  throws SQLException, ServletException, IOException {
		   int id = Integer.parseInt(request.getParameter("id"));
		   BookList existingBook = booklistDAO.selectBookList(id);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("bookdetail.jsp");
		 request.setAttribute("book", existingBook);
		 dispatcher.forward(request, response);
		}
	
	
	
}
