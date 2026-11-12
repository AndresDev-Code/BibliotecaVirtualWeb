<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Libros - Biblioteca Virtual</title>
</head>
<body>

    <h1>Biblioteca Virtual</h1>
    <h2>Libros registrados</h2>

    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor</th>
        </tr>

        <%
            List<String[]> libros =
                (List<String[]>) request.getAttribute("libros");

            if (libros != null) {
                for (String[] libro : libros) {
        %>
        <tr>
            <td><%= libro[0] %></td>
            <td><%= libro[1] %></td>
            <td><%= libro[2] %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <h2>Registrar nuevo libro</h2>

    <form action="libros" method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required>

        <br><br>

        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor" required>

        <br><br>

        <button type="submit">Guardar libro</button>
    </form>

</body>
</html>
