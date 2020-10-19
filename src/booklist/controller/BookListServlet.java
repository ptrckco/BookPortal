package booklist.controller;

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
