package datos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Néstor Villalobos Corrales
 */
public class RegistroCursos implements Serializable{
    
    //ATRIBUTOS
    private ArrayList<Curso> listaCursos;
    
    //CONSTRUCTORES
    public RegistroCursos() {
        listaCursos = new ArrayList<Curso>();
    }

    //GETS&SETS
    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
    
    //OTROS MÉTODOS
    public boolean agregarCurso(String codigo, String nombre, int horas, int creditos, String contenidoTematico, Icon icono){
        if(!cursoRepetido(codigo, nombre))
        {
            Curso c = new Curso(codigo, nombre, horas, creditos, contenidoTematico, icono);
            listaCursos.add(c);
            return true;
        }
        else return false;
    }
    
    public boolean cursoRepetido(String codigo, String nombre){
        Curso c = new Curso();
        c.setCodigo(codigo);
        c.setNombre(nombre);
        int indice = listaCursos.indexOf(c);
        if (indice == -1) {
            //No existe la combinación, pero talvez por separado si existan.
            if (cursoCodigoRepetido(codigo) || cursoNombreRepetido(nombre)) return true;
            else return false;
        }
        else return true;
    }
    
    public boolean cursoCodigoRepetido (String codigo){
        for (int i = 0; i < listaCursos.size(); i++){
            Curso cursoActual = listaCursos.get(i);
            if (cursoActual.getCodigo().equals(codigo)) return true;
        }
        return false;
    }
    
    public boolean cursoNombreRepetido (String nombre){
        for (int i = 0; i < listaCursos.size(); i++){
            Curso cursoActual = listaCursos.get(i);
            if (cursoActual.getNombre().equals(nombre)) return true;
        }
        return false;
    }
    
    public Curso buscarCurso (String codigo, String nombre){
        Curso c = new Curso();
        c.setCodigo(codigo);
        c.setNombre(nombre);       
        int indice = listaCursos.indexOf(c);
        if (indice != -1) {
            return listaCursos.get(indice);
        }
        else return null;
    }
    
    public void modificarCurso(String codigo, String nombre, int horas, int creditos, String contenidoTematico, Icon icono, boolean enUso){
        Curso c = buscarCurso(codigo, nombre);
        c.setHoras(horas);
        c.setCreditos(creditos);
        c.setContenidoTematico(contenidoTematico);
        c.setIcono(icono);
        c.setEnUso(enUso);
    }
    
    public boolean eliminarCurso(String codigo, String nombre){
        Curso c = buscarCurso(codigo, nombre);
        if(c != null) {
            listaCursos.remove(c);
            return true;
        }
        else return false;
    }
}
