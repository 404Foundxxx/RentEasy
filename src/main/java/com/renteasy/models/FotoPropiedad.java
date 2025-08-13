package com.renteasy.models;

public class FotoPropiedad {
    private int id;
    private int propiedadId;
    private String urlFoto;
    
    // Constructor vacío
    public FotoPropiedad() {}
    
    // Constructor completo
    public FotoPropiedad(int id, int propiedadId, String urlFoto) {
        this.id = id;
        this.propiedadId = propiedadId;
        this.urlFoto = urlFoto;
    }
    
    // Constructor sin ID (para inserciones)
    public FotoPropiedad(int propiedadId, String urlFoto) {
        this.propiedadId = propiedadId;
        this.urlFoto = urlFoto;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPropiedadId() {
        return propiedadId;
    }
    
    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
    
    public String getUrlFoto() {
        return urlFoto;
    }
    
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    @Override
    public String toString() {
        return "FotoPropiedad{" +
                "id=" + id +
                ", propiedadId=" + propiedadId +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}
