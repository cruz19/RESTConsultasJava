package co.edu.unicundi.ejb.dtos;

import java.util.List;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class ConsultaDto {
    private Integer id;   
    private List<DetalleConsultaDto> detallesConsulta;
    private String fechaStr;
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

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    public MedicoDto getMedico() {
        return medico;
    }

    public void setMedico(MedicoDto medico) {
        this.medico = medico;
    }
}
