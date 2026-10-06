package binding;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "sistema")
@XmlType(propOrder = {"componente"})
public class SistemaCritico implements Serializable {

    private String nombre;     // Irá como atributo
    private String componente; // Irá como elemento hijo

    // Constructor vacío obligatorio
    public SistemaCritico() {
    }

    public SistemaCritico(String nombre, String componente) {
        this.nombre = nombre;
        this.componente = componente;
    }

    @XmlAttribute(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "componente")
    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }
}