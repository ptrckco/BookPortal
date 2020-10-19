package booklist.model.dao;

import java.sql.*;
import java.util.*;

import booklist.model.bean.AdminBookList;


public class AdminBookListDAO {
	
	//Define instance variables	
	private String DBURL = "jdbc:mysql://localhost:3306/bookstore";
    private String DBUsername = "root";
    private String DBPassword = "mysql";

    private String INSERTBOOKSQL = "INSERT INTO Books (bid, cid,  booktitle, description, author, publisheddate, isbn, price, noofpages) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private String SELECTBOOKID = "select bid,cid,booktitle,description,author,publisheddate,isbn,price,noofpages from books where bid =?";
    private String SELECTALLBOOKS = "select * from books";
    private String DELETEBOOKSQL = "delete from books where bid = ?;";
    private String UPDATEBOOKSQL = "update books set cid = ?, booktitle = ?, description= ?, author = ?, publisheddate = ?, isbn = ?, price = ?, noofpages = ? where bid = ?;";

    //constructor
    public AdminBookListDAO() {}

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

    public void insertBook(AdminBookList book) throws SQLException {
        System.out.println(INSERTBOOKSQL);
        Connection connection = null; 
    	PreparedStatement preparedStatement = null;
        // try-with-resource statement will auto close the connection.
        try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(INSERTBOOKSQL);
            preparedStatement.setInt(1, book.getbid());
            preparedStatement.setInt(2, book.getcid());
            preparedStatement.setString(3, book.getbooktitle());		            
            preparedStatement.setString(4, book.getdescription());
            preparedStatement.setString(5, book.getauthor());
            preparedStatement.setTimestamp(6, book.getpublisheddate());
            preparedStatement.setString(7, book.getisbn());
            preparedStatement.setFloat(8, book.getprice());
            preparedStatement.setInt(9, book.getnoofpages());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement,null);
        }
    }

    public AdminBookList selectBook(int bid) {
    	AdminBookList book = null;
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
                book = new AdminBookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return book;
    }

    public List < AdminBookList > selectAllBooks() {
    	//Book book = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < AdminBookList > books = new ArrayList < > ();
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
              
                books.add(new AdminBookList(bid, booktitle, cid, description, author, publisheddate, isbn, price, noofpages));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return books;
    }

    public boolean deleteBook(int id) throws SQLException {
        boolean bookDeleted = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	 connection = getConnection(); 
        	 preparedStatement = connection.prepareStatement(DELETEBOOKSQL);
        	 preparedStatement.setInt(1, id);
            bookDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return bookDeleted;
    }

    public boolean updateBook (AdminBookList book) throws SQLException {
        boolean bookUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(UPDATEBOOKSQL);

            preparedStatement.setInt(1, book.getcid());
            preparedStatement.setString(2, book.getbooktitle());		            
            preparedStatement.setString(3, book.getdescription());
            preparedStatement.setString(4, book.getauthor());
            preparedStatement.setTimestamp(5, book.getpublisheddate());
            preparedStatement.setString(6, book.getisbn());
            preparedStatement.setFloat(7, book.getprice());
            preparedStatement.setInt(8, book.getnoofpages());
            preparedStatement.setInt(9, book.getbid());
            
        	bookUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        catch (SQLException e) {
        	printSQLException (e);
        }     
      	finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return bookUpdated;
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
