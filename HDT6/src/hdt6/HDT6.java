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
        Map<String, String> cartas = new HashMap<>();
        ArrayList<String> nombres = new ArrayList<>(), cartasU = new ArrayList<>();
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
            for (int i=0; i<=(lectura.size()-1); i++){
                //Se itera la lectura y cada elemento se divide en dos (nombre-tipo)
                String item = lectura.get(i);
                //System.out.println(item);
                String[] arr = item.split("[|]");
                String k = arr[0].toString();
                nombres.add(arr[0]);
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
                        cartasU.add(nombre);
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
                        int monstruos = 0,trampas = 0, hechizos = 0;
                        for (int i =0;i< cartasU.size();i++){
                            System.out.println(cartasU.get(i));
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
                        mazo.forEach((k,v) -> System.out.println(k + " de tipo "+ v));
                    }else{
                        System.out.println("El mazo del usuario esta vacio...");
                    }
                }else if(opcion.equals("4")){
                    if (!mazo.isEmpty()){
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

                        System.out.println("Los monstruos del usuario son: ");
                        monstruos.forEach((v) -> System.out.println(v));
                        System.out.println("Los hechizos del usuario son: ");
                        hechizos.forEach((v) -> System.out.println(v));
                        System.out.println("Las trampas del usuario son: ");
                        trampas.forEach((v) -> System.out.println(v));

                    }else{
                        System.out.println("El mazo del usuario esta vacio...");
                    }
                }else if (opcion.equals("5")){
                    if (!cartas.isEmpty()){
                        cartas.forEach((k,v) -> System.out.println(k + ", de tipo "+ v));
                    }else{
                        System.out.println("No hay cartas en el sistema...");
                    }
                }else if(opcion.equals("6")){
                    if (!cartas.isEmpty()){
                        ArrayList<String> monstruos = new ArrayList<>();
                        ArrayList<String> hechizos = new ArrayList<>();
                        ArrayList<String> trampas = new ArrayList<>();

                        for (int i = 0;i<nombres.size(); i++){
                            if (cartas.get(nombres.get(i)).equals("Monstruo")){
                                monstruos.add(nombres.get(i));
                            }else if (cartas.get(nombres.get(i)).equals("Hechizo")){
                                hechizos.add(nombres.get(i));
                            }else if(cartas.get(nombres.get(i)).equals("Trampa")){
                                trampas.add(nombres.get(i));
                            }
                        }

                        System.out.println("Los monstruos son: ");
                        for (int i = 0;i < monstruos.size();i++){
                            System.out.println(monstruos.get(i));
                        }

                        System.out.println("Los hechizos son: ");
                        for (int i = 0;i < hechizos.size();i++){
                            System.out.println(hechizos.get(i));
                        }

                        System.out.println("Las trampas son: ");
                        for (int i = 0;i < trampas.size();i++){
                            System.out.println(trampas.get(i));
                        }
                    }else{
                        System.out.println("No hay cartas en el sistema...");
                    }
                }
                //Se muestra menu de opciones a realizar con las cartas y se lee
                System.out.print(menu);
                opcion = scan.next();
            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        System.out.println("Hasta luego!");
    }	
}
