package servlet;

import database.TodoDatabase;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class TodoServlet extends HttpServlet {

    private TodoDatabase dao;

    @Override
    public void init() {
        dao = new TodoDatabase();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setAttribute("items", dao.findAll());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                dao.add(req.getParameter("text"));
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/");
    }
}