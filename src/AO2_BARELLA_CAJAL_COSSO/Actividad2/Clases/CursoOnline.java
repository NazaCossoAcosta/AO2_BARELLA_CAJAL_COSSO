package AO2_BARELLA_CAJAL_COSSO.Actividad2.Clases;

public class CursoOnline extends Curso {
    String ubicacion;
    String plataforma;
    Double horario;

    /*
     * GETTERS & SETTERS
     */
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Double getHorario() {
        return horario;
    }

    public void setHorario(Double horario) {
        this.horario = horario;
    }
    
    /*
     * MÉTODOS CONSTRUCTORES
     */
    
    public CursoOnline() {
        super();
        this.ubicacion = "";
        this.plataforma = "";
        this.horario = 0.0;
    }

    public CursoOnline(String nombreCurso, int duracion, Double matriculaAlumno, Double costoPorEstudiante, String ubicacion, String plataforma, Double horario) {
        super(nombreCurso, duracion, matriculaAlumno, costoPorEstudiante);
        this.ubicacion = ubicacion; 
        this.plataforma = plataforma;
        this.horario = horario; 
    }
}
