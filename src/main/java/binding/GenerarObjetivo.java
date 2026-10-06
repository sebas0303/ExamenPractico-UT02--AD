/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package binding;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;

/**
 *
 * @author arqui
 */
public class GenerarObjetivo {
    public static void main(String[] args) {

        try {

            // Creamos un personaje
            SistemaCritico personaje = new SistemaCritico(
                    "preuba",
                    "prueba",
                    "prueba"
            );

            // Creamos el contexto JAXB para nuestra clase
            JAXBContext contexto = JAXBContext.newInstance(SistemaCritico.class);

            // Creamos el objeto que realizará el Marshalling
            Marshaller marshaller = contexto.createMarshaller();

            // Indicamos que queremos que el XML tenga formato
            marshaller.setProperty(
                    Marshaller.JAXB_FORMATTED_OUTPUT,
                    true
            );

            // Generamos el archivo rebelde.xml
            File archivo = new File("rebelde.xml");

            marshaller.marshal(personaje, archivo);

            System.out.println("Archivo rebelde.xml generado correctamente.");

        } catch (JAXBException e) {
            System.out.println("Error al generar el archivo XML.");
            e.getMessage();
        }
    }
}
