package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
public class ExamenInfoDto extends ExamenDto implements Serializable {
    private String infoAdicional;

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
