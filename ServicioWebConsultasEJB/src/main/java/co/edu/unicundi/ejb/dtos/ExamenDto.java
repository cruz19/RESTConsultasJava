package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
public class ExamenDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String nombre;
    
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
