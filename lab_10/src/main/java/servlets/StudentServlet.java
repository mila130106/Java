package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;
import repository.StudentRepository;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final StudentRepository repo = new StudentRepository();

    @Override
    public void init() {
        repo.add(1, new Student(1, "Alice", 20, "CS"));
        repo.add(2, new Student(2, "Bob", 22, "Math"));
        System.out.println("StudentServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Student> all = repo.getAll();
            resp.getWriter().write(mapper.writeValueAsString(all));
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            Student s = repo.getById(id);
            if (s != null)
                resp.getWriter().write(mapper.writeValueAsString(s));
            else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Student not found\"}");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student s = mapper.readValue(req.getInputStream(), Student.class);
        repo.add(s.getId(), s);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("{\"message\":\"Student added successfully\"}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student s = mapper.readValue(req.getInputStream(), Student.class);
        repo.update(s.getId(), s);
        resp.getWriter().write("{\"message\":\"Student updated\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            int id = Integer.parseInt(pathInfo.substring(1));
            repo.delete(id);
            resp.getWriter().write("{\"message\":\"Student deleted\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void destroy() {
        System.out.println("StudentServlet destroyed.");
    }
}
