
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
import java.util.*;
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
        Map<String, String> cartas;
        ArrayList<String> nombres = new ArrayList<>(), cartasU = new ArrayList<>();
        Map<String, String> mazo;
        MapFactory fabrica = new MapFactory();
        ArrayList<String> lectura = new ArrayList<>();
        int estiloMap, seleccion = 0;
        String tipo, opcion="0";
        Scanner scan = new Scanner(System.in);

        
        //Se imprime el menu de tipos de maps para crear el mazo
        System.out.println("    B I E N V E N I D O  \n\n\nIngrese el tipo de Map que desea utilizar para los mazos de las cartas:\n1. HashMap\n2. TreeMap\n3. Linked HashMap");
        //Programacion defensiva y creacion del mazo segun la eleccion de Map
        estiloMap =scan.nextInt();
        mazo = fabrica.MapCreator(estiloMap);
        cartas = fabrica.MapCreator(estiloMap);
        if (estiloMap>3){
            System.out.println("Opcion incorrecta!! Se ejecutara como un Linked Hashmap");
        }
        //Se lee el archivo txt y se almacena su data en 'lectura'
        try{
            Stream<String> lines = Files.lines(
                    Paths.get("cards_desc.txt"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(c -> lectura.add(c));
            for (int i=0; i<=(lectura.size()-1); i++){
                //Se itera la lectura y cada elemento se divide en dos (nombre-tipo)
                String item = lectura.get(i);
                //System.out.println(item);
                String[] arr = item.split("[|]");
                String k = arr[0];
                nombres.add(arr[0]);
                String v = arr[1];
                //Se agrega el nombre y tipo al hashmap de cartas totales como key y value
                cartas.put(k,v);
            }
            //Ya se comprobo que lee el archivo correctamente
            //System.out.println(cartas);
       
            String menu = "\n\nElige una opcion:\n1. Agregar una carta a tu mazo\n2. Mostrar tipo de una carta especifica del mazo general\n3. Mostrar nombre, tipo y cantidad de cada carta en tu mazo\n4. Mostrar nombre, tipo y cantidad de cada carta en tu mazo, ordenadas por tipo\n5. Mostrar el nombre y tipo de todas las cartas existentes para elegir\n6. Mostrar nombre y tipo de todas las cartas en el mazo general ordenadas por tipo\n7. Salir\n";
            System.out.print(menu+"\n");
            //opcion = scan.next();
            seleccion = scan.nextInt();
            
            // EMPIEZA EL CASE
            Scanner sc = new Scanner(System.in);
            String nombre = "";
            while (seleccion != 7){
                switch(seleccion){
                    case(1):
                        System.out.println("\nAgregar  -->   Agregar una carta al mazo");
                        System.out.println("Ingresa el nombre de la carta que deseas agregar\n");
                        nombre = sc.nextLine();
                        if(cartas.containsKey(nombre)){
                            tipo = cartas.get(nombre);
                            mazo.put(nombre, tipo);
                            cartas.remove(nombre);
                            cartasU.add(nombre);
                            System.out.print("\nCarta agregada con exito!");
                        } else {
                            System.out.print("\nEsa carta no existe en el sistema...");
                        }
                    break;
                        
                    case(2):
                        System.out.println("\nTipo de Carta  -->   Ingrese el nombre de la carta del mazo general de la que desea saber el tipo\n");
                        nombre = sc.nextLine();
                        if (cartas.containsKey(nombre)){
                            System.out.println("\nLa carta " + nombre + " es de tipo: " + cartas.get(nombre));
                        }else{
                            System.out.println("\nEsa carta no existe en el sistema...");
                        }
                    break;
                    case(3):
                        if (!mazo.isEmpty()){
                            System.out.println("\nVer tu mazo  -->   Mostrar el nombre, tipo y cantidad de cartas en el mazo personal\n");
                            int monstruos = 0,trampas = 0, hechizos = 0;
                            for (int i =0;i< cartasU.size();i++){
                                if (mazo.get(cartasU.get(i)).equals("Monstruo")){
                                    monstruos++;
                                }else if(mazo.get(cartasU.get(i)).equals("Hechizo")){
                                    hechizos++;
                                }else if(mazo.get(cartasU.get(i)).equals("Trampa")){
                                    trampas++;
                                }
                            }
                            System.out.println("Existen "+monstruos+" monstruos...");
                            System.out.println("Existen "+hechizos+" hechizos...");
                            System.out.println("Existen "+trampas+" trampas... \n\n\n");
                            System.out.println("Estas son las cartas: ");
                            mazo.forEach((k,v) -> System.out.println("\n"+ k + " ----TIPO--->   "+ v));
                        }else{
                            System.out.println("\nEl mazo del usuario esta vacio...");
                        }
                    break;
                    case(4):
                        if (!mazo.isEmpty()){
                            System.out.println("\nTu mazo ordenado  -->   Mostrar nombre, tipo y cantidad de cada carta en el mazo, ordenadas por tipo\n");
                            ArrayList<String> monstruos = new ArrayList<>();
                            ArrayList<String> hechizos = new ArrayList<>();
                            ArrayList<String> trampas = new ArrayList<>();
                            for (int i = 0;i<cartasU.size(); i++){
                                if (mazo.get(cartasU.get(i)).equals("Monstruo")){
                                    monstruos.add(cartasU.get(i));
                                }else if (mazo.get(cartasU.get(i)).equals("Hechizo")){
                                    hechizos.add(cartasU.get(i));
                                }else{
                                    trampas.add(cartasU.get(i));
                                }
                            }
                            Collections.sort(monstruos);
                            Collections.sort(hechizos);
                            Collections.sort(trampas);

                            System.out.println("\nLos MONSTRUOS del usuario son: ");
                            monstruos.forEach((v) -> System.out.println("\n" +v));
                            System.out.println("\nLos HECHIZOS del usuario son: ");
                            hechizos.forEach((v) -> System.out.println("\n"+v));
                            System.out.println("\nLas TRAMPAS del usuario son: ");
                            trampas.forEach((v) -> System.out.println("\n"+v));

                        }else{
                            System.out.println("\nEl mazo del usuario esta vacio...");
                        }
                    break;
                    case(5):
                        System.out.println("\nCartas disponibles  -->   Mostrar el nombre y tipo de todas las cartas del mazo general\n");
                        if (!cartas.isEmpty()){
                        cartas.forEach((k,v) -> System.out.println("\n-------------------\nNombre:  " + k + "\nTipo:  "+ v + "\n-------------------\n"));
                    }else{
                        System.out.println("No hay cartas en el sistema...");
                    }
                    break;
                    case(6):
                        if (!cartas.isEmpty()){
                            System.out.println("Mazo General Ordenado  -->   Mostrar nombre y tipo de todas las cartas disponibles ordenadas por tipo");
                            ArrayList<String> monstruos = new ArrayList<>();
                            ArrayList<String> hechizos = new ArrayList<>();
                            ArrayList<String> trampas = new ArrayList<>();

                            for (int i = 0;i<nombres.size(); i++){
                                if(cartas.get(nombres.get(i)).equals("Monstruo")){
                                    monstruos.add(nombres.get(i));
                                }else if (cartas.get(nombres.get(i)).equals("Hechizo")){
                                    hechizos.add(nombres.get(i));
                                }else if(cartas.get(nombres.get(i)).equals("Trampa")){
                                    trampas.add(nombres.get(i));
                                }
                            }

                            System.out.println("\n\n-------------\nMONSTRUOS\n-------------\n\n\n");
                            for (int i = 0;i < monstruos.size();i++){
                                System.out.println(i +". " + monstruos.get(i));
                            }

                            System.out.println("\n\n-------------\nHECHIZOS\n-------------\n\n\n");
                            for (int i = 0;i < hechizos.size();i++){
                                System.out.println(i +". " +hechizos.get(i));
                            }

                            System.out.println("\n-------------\n\n\nTRAMPAS\n-------------\n\n\n");
                            for (int i = 0;i < trampas.size();i++){
                                System.out.println(i +". " + trampas.get(i));
                            }
                        }else{
                            System.out.println("\nNo hay cartas en el sistema...");
                        }
                    break;
                    default:
                        System.out.println("¡¡ERROR!! Opcion invalida");
                    break;
                }
                //Se muestra menu de opciones a realizar con las cartas y se lee
                System.out.print(menu);
                seleccion = scan.nextInt();
            } //TERMINAN EL CASE
           
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        System.out.println("\n \\-___-//  ¡Hasta luego!  \\-___-// ");
    }	
}
