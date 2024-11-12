package AO2_BARELLA_CAJAL_COSSO.Actividad2.Clases;

public class Curso {
    String nombreCurso;
    int duracion;
    Double matriculaAlumnos;
    Double costoPorEstudiante;
    
    /*
     * GETTERS & SETTERS
     */
  
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Double getMatriculaAlumnos() {
        return matriculaAlumnos;
    }
    
    public void setMatriculaAlumnos(Double matriculaAlumnos) {
        this.matriculaAlumnos = matriculaAlumnos;
    }

    public Double getCostoPorEstudiante() {
        return costoPorEstudiante;
    }

    public void setCostoPorEstudiante(Double costoPorEstudiante) {
        this.costoPorEstudiante = costoPorEstudiante;
    }
    
    /*
     * MÉTODOS CONSTRUCTORES
     */

    public Curso() {
        this.nombreCurso = "";
        this.duracion = 0;
        this.matriculaAlumnos = 0.0;
        this.costoPorEstudiante = 0.0;
    }

    public Curso(String nombreCurso, int duracion, Double matriculaAlumnos, Double costoPorEstudiante) {
        this.nombreCurso = nombreCurso;
        this.duracion = duracion;
        this.matriculaAlumnos = matriculaAlumnos;
        this.costoPorEstudiante = costoPorEstudiante;
    }
   
}
