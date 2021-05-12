package co.edu.unicundi.ejb.dtos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class ConsultaDto {
    private Integer id;   
    private List<DetalleConsultaDto> detallesConsulta;
    private String fechaFormat;
    private MedicoDto medico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<DetalleConsultaDto> getDetallesConsulta() {
        return detallesConsulta;
    }

    public void setDetallesConsulta(List<DetalleConsultaDto> detallesConsulta) {
        this.detallesConsulta = detallesConsulta;
    }

    public String getFechaFormat() {
        return fechaFormat;
    }

    public void setFechaFormat(String fechaFormat) {
        this.fechaFormat = fechaFormat;
    }

    @JsonBackReference
    public MedicoDto getMedico() {
        return medico;
    }

    public void setMedico(MedicoDto medico) {
        this.medico = medico;
    }
}
