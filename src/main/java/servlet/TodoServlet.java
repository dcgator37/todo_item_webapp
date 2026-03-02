package servlet;

import database.TodoDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import model.TodoItem;
import util.DatabaseUtil;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class TodoServlet extends HttpServlet {

    private TodoDatabase dao;

    @Override
    public void init() {
        System.out.println("=== TodoServlet init() CALLED ===");
        DatabaseUtil.initializeDatabase();
        dao = new TodoDatabase();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("=== doGet called ===");
        try {
            List<TodoItem> items = dao.findAll();
            System.out.println(items.size());
            req.setAttribute("items", items);
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req,resp);
            //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        System.out.println("=== doPost called ===");
        req.getParameterMap().forEach((k, v) -> System.out.println(k + " -> " + String.join(",", v)));


        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                System.out.println("doPost() adding text: " + req.getParameter("text"));
                dao.add(req.getParameter("text"));
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/todos");
    }
}