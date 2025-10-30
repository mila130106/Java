package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Course;
import org.example.repositories.CourseRepository;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/courses/*")
public class CourseServlet extends HttpServlet {
    private final CourseRepository repo = new CourseRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json");
        if (path == null || path.equals("/")) {
            Collection<Course> all = repo.findAll();
            mapper.writeValue(resp.getWriter(), all);
        } else {
            int id = Integer.parseInt(path.substring(1));
            Course c = repo.findById(id);
            if (c != null) mapper.writeValue(resp.getWriter(), c);
            else resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course c = mapper.readValue(req.getReader(), Course.class);
        repo.save(c);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        mapper.writeValue(resp.getWriter(), c);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course c = mapper.readValue(req.getReader(), Course.class);
        repo.update(c);
        mapper.writeValue(resp.getWriter(), c);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        repo.delete(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
