package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class MedicoDto implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private DireccionDto direccion;
    private List<ConsultaDto> consultas;
    private String fechaNacimientoFormat;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public DireccionDto getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDto direccion) {
        this.direccion = direccion;
    }

    public List<ConsultaDto> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaDto> consultas) {
        this.consultas = consultas;
    }

    public String getFechaNacimientoFormat() {
        return fechaNacimientoFormat;
    }

    public void setFechaNacimientoFormat(String fechaNacimientoFormat) {
        this.fechaNacimientoFormat = fechaNacimientoFormat;
    }
}
