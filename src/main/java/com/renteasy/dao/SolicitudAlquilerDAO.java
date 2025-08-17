package com.renteasy.dao;


import com.renteasy.database.ConexionBD;
import com.renteasy.models.SolicitudAlquiler;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar las operaciones de base de datos para solicitudes de alquiler
 */
public class SolicitudAlquilerDAO {

    /**
     * Crear una nueva solicitud de alquiler
     * @param solicitud La solicitud a crear
     * @return true si se creó exitosamente, false en caso contrario
     */
    public boolean crearSolicitud(SolicitudAlquiler solicitud) {
        String sql = "INSERT INTO solicitudes_alquiler (inquilino_id, propiedad_id, mensaje) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, solicitud.getInquilinoId());
            stmt.setInt(2, solicitud.getPropiedadId());
            stmt.setString(3, solicitud.getMensaje());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al crear solicitud: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtener una solicitud por ID
     * @param id ID de la solicitud
     * @return La solicitud encontrada o null si no existe
     */
    public SolicitudAlquiler obtenerSolicitudPorId(int id) {
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE sa.id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearResultadoAObjeto(rs);
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitud: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Obtener todas las solicitudes
     * @return Lista de todas las solicitudes
     */
    public List<SolicitudAlquiler> obtenerTodasLasSolicitudes() {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                solicitudes.add(mapearResultadoAObjeto(rs));
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes: " + e.getMessage());
        }
        
        return solicitudes;
    }

    /**
     * Obtener solicitudes por inquilino
     * @param inquilinoId ID del inquilino
     * @return Lista de solicitudes del inquilino
     */
    public List<SolicitudAlquiler> obtenerSolicitudesPorInquilino(int inquilinoId) {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE sa.inquilino_id = ? " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, inquilinoId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                solicitudes.add(mapearResultadoAObjeto(rs));
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes por inquilino: " + e.getMessage());
        }
        
        return solicitudes;
    }

    /**
     * Obtener solicitudes por propiedad
     * @param propiedadId ID de la propiedad
     * @return Lista de solicitudes para la propiedad
     */
    public List<SolicitudAlquiler> obtenerSolicitudesPorPropiedad(int propiedadId) {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE sa.propiedad_id = ? " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, propiedadId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                solicitudes.add(mapearResultadoAObjeto(rs));
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes por propiedad: " + e.getMessage());
        }
        
