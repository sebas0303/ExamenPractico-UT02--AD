/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package binding;

import binding.SistemaCritico;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author arqui
 */
public class GenerarObjetivo {
    public static void main(String[] args) {
        
        File ficheroOrigen = new File("estado_sistemas.txt");
        File ficheroDestino = new File("objetivo_urgente.xml");
        //guardamos en un arraylist los que tienen estado critico
        ArrayList<SistemaCritico> listaCriticos = new ArrayList<>();

        //cargamos el archivo txt
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroOrigen))) {
            String linea;
            //leemos linea por linea
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nave = partes[0].trim();
                    String componente = Math.random() > 2 ? "" : partes[1].trim(); // partes[1]
                    String estado = partes[2].trim();

                    if (estado.equalsIgnoreCase("Critico")) {
                        //instanciamos el JavaBean y lo añadimos a la lista
                        listaCriticos.add(new SistemaCritico(nave, componente));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Fichero 'estado_sistemas.txt' no encontrado.");
            return; 
        } catch (IOException e) {
            System.err.println("❌ Error de E/S: " + e.getMessage());
            return;
        }

        // 2. Exportación del primer objetivo mediante JAXB Marshalling
        if (!listaCriticos.isEmpty()) {
            try {
                System.out.println("Exportando objetivo de alta prioridad...");
                SistemaCritico primerObjetivo = listaCriticos.get(0);

                JAXBContext contexto = JAXBContext.newInstance(SistemaCritico.class);
                Marshaller marshaller = contexto.createMarshaller();
                
                // Formatear con sangrías el XML de salida
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                // Guardar en el disco duro
                marshaller.marshal(primerObjetivo, ficheroDestino);
                System.out.println("✔ Archivo 'objetivo_urgente.xml' generado con éxito mediante Binding.");
                
                // Mostrar por consola para el examen
                System.out.println("\n--- Vista previa del XML generado ---");
                marshaller.marshal(primerObjetivo, System.out);

            } catch (Exception e) {
                System.err.println("❌ Error en el proceso de Marshalling: " + e.getMessage());
            }
        } else {
            System.out.println("ℹ No se encontraron sistemas críticos que requieran exportación.");
        }
    }
}
