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
import com.renteasy.models.Contrato;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContratoDAO {
    
    private static final Logger LOGGER = Logger.getLogger(ContratoDAO.class.getName());

    /**
     * Crear un nuevo contrato
     */
    public int crearContrato(Contrato contrato) {
        String sql = "INSERT INTO contratos (propiedad_id, propietario_id, inquilino_id, fecha_inicio, " +
                    "fecha_fin, monto_mensual, firmado_propietario, firmado_inquilino, estado) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, contrato.getPropiedadId());
            ps.setInt(2, contrato.getPropietarioId());
            ps.setInt(3, contrato.getInquilinoId());
            ps.setDate(4, Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(5, Date.valueOf(contrato.getFechaFin()));
            ps.setBigDecimal(6, contrato.getMontoMensual());
            ps.setBoolean(7, contrato.isFirmadoPropietario());
            ps.setBoolean(8, contrato.isFirmadoInquilino());
            ps.setString(9, contrato.getEstado());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        LOGGER.info("Contrato creado con ID: " + id);
                        return id;
                    }
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al crear contrato", e);
        }
        return 0;
    }

    /**
     * Obtener contrato por ID
     */
    public Contrato obtenerPorId(int id) {
        String sql = "SELECT * FROM contratos WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearContrato(rs);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener contrato por ID: " + id, e);
        }
        return null;
    }

    /**
     * Listar todos los contratos
     */
    public List<Contrato> listar() {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearContrato(rs));
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar contratos", e);
        }
        return lista;
    }

    /**
     * Listar contratos por propietario
     */
    public List<Contrato> listarPorPropietario(int propietarioId) {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos WHERE propietario_id = ? ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propietarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearContrato(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar contratos por propietario: " + propietarioId, e);
        }
        return lista;
    }

    /**
     * Listar contratos por inquilino
     */
    public List<Contrato> listarPorInquilino(int inquilinoId) {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos WHERE inquilino_id = ? ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, inquilinoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearContrato(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar contratos por inquilino: " + inquilinoId, e);
        }
        return lista;
    }

    /**
     * Listar contratos por estado
     */
    public List<Contrato> listarPorEstado(String estado) {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos WHERE estado = ? ORDER BY creado_en DESC";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearContrato(rs));
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al listar contratos por estado: " + estado, e);
        }
        return lista;
    }

    /**
     * Obtener contrato activo de una propiedad
     */
    public Contrato obtenerContratoActivoPorPropiedad(int propiedadId) {
        String sql = "SELECT * FROM contratos WHERE propiedad_id = ? AND estado = 'activo'";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, propiedadId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearContrato(rs);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener contrato activo por propiedad: " + propiedadId, e);
        }
        return null;
    }

    /**
     * Firmar contrato (propietario)
     */
    public boolean firmarPropietario(int contratoId) {
        String sql = "UPDATE contratos SET firmado_propietario = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, true);
            ps.setInt(2, contratoId);

            boolean firmado = ps.executeUpdate() > 0;
            if (firmado) {
                LOGGER.info("Contrato firmado por propietario: " + contratoId);
            }
            return firmado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al firmar contrato (propietario): " + contratoId, e);
            return false;
        }
    }

    /**
     * Firmar contrato (inquilino)
     */
    public boolean firmarInquilino(int contratoId) {
        String sql = "UPDATE contratos SET firmado_inquilino = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, true);
            ps.setInt(2, contratoId);

            boolean firmado = ps.executeUpdate() > 0;
            if (firmado) {
                LOGGER.info("Contrato firmado por inquilino: " + contratoId);
            }
            return firmado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al firmar contrato (inquilino): " + contratoId, e);
            return false;
        }
    }

    /**
     * Cambiar estado de contrato
     */
    public boolean cambiarEstado(int contratoId, String nuevoEstado) {
        String sql = "UPDATE contratos SET estado = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, contratoId);

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Estado de contrato " + contratoId + " cambiado a: " + nuevoEstado);
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al cambiar estado de contrato: " + contratoId, e);
            return false;
        }
    }

    /**
     * Actualizar contrato
     */
    public boolean actualizar(Contrato contrato) {
        String sql = "UPDATE contratos SET fecha_inicio = ?, fecha_fin = ?, monto_mensual = ?, " +
                    "firmado_propietario = ?, firmado_inquilino = ?, estado = ? WHERE id = ?";
        
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(2, Date.valueOf(contrato.getFechaFin()));
            ps.setBigDecimal(3, contrato.getMontoMensual());
            ps.setBoolean(4, contrato.isFirmadoPropietario());
            ps.setBoolean(5, contrato.isFirmadoInquilino());
            ps.setString(6, contrato.getEstado());
            ps.setInt(7, contrato.getId());

            boolean actualizado = ps.executeUpdate() > 0;
            if (actualizado) {
                LOGGER.info("Contrato actualizado: " + contrato.getId());
            }
            return actualizado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar contrato: " + contrato.getId(), e);
            return false;
        }
    }

    /**
     * Eliminar contrato
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM contratos WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            boolean eliminado = ps.executeUpdate() > 0;
            if (eliminado) {
                LOGGER.info("Contrato eliminado con ID: " + id);
            }
            return eliminado;

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar contrato: " + id, e);
            return false;
        }
    }

    /**
     * Contar contratos por estado
     */
    public int contarPorEstado(String estado) {
        String sql = "SELECT COUNT(*) FROM contratos WHERE estado = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al contar contratos por estado: " + estado, e);
        }
        return 0;
    }

    /**
     * Verificar si contrato está completamente firmado
     */
    public boolean estaCompletamenteFirmado(int contratoId) {
        String sql = "SELECT firmado_propietario, firmado_inquilino FROM contratos WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, contratoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("firmado_propietario") && rs.getBoolean("firmado_inquilino");
                }
            }

        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al verificar firmas del contrato: " + contratoId, e);
        }
        return false;
    }

    /**
     * Método auxiliar para mapear ResultSet a objeto Contrato
     */
    private Contrato mapearContrato(ResultSet rs) throws SQLException {
        return new Contrato(
            rs.getInt("id"),
            rs.getInt("propiedad_id"),
            rs.getInt("propietario_id"),
            rs.getInt("inquilino_id"),
            rs.getDate("fecha_inicio").toLocalDate(),
            rs.getDate("fecha_fin").toLocalDate(),
            rs.getBigDecimal("monto_mensual"),
            rs.getBoolean("firmado_propietario"),
            rs.getBoolean("firmado_inquilino"),
            rs.getString("estado"),
            rs.getTimestamp("creado_en")
        );
    }
}

