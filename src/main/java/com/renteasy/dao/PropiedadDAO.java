/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renteasy.dao;

/**
 *
 * @author Admin
 */

import com.renteasy.database.ConexionBD;
import com.renteasy.models.Propiedad;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropiedadDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PropiedadDAO.class.getName());

    /**
     * Registrar una nueva propiedad
     */
    public int registrarPropiedad(Propiedad propiedad) {
        String sql = "INSERT INTO propiedades (propietario_id, titulo, descripcion, direccion, ciudad, " +
                    "provincia, precio_mensual, habitaciones, baños, area_m2, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, propiedad.getPropietarioId());
            ps.setString(2, propiedad.getTitulo());
            ps.setString(3, propiedad.getDescripcion());
            ps.setString(4, propiedad.getDireccion());
            ps.setString(5, propiedad.getCiudad());
            ps.setString(6, propiedad.getProvincia());
            ps.setBigDecimal(7, propiedad.getPrecioMensual());
            ps.setInt(8, propiedad.getHabitaciones());
            ps.setInt(9, propiedad.getBanos());
            ps.setInt(10, propiedad.getAreaM2());
            ps.setString(11, propiedad.getEstado());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        LOGGER.info("Propiedad registrada con ID: " + id);
                        return id;
                    }
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar propiedad", e);
        }
        return 0;
    }

    /**
     * Obtener propiedad por ID
     */
    public Propiedad obtenerPorId(int id) {
        String sql = "SELECT * FROM propiedades WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPropiedad(rs);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener propiedad por ID: " + id, e);
        }
        return null;
    }

    /**
     * Listar todas las propiedades
     */
    public List<Propiedad> listar() {
        List<Propiedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM propiedades ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearPropiedad(rs));
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar propiedades", e);
        }
        return lista;
    }

    /**
     * Listar propiedades por propietario
     */
    public List<Propiedad> listarPorPropietario(int propietarioId) {
        List<Propiedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM propiedades WHERE propietario_id = ? ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propietarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPropiedad(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar propiedades por propietario: " + propietarioId, e);
        }
        return lista;
    }

    /**
     * Listar propiedades por estado
     */
    public List<Propiedad> listarPorEstado(String estado) {
        List<Propiedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM propiedades WHERE estado = ? ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPropiedad(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar propiedades por estado: " + estado, e);
        }
        return lista;
    }

    /**
     * Buscar propiedades con filtros
     */
    public List<Propiedad> buscarConFiltros(String ciudad, String provincia, 
                                          Double precioMin, Double precioMax, 
                                          Integer habitaciones, String estado) {
        List<Propiedad> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM propiedades WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (ciudad != null && !ciudad.trim().isEmpty()) {
            sql.append(" AND ciudad LIKE ?");
            parametros.add("%" + ciudad + "%");
        }
        if (provincia != null && !provincia.trim().isEmpty()) {
            sql.append(" AND provincia LIKE ?");
            parametros.add("%" + provincia + "%");
        }
        if (precioMin != null) {
            sql.append(" AND precio_mensual >= ?");
            parametros.add(precioMin);
        }
        if (precioMax != null) {
            sql.append(" AND precio_mensual <= ?");
            parametros.add(precioMax);
        }
        if (habitaciones != null) {
            sql.append(" AND habitaciones >= ?");
            parametros.add(habitaciones);
        }
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append(" AND estado = ?");
            parametros.add(estado);
        }
        
        sql.append(" ORDER BY creado_en DESC");

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPropiedad(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar propiedades con filtros", e);
        }
        return lista;
    }

    /**
     * Actualizar propiedad
     */
    public boolean actualizar(Propiedad propiedad) {
        String sql = "UPDATE propiedades SET titulo = ?, descripcion = ?, direccion = ?, " +
                    "ciudad = ?, provincia = ?, precio_mensual = ?, habitaciones = ?, " +
                    "baños = ?, area_m2 = ?, estado = ? WHERE id = ?";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, propiedad.getTitulo());
            ps.setString(2, propiedad.getDescripcion());
            ps.setString(3, propiedad.getDireccion());
            ps.setString(4, propiedad.getCiudad());
            ps.setString(5, propiedad.getProvincia());
            ps.setBigDecimal(6, propiedad.getPrecioMensual());
            ps.setInt(7, propiedad.getHabitaciones());
            ps.setInt(8, propiedad.getBanos());
            ps.setInt(9, propiedad.getAreaM2());
            ps.setString(10, propiedad.getEstado());
            ps.setInt(11, propiedad.getId());

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Propiedad actualizada: " + propiedad.getId());
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar propiedad: " + propiedad.getId(), e);
            return false;
        }
    }

    /**
     * Cambiar estado de propiedad
     */
    public boolean cambiarEstado(int propiedadId, String nuevoEstado) {
        String sql = "UPDATE propiedades SET estado = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, propiedadId);

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Estado de propiedad " + propiedadId + " cambiado a: " + nuevoEstado);
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al cambiar estado de propiedad: " + propiedadId, e);
            return false;
        }
    }

    /**
     * Eliminar propiedad
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM propiedades WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            boolean eliminado = ps.executeUpdate() > 0;
            if (eliminado) {
                LOGGER.info("Propiedad eliminada con ID: " + id);
            }
            return eliminado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar propiedad: " + id, e);
            return false;
        }
    }

    /**
     * Contar propiedades por propietario
     */
    public int contarPorPropietario(int propietarioId) {
        String sql = "SELECT COUNT(*) FROM propiedades WHERE propietario_id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propietarioId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al contar propiedades por propietario: " + propietarioId, e);
        }
        return 0;
    }

    /**
     * Contar propiedades por estado
     */
    public int contarPorEstado(String estado) {
        String sql = "SELECT COUNT(*) FROM propiedades WHERE estado = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al contar propiedades por estado: " + estado, e);
        }
        return 0;
    }

    /**
     * Método auxiliar para mapear ResultSet a objeto Propiedad
     */
    private Propiedad mapearPropiedad(ResultSet rs) throws SQLException {
        return new Propiedad(
            rs.getInt("id"),
            rs.getInt("propietario_id"),
            rs.getString("titulo"),
            rs.getString("descripcion"),
            rs.getString("direccion"),
            rs.getString("ciudad"),
            rs.getString("provincia"),
            rs.getBigDecimal("precio_mensual"),
            rs.getInt("habitaciones"),
            rs.getInt("baños"),
            rs.getInt("area_m2"),
            rs.getString("estado"),
            rs.getTimestamp("creado_en")
        );
    }
}
