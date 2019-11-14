package web;

import dao.UserDaoImpl;
import exception.DBException;
import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



//@WebServlet(name = "UserServlet",  urlPatterns = {"/", "/home"})
@WebServlet(name = "UserServlet",  urlPatterns = {"/", "/home"})
public class UserServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (  DBException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws  IOException, ServletException {
		List<User> listUser = UserServiceImpl.instance().selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws  ServletException, IOException, DBException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", UserServiceImpl.instance().selectUser(Integer.parseInt(request.getParameter("id"))));
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws  IOException, DBException {
		UserServiceImpl.instance().insertUser(new User(request.getParameter("name")
				, request.getParameter("email")
				, request.getParameter("country")));
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws DBException, IOException {
		UserServiceImpl.instance().updateUser(new User(Long.valueOf(request.getParameter("id")),
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("country")));
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException,  DBException  {
		UserServiceImpl.instance().deleteUser(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("list");

	}

}
