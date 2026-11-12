
package com.bibliotecavirtual.servlet;

import com.bibliotecavirtual.LibroDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/libros")
public class LibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            LibroDAO dao = new LibroDAO();

            List<String[]> libros = dao.obtenerLibros();

            request.setAttribute("libros", libros);

            request.getRequestDispatcher("/libros.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al consultar los libros", e
            );
        }
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        if (titulo == null || titulo.isBlank()
                || autor == null || autor.isBlank()) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El título y el autor son obligatorios"
            );
            return;
        }

        try {
            LibroDAO dao = new LibroDAO();

            dao.insertar(titulo.trim(), autor.trim());

            response.sendRedirect(
                    request.getContextPath() + "/libros"
            );

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al registrar el libro", e
            );
        }
    }

}
