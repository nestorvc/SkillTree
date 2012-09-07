package datos;

import general.TipoProceso;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Nestor Villalobos Corrales
 */
public class Curso implements Serializable{
    
    //ATRIBUTOS
    private String codigo;
    private String nombre;
    private int horas;
    private int creditos;
    private String contenidoTematico;
    private Icon icono;
    private boolean enUso;
    private ArrayList<Curso> requisitos;
    private int nota;
    private TipoProceso tipoProceso;
    private int vecesCursado;
    private boolean aprobado;
    private boolean requisitosLevantados;

    //CONSTRUCTORES
    public Curso() {
    }

    public Curso(String codigo, String nombre, int horas, int creditos, String contenidoTematico, Icon icono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
        this.creditos = creditos;
        this.contenidoTematico = contenidoTematico;
        this.enUso = false;
        this.icono = icono;
        requisitos = new ArrayList<Curso>();
        nota = -1;
        vecesCursado = 0;
        tipoProceso = TipoProceso.Ordinario;
        aprobado = false;
        requisitosLevantados = false;
    }    

    //GETS&SETS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContenidoTematico() {
        return contenidoTematico;
    }

    public void setContenidoTematico(String contenidoTematico) {
        this.contenidoTematico = contenidoTematico;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
        
    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }
    
    public Icon getIcono() {
        return icono;
    }

    public void setIcono(Icon icono) {
        this.icono = icono;
    }

    public ArrayList<Curso> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(ArrayList<Curso> requisitos) {
        this.requisitos = requisitos;
    }
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public int getVecesCursado() {
        return vecesCursado;
    }

    public void setVecesCursado(int vecesCursado) {
        this.vecesCursado = vecesCursado;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
    
    public boolean isRequisitosLevantados() {
        return requisitosLevantados;
    }

    public void setRequisitosLevantados(boolean requisitosLevantados) {
        this.requisitosLevantados = requisitosLevantados;
    }

    //OTROS MÉTODOS

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", nombre=" + nombre + ", horas=" + horas + ", creditos=" + creditos + ", contenidoTematico=" + contenidoTematico + ", icono=" + icono + ", enUso=" + enUso + '}';
    }    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 79 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }
}