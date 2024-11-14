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
        int numero=0;// número a adivinar
        int numAleatorio;// numero aleatorio generado por el programa
        int contaErrores;// guardará la cantidad de error que tuvo el jugador  
        boolean validacion=false;// verificación para el ciclo de control de excepciones

        do{
            try {
                nJugadores=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad de jugadores", "Cantidad de Participantes", 1));
                validacion=true;// cuando sea válido el ingreso saldrá del ciclo
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero", "Error!!", 0);
            }
        }while(!validacion); //seguirá solicitando el ingreso de un número entero mientras lo que el usuario ingrese no sea válido

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
                    try {
                        numero=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número", "Elección Nro", 1));
                        contaIntentos++;// cuenta la cantidad de intentos del jugador
                        
                        if(numero==numAleatorio){// el mensaje de felicitaciones se coloca al principio del ciclo interno para que no se anteponga un cuadro de diálogo de las opciones que le siguen
                            JOptionPane.showMessageDialog(null, "Usted ha adivinado el número "+numAleatorio+" en "+contaIntentos+" intentos", "Felicitaciones!!", 1);// imprime el número y la cantidad de intentos del jugador
                            termina=Instant.now();// se utiliza la clase instanta para marcar el momento en que termina la partida que es cuando el jugador a adivinado el número y recibido el mensaje de felicitación
                        }
                        else if (numAleatorio>numero) {
                            JOptionPane.showMessageDialog(null, "Demasiado bajo...Ingrese un número mayor", "Nueva Elección", 1);// solicitará otro número si nro aleatorio es mayor al que ingreso el usuario
                        
                        } else{
                            JOptionPane.showMessageDialog(null, "Demasiado alto...Ingrese un número menor", "Nueva Elección", 1);// solicitará otro número si nro aleatorio es menor al que ingreso el usuario
                            
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero", "Error!!", 0);
                    }
                     
                } while (numero!=numAleatorio);// iterará hasta que el numero ingresado por el usuario coincida con el aleatorio
                
                intentos[participantes-1]=contaIntentos;// al arreglo intentos se le asigna la cantidad de intentos totales a la posición del nombre correspondiente de cada jugador

                contaErrores=contaIntentos-1;// los errores serán todos los anteriores menos el último en que el jugador adiniva el número

                errores[participantes-1]=contaErrores;// al arreglo errores se le asigna la cantidad de errores totales a la posición del nombre correspondiente de cada jugador

                Duration duracion=Duration.between(comienza, termina); //calcula el tiempo entre comienzo y final de cada partida de cada jugador
                tiempos[participantes-1]=duracion.getSeconds(); // Devuelve segundos del jugador
                
                participantes++;// cuenta a medida que finaliza la jugada cada participante, sirve como bandera para salir del ciclo externo y como referencia de índice-1  en los arreglos
            
            }while(participantes<=nJugadores);// cuando la cantidad de participantes sea igual a la cantidad de jugadores ingresada al inicio saldrá del programa
    
           
        //---------------------//
        //Menú con Estadísticas// 
        //---------------------//
        int opcion=-1;// inicializada en -1 para que la primer opción del menú sea cero

        do {
                String[] opcionNro = {
                "Ganador del Premio", 
                "Jugador más rápido", 
                "Jugador más lento", 
                "Jugador con menor performance", 
                "Promedio de errores entre jugadores",
                "Informe Final", 
                "Finalizar Estadísticas"
                };
                
                String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la Operación que desea realizar...", "Estadísticas",
                JOptionPane.QUESTION_MESSAGE, null, opcionNro, opcionNro[0]); //contiene al array "opcionNro" como una lista
                if (seleccion != null) {
                opcion=Arrays.asList(opcionNro).indexOf(seleccion); //Arrays.asList contiene la lista de opciones e indexOf asignará un valor numérico según la posición de la opción en la lista y ese valor numérico es el que tomará la variable "opcion" para ingresar a la opción correspondiente en Switch
                } else {
                    opcion=6; //Sale del menú
                }    

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, menosIntentos(nombres, intentos), "Ganador!!",1);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, masRapido(nombres, tiempos),"Jugador más rápido", 1);
                    break;
                case 2:
                    masLento(nombres, tiempos);
                    break;
                case 3:
                    menorPerformance(nombres, intentos, errores);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, promErrores(nombres, errores), "Promedio Total Errores",1);
                    break;
                case 5:
                    informe();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Estadísticas Finalizada");
                    System.exit(0);
                    break;
            }
        } while (opcion!=6); 
    }

    /*Los métodos declarados a continuación se utilizaron para darle más legibilidad a la lectura del código de cada opción del menú Switch y al ingreso de datos en el array nombres[] en el programa principal*/


    //-------------------------//
    //Ingreso nombre de jugador// 
    //-------------------------//
    public static void nombreJugador(String[] nombres){
        String nombre;

        for (int i=0; i<nombres.length; i++) {
            do{
                nombre=JOptionPane.showInputDialog(null, "Ingrese nombre del Jugador "+(i+1), "Datos Jugador", 1);// el imput se asigna directamente al array para no declarar una variable intermedia
                
                if (nombre==null||nombre.isEmpty()||!nombre.matches("[a-zA-Z]+")) {//isEmty() controla que la cadena no esté vacía, vacía=true sino false // matches() guarda un patrón que indica que es lo que acepta la variable como ingreso de su valor, en este caso solo letras mayúsculas y minúsculas, devolverá true si lo ingresado NO contiene solo letras en este caso por eso se antepone el símbolo de negación 
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre válido", "Error!!", 0);
                } else {
                    nombres[i]=nombre;//si la cadena ingresada como nombre es correcta, la asigna como elemento del array nombres
                }
            }while(nombre==null||nombre.isEmpty()||!nombre.matches("[a-zA-Z]+"));
        }
    }


    //--------------------------//
    //Muestra nombre del Ganador//con menos intentos, se declara como string para utilizarlo en la opción correspondiente dentro del Switch e imprimirlo en el informe final 
    //--------------------------//
    public static String menosIntentos(String[] nombres, int[] intentos){
        int menosInt=intentos[0];
        int pos=0;

        for(int i=0; i<intentos.length; i++){
            if(intentos[i]<menosInt){
                menosInt=intentos[i];
                pos=i;
            }
        }
        return "El jugador "+nombres[pos]+" ha ganado el premio!!"+" con en el menor número de intentos>>> "+menosInt+" total";
    }


    //--------------------------//
    //Muestra jugador más rápido//el que tardo menos tiempo, se declara como string para utilizarlo en la opción correspondiente dentro del Switch e imprimirlo en el informe final
    //--------------------------//
    public static String masRapido(String[] nombres, long[] tiempos){
        long menosTiempo=tiempos[0];
        int pos=0;

        for(int i=0; i<tiempos.length; i++){
            if(tiempos[i]<menosTiempo){
                menosTiempo=tiempos[i];
                pos=i;
            }
        }
        return "El jugador "+nombres[pos]+" ha tardado "+menosTiempo+" segundos en adivinar el número, siendo el que menor tiempo ha hecho de todos los jugadores";
    }


    //-------------------------//
    //Muestra jugador más lento//el que tardo más tiempo 
    //-------------------------//
    public static void masLento(String[] nombres, long[] tiempos){
        long masTiempo=tiempos[0];
        int pos=0;

        for(int i=0; i<tiempos.length; i++){
            if(tiempos[i]>masTiempo){
                masTiempo=tiempos[i];
                pos=i;
            }
        }
        JOptionPane.showMessageDialog(null, "El jugador "+nombres[pos]+" ha tardado "+masTiempo+" segundos en adivinar el número, siendo el que mayor tiempo ha hecho de todos los jugadores","Jugador más lento", 1);
    }


    //----------------------------------------//
    //Muestra el jugador con menor performance//el que más intentos y errores tuvo 
    //----------------------------------------//
    public static void menorPerformance(String[] nombres, int[] intentos, int[] errores){
        int masInt=intentos[0];
        int masError=errores[0];
        int pos=0;

        for(int i=0; i<intentos.length&&i<errores.length; i++){
            if(intentos[i]>masInt&&errores[i]>masError){
                masInt=intentos[i];
                masError=errores[i];
                pos=i;
            }
        }
        JOptionPane.showMessageDialog(null, "El jugador "+nombres[pos]+" es el que menor performance ha tenido con "+masInt+" intentos y "+masError+" errores totales", "Menor Performance",1);
    }


    //---------------------------------------------//
    //Promedio de errores entre todos los jugadores// suma la cantidad de errores de cada jugador y lo divide por la cantidad total de jugadores, se declara como string para utilizarlo en la opción correspondiente dentro del Switch e imprimirlo en el informe final  
    //---------------------------------------------//
    public static String promErrores(String[] nombres, int[] errores){
        int sumaError=errores[0];
        double promErrores;

        for(int i=0; i<errores.length; i++){
            sumaError+=errores[i];
        }
        promErrores=(sumaError/nombres.length);
        return "El promedio de errores entre todos los jugadores es de "+promErrores+" errores";
    }


    //---------------------//
    //Muestra Informe Final//para que imprima un resumen de las estadísticas se declararon los métodos utilizados dentro del método informe como String que se suman y se asignan a la variable mensaje la cual sera impresa en un solo JOptionPane   
    //---------------------//
    public static void informe() {
        String mensaje="";
        mensaje+="\n"+menosIntentos(nombres, intentos);
        mensaje+="\n"+masRapido(nombres, tiempos);
        mensaje+="\n"+promErrores(nombres, errores);
        JOptionPane.showMessageDialog(null, mensaje, "Informe Final", 1);
    }
}
