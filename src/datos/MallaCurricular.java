package datos;

import general.TipoPeriodo;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Néstor Villalobos Corrales
 */
public class MallaCurricular implements Serializable{

    //ATRIBUTOS
    private String nombre;
    private String carrera;
    private Periodo[] periodos;

    //CONSTRUCTORES
    public MallaCurricular( String nombre, String carrera, int cantidadPeriodos, TipoPeriodo tipoP) {
        this.nombre = nombre;
        this.carrera = carrera;
        periodos = new Periodo [cantidadPeriodos];
        for (int i = 0; i <cantidadPeriodos; i++){
            periodos[i] = new Periodo(i+1, tipoP);
        }
    }
    
    //GETS&SETS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }
    
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Periodo[] getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Periodo[] periodos) {
        this.periodos = periodos;
    }

    //OTROS MÉTODOS
    public Periodo recuperaPeriodo(int numero){
        if (numero > 0 && numero <=getPeriodos().length) return getPeriodos()[numero-1];
        else return null;
    }
    
    public boolean agregarCurso(int periodo, Curso curso, ArrayList<Curso> requisitos){
        if(!cursoRepetidoEnPeriodos(curso.getCodigo(), curso.getNombre())){
            Curso c = curso;            
            c.setRequisitos(requisitos);
            c.setEnUso(true);
            periodos[periodo].agregarCurso(c);            
            return true;
        }
        else return false;
    }
    
    public void modificarCurso(int periodoOriginal, int periodoNuevo, Curso curso, ArrayList<Curso> requisitos){
        Curso c = curso;       
        periodos[periodoOriginal].eliminarCurso(c);
        c.setRequisitos(requisitos);
        periodos[periodoNuevo].agregarCurso(c);
    }
    
    public void eliminarCurso(int periodo, Curso curso){
        periodos[periodo].eliminarCurso(curso);
    }
    
    public boolean cursoRepetidoEnPeriodos(String codigo, String nombre){        
        for (int i = 0; i < periodos.length; i++) {
            Periodo periodoActual = periodos[i];
            if(periodoActual.cursoRepetido(codigo, nombre))return true;
        }
        return false;
    }
    
    public int periodoDelCurso (Curso curso){
        int periodoEncontrado = -1;
        for (int i = 0; i < periodos.length; i++){
            if (periodos[i].getCursos().contains(curso)) periodoEncontrado = i;
        }
        return periodoEncontrado;
    }
    
    public int totalCreditos (){
        int creditos = 0;
        for (int i = 0; i < periodos.length; i++){
            creditos = creditos + periodos[i].totalCreditos();
        }
        return creditos;
    }
    
    public int totalHoras (){
        int horas = 0;
        for (int i = 0; i < periodos.length; i++){
            horas = horas + periodos[i].totalHoras();
        }
        return horas;
    }
    
    public int totalCursos(){
        int cursos = 0;
        for (int i = 0; i < periodos.length; i++){
            cursos = cursos + periodos[i].getCursos().size();
        }
        return cursos;
    }

    @Override
    public String toString() {        
        String periodosSt = "";
            for (int i = 0; i < getPeriodos().length; i++) {
                periodosSt += "Periodo " + (i + 1) + getPeriodos()[i] + "\n";
            }
        return "MallaCurricular{" + "nombre=" + nombre + ", carrera=" + carrera + ", periodos=" + periodosSt + '}';
    }
}