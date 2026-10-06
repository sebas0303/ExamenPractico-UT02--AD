package ut2.ficheros.examen;

/**
 *
 * @author arquinigo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Examen {
    public static void main(String[] args) {
        //cargamos los archivos necesarios, de entrada y salida
        File ficheroOrigen = new File("estado_sistemas.txt");
        File ficheroDestino = new File("objetivos_sabotaje.xml");
        
        try {
            //Inicializar la estructura para construir el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Creamos el elemento raíz del XML
            Element raiz = doc.createElement("objetivos");
            //agregamos el elemento objetivos como hijo de la raiz xml
            doc.appendChild(raiz);
            
            //indicador si es que hay objetivos criticos
            boolean hayObjetivosCriticos = false;

            //lectura del archivo estado_sistemas.txt
            try (BufferedReader br = new BufferedReader(new FileReader(ficheroOrigen))) {
                //separamos linea por linea para identificar a cada nave
                String linea;
                
                while ((linea = br.readLine()) != null) {
                    //seccionamos cada nave con su respectivo componente y estado en un array para cada nave
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String objetivo = partes[0].trim();
                        String componente = partes[1].trim();
                        String estado = partes[2].trim();
                        
                        //filtrado por estado crítico
                        if (estado.equalsIgnoreCase("Critico")) {
                            hayObjetivosCriticos = true;
                            
                            // Crear la estructura de nodos XML correspondientes
                            Element eSistema = doc.createElement("sistema");
                            eSistema.setAttribute("objetivo", objetivo);
                            
                            Element eComp = doc.createElement("componente");
                            eComp.appendChild(doc.createTextNode(componente));
                            
                            eSistema.appendChild(eComp);
                            raiz.appendChild(eSistema);
                        }
                    }
                }
            }
            
            // si se encontraron sistemas críticos, guardamos el archivo XML
            if (hayObjetivosCriticos) {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
                
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(ficheroDestino);
                
                transformer.transform(source, result);
                System.out.println("✔ Fichero 'objetivos_sabotaje.xml' generado correctamente para los cazas rebeldes.");
            } else {
                System.out.println("ℹ No se detectaron anomalías críticas en los sistemas imperiales.");
            }

            //Control de excepciones,
        } catch (FileNotFoundException e) {
            System.err.println("Error: Transmisión imperial no encontrada.");
        } catch (IOException e) {
            System.err.println("❌ Error general de Entrada/Salida: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado en el procesamiento XML: " + e.getMessage());
        }
    }
}