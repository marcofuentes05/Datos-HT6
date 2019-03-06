/*
 *        Algoritmos y Estructuras de Datos - Sección 10
 * El main lee un archivo txt con todas las cartas disponibles y las guarda en 
 * un hashmap. El usuario determina el tipo de map que desea para su mazo.  
 * Luego el usuario puede agregarlas a su mazo, ver el tipo de una carta 
 * determinada, ver el nombre y tipo de todas las cartas existentes, entre otras
 * varias acciones
 */
package hdt6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 *
 * @author Camila y Marco
 */
public class HDT6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // A T R I B U T O S
        Map<String, String> cartas = new HashMap<>();
        Map<String, String> mazo;
        ArrayList<String> lectura = new ArrayList<>();
        
        //Se lee el archivo txt y se almacena su data en 'lectura'
         try{
            Stream<String> lines = Files.lines(
                    Paths.get("cards_desc.txt"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(c -> lectura.add(c));
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        
         
        for (int i=0; i<=(lectura.size()-1); i++){
            //Se itera la lectura y cada elemento se divide en dos (nombre-tipo)
            String item = lectura.get(i);
            //System.out.println(item);
            String[] arr = item.split("[|]");
            String k = arr[0];
            String v = arr[1];
            //Se agrega el nombre y tipo al hashmap de cartas totales como key y value
            cartas.put(k,v);
        }
        //Se imprime el Hashmap de todas las cartas leidas
        for (Entry<String, String> todas: cartas.entrySet()){
            String clave = todas.getKey();
            String valor = todas.getKey();
            System.out.println("Nombre de carta: " +clave + " --> Tipo: " + valor);
        }
       
    }
    
}
