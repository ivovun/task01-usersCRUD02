package web;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsersListServlet",  urlPatterns = {"/", "/list"})
public class UserListServlet extends HttpServlet {
    private UserService instance = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listUser", instance.selectAllUsers());
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, resp);
    }
}
