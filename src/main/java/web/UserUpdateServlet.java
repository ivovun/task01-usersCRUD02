package web;

import exception.DBException;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserUpdateServlet", urlPatterns = {"/update"})
public class UserUpdateServlet extends HttpServlet {
    private UserService instance = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            instance.updateUser(new User(Long.valueOf(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("country")));
            resp.sendRedirect("list");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
