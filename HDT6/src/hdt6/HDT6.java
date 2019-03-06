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
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
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
        MapFactory fabrica = new MapFactory();
        ArrayList<String> lectura = new ArrayList<>();
        int estiloMap, opcion=0;
        String tipo, nombre;
        Scanner scan = new Scanner(System.in);
        
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
        
        //Se imprime el menu de tipos de maps para crear el mazo
        System.out.println("    B I E N V E N I D O\nIngrese el tipo de Map que desea utilizar para el mazo de sus cartas:\n1. HashMap\n2. TreeMap\n3. Linked HashMap");
        //Programacion defensiva y creacion del mazo segun la eleccion de Map
        try{
            estiloMap =scan.nextInt();
            if (estiloMap > 3){
                System.out.print("Ingresaste un numero mayor a 3, se ejecutara como un Hasmap");
                estiloMap=1;
            }
        }catch(InputMismatchException e){
            estiloMap = 1;
            System.out.print("Los errores pasan, al parecer no ingresaste una opcion correcta! Se ejecutara con un HashMap");
        }
        mazo = fabrica.MapCreator(estiloMap);
        while (opcion != 7){
            //Se muestra menu de opciones a realizar con las cartas y se lee
            String menu = "\nElige una opcion:\n1. Agregar una carta al mazo\n2. Mostrar tipo de una carta especifica\n3. Mostrar nombre, tipo y cantidad de cada carta en el mazo\n4. Mostrar nombre, tipo y cantidad de cada carta en el mazo, ordenadas por tipo\n5. Mostrar el nombre y tipo de todas las cartas existentes\n6. Mostrar nombre y tipo de todas las cartas ordenadas por tipo\n7. Salir";
            System.out.println(menu);
            try{
                opcion = scan.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Ingresaste una opcion incorrecta, se tomara como la primera opcion");
                opcion = 1;
            }
            switch(opcion){
                case 1:
                    System.out.println("Eleccion  -->   Agregar una carta al mazo");
                    System.out.println("Ingresa el nombre de la carta que deseas agregar");
                    nombre = scan.next();
                    if(cartas.containsKey(nombre)){
                        tipo = cartas.get(nombre);
                        mazo.put(nombre, tipo);
                        cartas.remove(nombre);
                        System.out.println("Carta agregada con exito");
                    } else {
                        System.out.println("No hay ese nombre de carta");
                    }
                   
                    break;
                
                case 2:
                    break;
                
                case 3:
                    System.out.println("HOla");
                    break;
                    
                case 4:
                    break;
                    
                case 5:
                    break;
                    
                case 6:
                    break;
                    
                case 7:
                    System.out.println("Byeee, Ciao, adios!");
                    break;
            }
        }
        
        
        
    }
    
}
