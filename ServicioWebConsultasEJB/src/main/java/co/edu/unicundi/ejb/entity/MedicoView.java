package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "vw_cantidad_consultas_medico")
@NamedQueries({
    @NamedQuery(name = "MedicoView.listar", query = "SELECT m FROM MedicoView m"),
    @NamedQuery(name = "MedicoView.contar", query = "SELECT COUNT(m) FROM MedicoView m")
})
public class MedicoView implements Serializable {
    @Id
    private Integer id;
    
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "cantidad_consultas")
    private Integer cantidadConsultas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(Integer cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }
    
}
