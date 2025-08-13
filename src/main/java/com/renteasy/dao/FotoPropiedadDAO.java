package com.renteasy.dao;

import com.renteasy.database.ConexionBD;
import com.renteasy.models.FotoPropiedad;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FotoPropiedadDAO {
    
    private static final Logger LOGGER = Logger.getLogger(FotoPropiedadDAO.class.getName());

    /**
     * Agregar una foto a una propiedad
     */
    public int agregarFoto(FotoPropiedad foto) {
        String sql = "INSERT INTO fotos_propiedad (propiedad_id, url_foto) VALUES (?, ?)";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, foto.getPropiedadId());
            ps.setString(2, foto.getUrlFoto());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        LOGGER.info("Foto agregada con ID: " + id);
                        return id;
                    }
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar foto", e);
        }
        return 0;
    }

    /**
     * Obtener todas las fotos de una propiedad
     */
    public List<FotoPropiedad> obtenerFotosPorPropiedad(int propiedadId) {
        List<FotoPropiedad> fotos = new ArrayList<>();
        String sql = "SELECT * FROM fotos_propiedad WHERE propiedad_id = ?";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propiedadId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fotos.add(mapearFoto(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener fotos de propiedad: " + propiedadId, e);
        }
        return fotos;
    }

    /**
     * Obtener la primera foto de una propiedad (foto principal)
     */
    public FotoPropiedad obtenerFotoPrincipal(int propiedadId) {
        String sql = "SELECT * FROM fotos_propiedad WHERE propiedad_id = ? LIMIT 1";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propiedadId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearFoto(rs);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener foto principal de propiedad: " + propiedadId, e);
        }
        return null;
    }

    /**
     * Eliminar una foto
     */
    public boolean eliminarFoto(int id) {
        String sql = "DELETE FROM fotos_propiedad WHERE id = ?";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar foto: " + id, e);
        }
        return false;
    }

    /**
     * Eliminar todas las fotos de una propiedad
     */
    public boolean eliminarFotosPorPropiedad(int propiedadId) {
        String sql = "DELETE FROM fotos_propiedad WHERE propiedad_id = ?";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propiedadId);
            int filasAfectadas = ps.executeUpdate();
            LOGGER.info("Eliminadas " + filasAfectadas + " fotos de la propiedad: " + propiedadId);
            return true;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar fotos de propiedad: " + propiedadId, e);
        }
        return false;
    }

    /**
     * Método auxiliar para mapear ResultSet a objeto FotoPropiedad
     */
    private FotoPropiedad mapearFoto(ResultSet rs) throws SQLException {
        return new FotoPropiedad(
            rs.getInt("id"),
            rs.getInt("propiedad_id"),
            rs.getString("url_foto")
        );
    }
}
