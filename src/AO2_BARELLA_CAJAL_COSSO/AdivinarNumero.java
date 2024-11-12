package AO2_BARELLA_CAJAL_COSSO;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class AdivinarNumero {
    
    static Instant comienza;// las variables de la clase instant se declaran estáticas para poder llamarlas directamente desde los métodos y el programa principal, atributos inmutables de la clase principal
    static Instant termina;
    static int nJugadores;
    static String[] nombres;
    static int[] intentos;
    static int[] errores;
    static long[] tiempos;

    public static void main(String[] args) {
        
        int numero;// número a adivinar
        int numAleatorio;// numero aleatorio generado por el programa
        int contaErrores;// guardará la cantidad de error que tuvo el jugador  

        nJugadores=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad de jugadores", "Cantidad de Participantes", 1));
        int participantes=1;// contador de participantes
        nombres=new String[nJugadores];// array nombre de jugador
        intentos=new int[nJugadores];// array cantidad intentos de cada jugador
        errores=new int[nJugadores];// array cantidad errores de cada jugador
        tiempos=new long[nJugadores];// array tiempo de partida de cada jugador, se declara como longo ya que para calcular el tiempo se utilizan las clases instant y duration que trabajan con este tipo de datos
        
        nombreJugador(nombres); // toma el dato nombre de cada jugador y lo asigna al array nombres

            do{
                JOptionPane.showMessageDialog(null, "Turno Jugador "+nombres[participantes-1], "Turnero", 1);// mostrará el nombre del jugador actual que inicia la partida, la variable participantes se utiliza como una especie de bandera y contador para salir del ciclo externo y a su vez como índice del array nombres para que muestre el nombre del jugador actual
                comienza=Instant.now();// la clase instant captura el momento actual del programa con presición ya que utiliza pequeñas unidades de tiempo, en este caso para marcar el inicio de una partida

                numAleatorio=(int)(Math.random()*100);// random genera numeros de tipo double por defecto entre 0 y 1 para que genere enteros multiplicamos la función por 100 y lo parseamos como int
                System.out.println("numero aleatorio "+numAleatorio);
                int contaIntentos=0;// cuenta número de intentos se coloca dentro del ciclo externo para que vuelva a cero al tocarle el turno al siguiente participante

                do {
                    numero=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número", "Elección Nro", 1));
                    contaIntentos++;// cuenta la cantidad de intentos del jugador
                    
                    if(numero==numAleatorio){// el mensaje de felicitaciones se coloca al principio del ciclo interno para que no se anteponga un cuadro de diálogo de las opciones que le siguen
                        JOptionPane.showMessageDialog(null, "Usted ha adivinado el número "+numAleatorio+" en "+contaIntentos+" intentos", "Felicitaciones!!", 1);// imprime el número y la cantidad de intentos del jugador
                        termina=Instant.now();// se utiliza la clase instanta para marcar el momento en que termina la partida que es cuando el jugador a adivinado el número y recibido el mensaje de felicitación
                    }
                    else if (numAleatorio>numero) {
                        JOptionPane.showMessageDialog(null, "Demasiado bajo...Ingrese un número mayor", "Nueva Elección", 1);// solicitará otro número si nro aleatorio es mayor al que ingreso el usuario
                        // contaErrrores++;
                    } else{
                        JOptionPane.showMessageDialog(null, "Demasiado alto...Ingrese un número menor", "Nueva Elección", 1);// solicitará otro número si nro aleatorio es menor al que ingreso el usuario
                        // contaErrrores++;
                    }
                    
                } while (numero!=numAleatorio);// iterará hasta que el numero ingresado por el usuario coincida con el aleatorio
                
                intentos[participantes-1] = contaIntentos;
                contaErrores=contaIntentos-1;
                errores[participantes-1] = contaErrores;
                Duration duracion=Duration.between(comienza, termina);
                tiempos[participantes-1]=duracion.getSeconds(); // Devuelve segundos del jugador
                participantes++;
            

            }while(participantes<=nJugadores);// cuando la cantidad de participantes sea igual a la cantidad de jugadores ingresada al inicio saldrá del programa
    
            resumen(nombres, intentos, errores, tiempos);

            int opcion=-1;// inicializada en -1 para que la primer opción del menú sea cero

        do {
                String[] opcionNro = {
                "Ingresar Producto", 
                "Orden por COD", 
                "Búsqueda Secuencial por COD", 
                "Búsqueda Binaria por COD", 
                "Producto de mayor precio", 
                "Finalizar Operación"
                };
                
                String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la Operación que desea realizar...", "Menú Principal",
                JOptionPane.QUESTION_MESSAGE, null, opcionNro, opcionNro[0]); //contiene al array "opcionNro" como una lista
                if (seleccion != null) {
                opcion=Arrays.asList(opcionNro).indexOf(seleccion); //Arrays.asList contiene la lista de opciones e indexOf asignará un valor numérico según la posición de la opción en la lista y ese valor numérico es el que tomará la variable "opcion" para ingresar a la opción correspondiente en Switch
                } else {
                    opcion=5; //Sale del programa
                }    

            switch (opcion) {
                case 0:
                    // cargarDatos(codigo, nombre, precio);
                    // mostrarDatos(codigo, nombre, precio);
                    break;
                case 1:
                    // ordenaCodigo(codigo, nombre, precio);
                    // mostrarOrdenAsc(codigo, nombre, precio);
                    break;
                case 2:
                    // buscarProd(codigo, nombre, precio);
                    break;
                case 3:
                    // ordenaCodigo(codigo, nombre, precio);
                    // busquedaBinaria(codigo, nombre, precio);
                    break;
                case 4:
                    // JOptionPane.showMessageDialog(null, "El producto de mayor precio es "+nombre[posMayorPrecio(precio)], "Producto más caro", 1);
                    break;
                default:
                    // JOptionPane.showMessageDialog(null, "Operación Finalizada");
                    // System.exit(0);
                    break;
            }
        } while (opcion!=5);

    }


    //-------------------------//
    //Ingreso nombre de jugador// al entrar al bucle externo toma el nombre del primer jugador una vez que este adivine el número toma el dato nombre del siguiente jugador 
    //-------------------------//
    public static void nombreJugador(String[] nombres){
        for (int i=0; i<nombres.length; i++) {
            nombres[i]=JOptionPane.showInputDialog(null, "Ingrese nombre del Jugador "+(i+1), "Datos Jugador", 1);// el imput se asigna directamente al array para no declarar una variable intermedia
        }
    }


    //---------------//
    //Muestra resumen// al entrar al bucle externo toma el nombre del primer jugador una vez que este adivine el número toma el dato nombre del siguiente jugador 
    //------------ --//
    public static void resumen(String[] nombres, int[] intentos, int[] errores, long[] t) {
        StringBuilder mensaje = new StringBuilder();

        for (int i = 0; i < nombres.length; i++) {
            mensaje.append("\nJugador: "+nombres[i]).append(" Intentos: "+intentos[i]).append(" Errores: "+errores[i]).append(" Tiempo: "+t[i]+" segundos.");
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Resumen", 1);
        }

    }


}
