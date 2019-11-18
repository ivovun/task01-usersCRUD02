package web;

import exception.DBException;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEditServlet", urlPatterns = {"/edit", "/update"})
public class UserEditServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        try {
            userService.updateUser(new User(req.getParameter("id"),
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("country")));
                resp.sendRedirect("list");

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                req.setAttribute("user", userService.selectUser(Integer.parseInt(req.getParameter("id"))));
                req.getRequestDispatcher("user-form.jsp").forward(req, resp);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
