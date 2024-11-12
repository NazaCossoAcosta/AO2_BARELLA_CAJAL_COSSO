package AO2_BARELLA_CAJAL_COSSO.Actividad2;

import AO2_BARELLA_CAJAL_COSSO.Actividad2.Clases.*;
import javax.swing.JOptionPane;

public class demoCursos {
    public static void main(String[] args) {

        boolean condition = true;
        
        do {
            
            String[] opciones = {
                "Registrar Nuevo Curso",
                "Listar Curso", 
                "Calcular Costo Final del Curso",
                "Mostrar Cursos (Ordenado por matrícula)",
                "Buscar Cursos por Nombre",
                "Mostrar Total Recaudado por Curso",
                "Salir"
                };


            int opcion = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú de Selección",0, 1, null, opciones, opciones[0]);
            
            switch (opcion) {
                case 0:
                    //
                    break;
                case 6:
                    condition = false;
                    break;
                default:
                    break;
            }


        } while (condition);
    }    
}
