package ut2.ficheros.examen;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author arquinigo
 */
@XmlRootElement
public class SistemaCritico implements Serializable{
    private String instalacionONave;
    private String componente;
    private String estado;

    public SistemaCritico() {
    }

    public SistemaCritico(String instalacionONave, String componente, String estado) {
        this.instalacionONave = instalacionONave;
        this.componente = componente;
        this.estado = estado;
    }

    @XmlElement
    public String getInstalacionONave() {
        return instalacionONave;
    }

    public void setInstalacionONave(String instalacionONave) {
        this.instalacionONave = instalacionONave;
    }

    @XmlElement
    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
