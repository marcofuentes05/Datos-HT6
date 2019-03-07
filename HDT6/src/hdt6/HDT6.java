/*
 *        Algoritmos y Estructuras de Datos - Sección 10
 * El main lee un archivo txt con todas las cartas disponibles y las guarda en 
 * un hashmap. El usuario determina el tipo de map que desea para su mazo.  
 * Luego el usuario puede agregarlas a su mazo, ver el tipo de una carta 
 * determinajada, ver el nombre y tipo de todas las cartas existentes, entre otras
 * varias acciones
 */
//package hdt6;

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
        int estiloMap;
        String tipo, opcion="0";
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
            String k = arr[0].toString();
            String v = arr[1].toString();
            //Se agrega el nombre y tipo al hashmap de cartas totales como key y value
            cartas.put(k,v);
        }
		//Ya se comprobo que lee el archivo correctamente
        //System.out.println(cartas);
        
        //Se imprime el menu de tipos de maps para crear el mazo
        System.out.println("    B I E N V E N I D O\nIngrese el tipo de Map que desea utilizar para el mazo de sus cartas:\n1. HashMap\n2. TreeMap\n3. Linked HashMap");
        //Programacion defensiva y creacion del mazo segun la eleccion de Map
        estiloMap =scan.nextInt();
        /*try{
            estiloMap =scan.nextInt();
            if (estiloMap > 3){
                System.out.print("Ingresaste un numero mayor a 3, se ejecutara como un Hasmap");
                estiloMap=1;
            }
        }catch(InputMismatchException e){
            estiloMap = 1;
            System.out.print("Los errores pasan, al parecer no ingresaste una opcion correcta! Se ejecutara con un HashMap");
        }*/
        mazo = fabrica.MapCreator(estiloMap);
        String menu = "\nElige una opcion:\n1. Agregar una carta al mazo\n2. Mostrar tipo de una carta especifica\n3. Mostrar nombre, tipo y cantidad de cada carta en el mazo\n4. Mostrar nombre, tipo y cantidad de cada carta en el mazo, ordenadas por tipo\n5. Mostrar el nombre y tipo de todas las cartas existentes\n6. Mostrar nombre y tipo de todas las cartas ordenadas por tipo\n7. Salir\n";
        System.out.print(menu);
        opcion = scan.next();
        while (!opcion.equals("7")){
            /*Se muestra menu de opciones a realizar con las cartas y se lee
            String menu = "\nElige una opcion:\n1. Agregar una carta al mazo\n2. Mostrar tipo de una carta especifica\n3. Mostrar nombre, tipo y cantidad de cada carta en el mazo\n4. Mostrar nombre, tipo y cantidad de cada carta en el mazo, ordenadas por tipo\n5. Mostrar el nombre y tipo de todas las cartas existentes\n6. Mostrar nombre y tipo de todas las cartas ordenadas por tipo\n7. Salir\n";
            System.out.print(menu);
            opcion = scan.next();*/
            /*try{
                opcion = scan.nextInt();
            } catch(InputMismatchException e){
                System.out.print("Ingresaste una opcion incorrecta, se tomara como la primera opcion");
                opcion = 1;
            }*/
            if(opcion.equals("1")){
                System.out.println("Eleccion  -->   Agregar una carta al mazo");
                System.out.println("Ingresa el nombre de la carta que deseas agregar");
				Scanner sc = new Scanner(System.in);
				String nombre = sc.nextLine();
                if(cartas.containsKey(nombre)){
                    tipo = cartas.get(nombre);
                    mazo.put(nombre, tipo);
                    cartas.remove(nombre);
                    System.out.print("Carta agregada con exito");
                } else {
                    System.out.print("Esa carta no existe en el sistema...");
                }
            }else if (opcion.equals("2")){
				System.out.println("Tipo de Carta  -->   Ingrese el nombre de la carta que desea");
				Scanner sc = new Scanner(System.in);
				String nombre = sc.nextLine();
				if (cartas.containsKey(nombre)){
					System.out.println("Esa carta es de tipo: "+ cartas.get(nombre));
				}else{
					System.out.println("Esa carta no existe en el sistema...");
				}
			}else if(opcion.equals("3")){
				if (!mazo.isEmpty()){
					//TODO contar la carta
					mazo.forEach((k,v) -> System.out.println(k + " de tipo "+ v + " esta  veces"));	
				}else{
					System.out.println("El mazo del usuario esta vacio...");
				}
				
			}else if(opcion.equals("4")){
				if (!mazo.isEmpty()){
					//TODO:Sorts and counting...			
				}else{
					System.out.println("El mazo del usuario esta vacio...");
				}
			}else if (opcion.equals("5")){
				if (!cartas.isEmpty()){
					cartas.forEach((k,v) -> System.out.println(k+" es de tipo "+v));
				}else{
					System.out.println("No hay cartas en el sistema...");
				}
			}else if(opcion.equals("6")){
				if (!cartas.isEmpty()){
					/**Map<String, String> ordenado = sortByValue(cartas);
					ordenado.forEach((k,v) -> System.out.println(k+" es de tipo "+v));*/
				}else{
					System.out.println("No hay cartas en el sistema...");
				}
			}
			
            //Se muestra menu de opciones a realizar con las cartas y se lee
            System.out.print(menu);
            opcion = scan.next();
                   
             
        }
        System.out.println("Hasta luego!");
        
        
    }
	
	
	/* Metodo para ordenar Map
	*  Obtenido de Mkyong.com
	*/
	private static Map<String, String> sortByValue(Map<String, String> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, String>> list =
                new LinkedList<Map.Entry<String, String>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
}
