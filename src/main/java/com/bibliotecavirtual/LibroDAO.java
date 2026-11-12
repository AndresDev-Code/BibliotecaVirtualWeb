
package com.bibliotecavirtual;

import com.bibliotecavirtual.config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // CREATE: registrar un libro
    public void insertar(String titulo, String autor) throws SQLException {
        String sql = "INSERT INTO libro (titulo, autor) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.executeUpdate();
            System.out.println("Libro registrado correctamente.");
        }
    }

    // READ: consultar los libros
    public void listar() throws SQLException {
        String sql = "SELECT * FROM libro ORDER BY id_libro";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.printf("%d | %s | %s%n",
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"));
            }
        }
    }

    // UPDATE: actualizar un libro
    public void actualizar(int id, String titulo, String autor)
            throws SQLException {

        String sql = "UPDATE libro SET titulo = ?, autor = ? WHERE id_libro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, id);

            int filas = stmt.executeUpdate();
            System.out.println("Libros actualizados: " + filas);
        }
    }

    // DELETE: eliminar un libro
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM libro WHERE id_libro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            System.out.println("Libros eliminados: " + filas);
        }
    }
    // Obtener libros para mostrarlos en la página web
    public List<String[]> obtenerLibros() throws SQLException {

        List<String[]> libros = new ArrayList<>();

        String sql = "SELECT id_libro, titulo, autor FROM libro ORDER BY id_libro";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] libro = {
                        String.valueOf(rs.getInt("id_libro")),
                        rs.getString("titulo"),
                        rs.getString("autor")
                };

                libros.add(libro);
            }
        }

        return libros;
    }



}
