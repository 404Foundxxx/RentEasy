package com.renteasy.dao;

import com.renteasy.database.ConexionBD;
import com.renteasy.models.Usuario;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

   
    /**
     * Método para login - Verifica credenciales y devuelve el usuario
     */
    public Usuario realizarLogin(String email, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("telefono"),
                        rs.getString("tipo_usuario"),
                        rs.getTimestamp("creado_en")
                    );
                }
            }

        } catch (SQLException | FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error en login para email: " + email, ex);
        }
        return null;
    }

    /**
     * Verifica si existe un usuario con el email dado
     */
    public boolean existeEmail(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException | FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error al verificar email: " + email, ex);
            return false;
        }
    }

    /**
     * Método para registrar un nuevo usuario
     */
    public boolean registrarUsuario(String nombre, String email, String contrasena, String telefono, String tipoUsuario) {
        // Validar que no exista el email
        if (existeEmail(email)) {
            LOGGER.warning("Intento de registro con email existente: " + email);
            return false;
        }

        String sql = "INSERT INTO usuarios (nombre, email, contraseña, telefono, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, contrasena);
            ps.setString(4, telefono);
            ps.setString(5, tipoUsuario);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                LOGGER.info("Usuario registrado exitosamente: " + email);
                return true;
            }
            return false;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar usuario: " + email, e);
            return false;
        }
    }

    /**
     * Registrar usuario y devolver el ID generado
     */
    public int registrarUsuarioConId(String nombre, String email, String contrasena, String telefono, String tipoUsuario) {
        if (existeEmail(email)) {
            return -1; // Email ya existe
        }

        String sql = "INSERT INTO usuarios (nombre, email, contraseña, telefono, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, contrasena);
            ps.setString(4, telefono);
            ps.setString(5, tipoUsuario);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar usuario con ID: " + email, e);
        }
        return 0;
    }

    /**
     * Obtener un usuario por su ID
     */
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("telefono"),
                        rs.getString("tipo_usuario"),
                        rs.getTimestamp("creado_en")
                    );
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener usuario por ID: " + id, e);
        }
        return null;
    }

    /**
     * Obtener un usuario por su email
     */
    public Usuario obtenerPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("telefono"),
                        rs.getString("tipo_usuario"),
                        rs.getTimestamp("creado_en")
                    );
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener usuario por email: " + email, e);
        }
        return null;
    }

    /**
     * Listar todos los usuarios
     */
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY creado_en DESC";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contraseña"),
                    rs.getString("telefono"),
                    rs.getString("tipo_usuario"),
                    rs.getTimestamp("creado_en")
                ));
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar usuarios", e);
        }
        return lista;
    }

    /**
     * Listar usuarios por tipo
     */
    public List<Usuario> listarPorTipo(String tipoUsuario) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE tipo_usuario = ? ORDER BY creado_en DESC";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("telefono"),
                        rs.getString("tipo_usuario"),
                        rs.getTimestamp("creado_en")
                    ));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar usuarios por tipo: " + tipoUsuario, e);
        }
        return lista;
    }

    /**
     * Actualizar un usuario
     */
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, contraseña = ?, telefono = ?, tipo_usuario = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getTipoUsuario());
            ps.setInt(6, usuario.getId());

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Usuario actualizado: " + usuario.getEmail());
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar usuario: " + usuario.getEmail(), e);
            return false;
        }
    }

    /**
     * Actualizar contraseña de un usuario
     */
    public boolean actualizarContrasena(int usuarioId, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contraseña = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaContrasena);
            ps.setInt(2, usuarioId);

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Contraseña actualizada para usuario ID: " + usuarioId);
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar contraseña para usuario ID: " + usuarioId, e);
            return false;
        }
    }

    /**
     * Eliminar un usuario por ID
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            boolean eliminado = ps.executeUpdate() > 0;
            if (eliminado) {
                LOGGER.info("Usuario eliminado con ID: " + id);
            }
            return eliminado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar usuario con ID: " + id, e);
            return false;
        }
    }

    /**
     * Contar total de usuarios
     */
    public int contarUsuarios() {
        String sql = "SELECT COUNT(*) FROM usuarios";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al contar usuarios", e);
        }
        return 0;
    }

    /**
     * Contar usuarios por tipo
     */
    public int contarUsuariosPorTipo(String tipoUsuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE tipo_usuario = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al contar usuarios por tipo: " + tipoUsuario, e);
        }
        return 0;
    }
}