        return solicitudes;
    }

    /**
     * Obtener solicitudes por estado
     * @param estado Estado de las solicitudes (pendiente, aceptada, rechazada)
     * @return Lista de solicitudes con el estado especificado
     */
    public List<SolicitudAlquiler> obtenerSolicitudesPorEstado(String estado) {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE sa.estado = ? " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                solicitudes.add(mapearResultadoAObjeto(rs));
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes por estado: " + e.getMessage());
        }
        
        return solicitudes;
    }

    /**
     * Obtener solicitudes de propiedades de un propietario específico
     * @param propietarioId ID del propietario
     * @return Lista de solicitudes para las propiedades del propietario
     */
    public List<SolicitudAlquiler> obtenerSolicitudesPorPropietario(int propietarioId) {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE p.propietario_id = ? " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, propietarioId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                solicitudes.add(mapearResultadoAObjeto(rs));
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes por propietario: " + e.getMessage());
        }
        
        return solicitudes;
    }

    /**
     * Actualizar el estado de una solicitud
     * @param solicitudId ID de la solicitud
     * @param nuevoEstado Nuevo estado (pendiente, aceptada, rechazada)
     * @return true si se actualizó exitosamente, false en caso contrario
     */
    public boolean actualizarEstadoSolicitud(int solicitudId, String nuevoEstado) {
        String sql = "UPDATE solicitudes_alquiler SET estado = ? WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, solicitudId);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al actualizar estado de solicitud: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualizar una solicitud completa
     * @param solicitud Solicitud con los datos actualizados
     * @return true si se actualizó exitosamente, false en caso contrario
     */
    public boolean actualizarSolicitud(SolicitudAlquiler solicitud) {
        String sql = "UPDATE solicitudes_alquiler SET mensaje = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, solicitud.getMensaje());
            stmt.setString(2, solicitud.getEstado());
            stmt.setInt(3, solicitud.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al actualizar solicitud: " + e.getMessage());
            return false;
        }
    }

    /**
     * Eliminar una solicitud
     * @param solicitudId ID de la solicitud a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    public boolean eliminarSolicitud(int solicitudId) {
        String sql = "DELETE FROM solicitudes_alquiler WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, solicitudId);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al eliminar solicitud: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verificar si ya existe una solicitud del mismo inquilino para la misma propiedad
     * @param inquilinoId ID del inquilino
     * @param propiedadId ID de la propiedad
     * @return true si ya existe una solicitud, false en caso contrario
     */
    public boolean existeSolicitud(int inquilinoId, int propiedadId) {
        String sql = "SELECT COUNT(*) FROM solicitudes_alquiler WHERE inquilino_id = ? AND propiedad_id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, inquilinoId);
            stmt.setInt(2, propiedadId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al verificar existencia de solicitud: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Contar solicitudes por estado
     * @param estado Estado a contar
     * @return Número de solicitudes con el estado especificado
     */
    public int contarSolicitudesPorEstado(String estado) {
        String sql = "SELECT COUNT(*) FROM solicitudes_alquiler WHERE estado = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al contar solicitudes: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Alias para obtenerSolicitudPorId - Compatibilidad con controladores
     */
    public SolicitudAlquiler obtenerPorId(int id) {
        return obtenerSolicitudPorId(id);
    }
    
    /**
     * Alias para obtenerTodasLasSolicitudes - Compatibilidad con controladores
     */
    public List<SolicitudAlquiler> obtenerTodas() {
        return obtenerTodasLasSolicitudes();
    }
    
    /**
     * Alias para obtenerSolicitudesPorPropiedad - Compatibilidad con controladores
     */
    public List<SolicitudAlquiler> obtenerPorPropiedad(int propiedadId) {
        return obtenerSolicitudesPorPropiedad(propiedadId);
    }
    
    /**
     * Obtener solicitudes por estado
     */
    public List<SolicitudAlquiler> obtenerPorEstado(String estado) {
        List<SolicitudAlquiler> solicitudes = new ArrayList<>();
        String sql = "SELECT sa.*, u.nombre as nombre_inquilino, u.email as email_inquilino, " +
                    "p.titulo as titulo_propiedad, p.direccion as direccion_propiedad " +
                    "FROM solicitudes_alquiler sa " +
                    "JOIN usuarios u ON sa.inquilino_id = u.id " +
                    "JOIN propiedades p ON sa.propiedad_id = p.id " +
                    "WHERE sa.estado = ? " +
                    "ORDER BY sa.fecha_solicitud DESC";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    solicitudes.add(mapearResultadoAObjeto(rs));
                }
            }
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al obtener solicitudes por estado: " + e.getMessage());
        }
        
        return solicitudes;
    }
    
    /**
     * Actualizar una solicitud existente
     */
    public boolean actualizar(SolicitudAlquiler solicitud) {
        String sql = "UPDATE solicitudes_alquiler SET inquilino_id = ?, propiedad_id = ?, " +
                    "mensaje = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, solicitud.getInquilinoId());
            stmt.setInt(2, solicitud.getPropiedadId());
            stmt.setString(3, solicitud.getMensaje());
            stmt.setString(4, solicitud.getEstado());
            stmt.setInt(5, solicitud.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al actualizar solicitud: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Actualizar estado de una solicitud (método de compatibilidad)
     * @param solicitudId ID de la solicitud
     * @param nuevoEstado Nuevo estado
     * @return true si se actualizó exitosamente
     */
    public boolean actualizarEstado(int solicitudId, String nuevoEstado) {
        return actualizarEstadoSolicitud(solicitudId, nuevoEstado);
    }
    
    /**
     * Eliminar una solicitud
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM solicitudes_alquiler WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error al eliminar solicitud: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método auxiliar para mapear un ResultSet a un objeto SolicitudAlquiler
     * @param rs ResultSet de la consulta
     * @return Objeto SolicitudAlquiler mapeado
     * @throws SQLException Si ocurre un error al acceder al ResultSet
     */
    private SolicitudAlquiler mapearResultadoAObjeto(ResultSet rs) throws SQLException {
        SolicitudAlquiler solicitud = new SolicitudAlquiler();
        
        solicitud.setId(rs.getInt("id"));
        solicitud.setInquilinoId(rs.getInt("inquilino_id"));
        solicitud.setPropiedadId(rs.getInt("propiedad_id"));
        solicitud.setMensaje(rs.getString("mensaje"));
        solicitud.setEstado(rs.getString("estado"));
        solicitud.setFechaSolicitud(rs.getTimestamp("fecha_solicitud"));
        
        // Campos adicionales si están disponibles
        try {
            solicitud.setNombreInquilino(rs.getString("nombre_inquilino"));
            solicitud.setEmailInquilino(rs.getString("email_inquilino"));
            solicitud.setTituloPropiedad(rs.getString("titulo_propiedad"));
            solicitud.setDireccionPropiedad(rs.getString("direccion_propiedad"));
        } catch (SQLException e) {
            // Los campos adicionales no están disponibles en esta consulta
        }
        
        return solicitud;
    }
}
