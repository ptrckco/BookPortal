package booklist.model.dao;

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
