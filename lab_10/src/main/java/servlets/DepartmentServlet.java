package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.models.Department;
import org.example.repositories.DepartmentRepository;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/departments/*")
public class DepartmentServlet extends HttpServlet {
    private final DepartmentRepository repo = new DepartmentRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        resp.setContentType("application/json");
        if (path == null || path.equals("/")) {
            Collection<Department> all = repo.findAll();
            mapper.writeValue(resp.getWriter(), all);
        } else {
            int id = Integer.parseInt(path.substring(1));
            Department d = repo.findById(id);
            if (d != null) mapper.writeValue(resp.getWriter(), d);
            else resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Department d = mapper.readValue(req.getReader(), Department.class);
        repo.save(d);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        mapper.writeValue(resp.getWriter(), d);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Department d = mapper.readValue(req.getReader(), Department.class);
        repo.update(d);
        mapper.writeValue(resp.getWriter(), d);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        repo.delete(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
