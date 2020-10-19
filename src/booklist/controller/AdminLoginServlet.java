package booklist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booklist.model.bean.AdminLogin;
import booklist.model.dao.AdminLoginDAO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("username");
        String pws = request.getParameter("pws");
        AdminLogin loginBean = new AdminLogin();
        loginBean.setUname(uname);
        loginBean.setUpassword(pws);

        try {
            if (AdminLoginDAO.validate(loginBean)) {
                HttpSession session = request.getSession();
                session.setAttribute("username",uname);
                response.sendRedirect("success.jsp");
            } else {
                //HttpSession session = request.getSession();
            	response.sendRedirect("error.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
