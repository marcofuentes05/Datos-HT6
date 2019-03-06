/*
 * Algoritmos y Estructuras de Datos - Sección 10
 * Esta clase funciona como Factory de Maps que el user
 * seleccione en el main para guardar sus cartas
 */
package hdt6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Camila y Marco
 */
public class MapFactory{
    
    public Map MapCreator(int opcion){
        switch (opcion) {
            case 1:
                //Si elige la primera opcion se crea un Map de tipo Hash
                return new HashMap<String, String>();
            case 2:
                //Si elige la segunda opcion se crea un Map de tipo Tree
                return new TreeMap<String, String>();
            default:
                //Si elige la tercera opcion se crea un Map de tipo Linked Hash
                return new LinkedHashMap<String, String>();
        }
        
    }
    
}
