package datos;

import general.TipoPeriodo;
import general.TipoProceso;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Néstor Villalobos Corrales
 */
public class Periodo implements Serializable{

    //ATRIBUTOS
    private int numero;
    private TipoPeriodo tipo;
    private ArrayList<Curso> cursos;

    //CONSTRUCTORES
    public Periodo() {
        cursos = new ArrayList<Curso>();
    }

    public Periodo(int numero, TipoPeriodo tipo) {
        this.numero = numero;
        this.tipo = tipo;
        cursos = new ArrayList<Curso>();
    }
    
    //GETS&SETS
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoPeriodo getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeriodo tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    //OTROS MÉTODOS
    public void agregarCurso(Curso curso ){
        cursos.add(curso);
    }
    
    public void eliminarCurso(Curso curso ){
        cursos.remove(curso);
    }
    
    public void modificarCurso(Curso curso, int nota, TipoProceso tipoProceso, int veces, boolean aprobado, boolean levantamiento){
        String codigo = curso.getCodigo();
        String nombre = curso.getNombre();
        int indice = buscarCurso(codigo, nombre);
        Curso cursoMod = cursos.get(indice);
        
        cursoMod.setAprobado(aprobado);
        cursoMod.setVecesCursado(veces);
        cursoMod.setTipoProceso(tipoProceso);
        cursoMod.setNota(nota);
        cursoMod.setRequisitosLevantados(levantamiento);
    }
    
    public int  buscarCurso(String codigo, String nombre){
        Curso c = new Curso();
        c.setCodigo(codigo);
        c.setNombre(nombre);
        return cursos.indexOf(c);
    }
    
    public int totalCreditos(){        
        int creditos = 0;
        for (int i = 0; i < cursos.size(); i++){
            Curso cursoActual = cursos.get(i);
            creditos = creditos + cursoActual.getCreditos();
        }
        return creditos;
    }
    
    public int totalHoras(){        
        int horas = 0;
        for (int i = 0; i < cursos.size(); i++){
            Curso cursoActual = cursos.get(i);
            horas = horas + cursoActual.getHoras();
        }
        return horas;
    }

    
    public boolean cursoRepetido(String codigo, String nombre){
        for (int i=0; i < cursos.size(); i++ ){
            if ( cursos.get(i).getCodigo().equals (codigo) ||
                 cursos.get(i).getNombre().equals (nombre) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Periodo{" + "numero=" + numero + ", tipo=" + tipo + ", cursos=" + cursos + '}';
    }   
}