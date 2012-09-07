package ui;

import datos.*;
import general.TipoPeriodo;
import general.TipoProceso;
import general.TipoUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Icon;

/**
 *
 * @author Néstor Villalobos Corrales
 */
public class UIAplicacion implements Serializable{

    //ATRIBUTOS
    private RegistroUsuarios regUsuarios;
    private RegistroCursos regCursos;
    private MallaCurricular malla;
    
    //Flags
    private boolean mallaCreada;
    private boolean usuarioDefaultCreado;

    //CONSTRUCTORES
    public UIAplicacion() {
        regUsuarios = new RegistroUsuarios();
        regCursos = new RegistroCursos();
        malla = null;
        
        mallaCreada = false;
    }

    //GETS & SETS
    public RegistroUsuarios getRegUsuarios() {
        return regUsuarios;
    }

    public void setRegUsuarios(RegistroUsuarios regUsuarios) {
        this.regUsuarios = regUsuarios;
    }

    public RegistroCursos getRegCursos() {
        return regCursos;
    }

    public void setRegCursos(RegistroCursos regCursos) {
        this.regCursos = regCursos;
    }

    public MallaCurricular getMalla() {
        return malla;
    }

    public void setMalla(MallaCurricular malla) {
        this.malla = malla;
    }
    
    public boolean isMallaCreada() {
        return mallaCreada;
    }

    public void setMallaCreada(boolean mallaCreada) {
        this.mallaCreada = mallaCreada;
    }
    
    public boolean isUsuarioDefaultCreado() {
        return usuarioDefaultCreado;
    }

    public void setUsuarioDefaultCreado(boolean usuarioDefaultCreado) {
        this.usuarioDefaultCreado = usuarioDefaultCreado;
    }
    
    //MÉTODO LOGIN
    public Usuario verificarLogin (String login){
        Usuario usuarioBuscado = regUsuarios.buscarLogin(login);
        if(usuarioBuscado != null) {
            return usuarioBuscado;
        }
        else return null;
    }
    
    public boolean verificarContrasena (Usuario usuario, char[] contrasena){        
        char[] contrasenaCorrecta = usuario.getContrasennia().toCharArray();
        boolean esCorrecta = Arrays.equals(contrasenaCorrecta, contrasena);
        return esCorrecta;        
    }
    

    //MÉTODOS ADMIN_USUARIOS
    public boolean crearUsuario(String id, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo) {
        if (regUsuarios.agregarUsuario(id, nombre, apellido, telefono, tipo, activo, malla)) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarUsuario(String id) {
        return regUsuarios.buscarUsuario(id);
    }

    public void modificarUsuario(String id, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo, String contrasena) {
        regUsuarios.modificarUsuario(id, nombre, apellido, telefono, tipo, activo, contrasena);
    }
    
    public void modificarAdministrador (String idOriginal, String idNuevo, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo, String contrasena){
        regUsuarios.modificarAdministrador(idOriginal, idNuevo, nombre, apellido, telefono, tipo, activo, contrasena);
    }

    //MÉTODOS ADMIN_CURSOS
    public boolean crearCurso(String codigo, String nombre, int horas, int creditos, String contenidoTematico, Icon icono) {
        if (regCursos.agregarCurso(codigo, nombre, horas, creditos, contenidoTematico, icono)) {
            return true;
        } else {
            return false;
        }
    }

    public Curso buscarCurso(String codigo, String nombre) {
        return regCursos.buscarCurso(codigo, nombre);
    }

    public void modificarCurso(String codigo, String nombre, int horas, int creditos, String contenidoTematico, Icon icono, boolean enUso) {
        regCursos.modificarCurso(codigo, nombre, horas, creditos, contenidoTematico, icono, enUso);
    }

    public boolean eliminarCurso(String codigo, String nombre) {
        if (regCursos.eliminarCurso(codigo, nombre)) {
            return true;
        } else {
            return false;
        }
    }

    //MÉTODOS ADMIN_MALLA
    public void crearMalla(String nombre, String carrera, int cantidadPeriodos, TipoPeriodo tipoP) {
        malla = new MallaCurricular(nombre, carrera, cantidadPeriodos, tipoP);
        mallaCreada = true;
    }

    public void modificarMalla(String nombre, String carrera) {
        malla.setNombre(nombre);
        malla.setCarrera(carrera);
    }
    
    public boolean agregarCursoMalla(int periodo, Curso curso, ArrayList<Curso> requisitos){                
        return(malla.agregarCurso(periodo, curso, requisitos));
    }
    
    public void modificarCursoMalla(int periodoNuevo, Curso curso, ArrayList<Curso> requisitos){                
        int periodoOriginal = periodoDelCurso(curso);
        malla.modificarCurso(periodoOriginal, periodoNuevo, curso, requisitos);
    }
    
    public void eliminarCursoMalla(Curso curso){
        int periodo = periodoDelCurso(curso);
        malla.eliminarCurso(periodo, curso);
    }
    
    public boolean existeCursoMalla (Curso curso){
        int indPeriodo = malla.periodoDelCurso(curso);
        if(indPeriodo != -1){
            String codigo = curso.getCodigo();
            String nombre = curso.getNombre();
            int indCurso = malla.getPeriodos()[indPeriodo].buscarCurso(codigo, nombre);
            if (indCurso != -1) return true;
        }
        return false;
    } 
    
    public int periodoDelCurso (Curso curso){
        return malla.periodoDelCurso(curso);
    }
    
    public int periodoDelCursoEnMalla (MallaCurricular malla, Curso curso){
        return malla.periodoDelCurso(curso);
    }
    
    public String datosMalla(MallaCurricular malla){
        return malla.getNombre() + " - " + malla.getCarrera();
    }
    
    public String resumenMalla(MallaCurricular malla){
        return malla.totalCursos() +" Cursos - " + malla.totalCreditos() + " Créditos";
    }
    
    //MÉTODOS ESTUDIANTE
    
    public void sincronizarMallaDefaultAEstudiantesCreados(){
        if (malla != null){
            ArrayList<Usuario> listaUsuarios = regUsuarios.getListaUsuarios();
            for (int i = 0; i < listaUsuarios.size(); i++){
                Usuario usuarioActual = listaUsuarios.get(i);
                if (usuarioActual.getTipo() == TipoUsuario.Estudiante){
                    usuarioActual.setMalla(malla);
                }
            }
        }
        
    }
    
    public MallaCurricular sincronizarMallaEstudiante(Usuario estudiante){        
        if (malla != null){
            MallaCurricular mallaEstudiante = estudiante.getMalla();
            MallaCurricular mallaSync = malla;

            Periodo[] periodosMallaEstudiante = mallaEstudiante.getPeriodos();
            for (int i = 0; i < periodosMallaEstudiante.length; i++){
                ArrayList<Curso> cursosMallaEstudiante = periodosMallaEstudiante[i].getCursos();
                for (int h = 0; h < cursosMallaEstudiante.size(); h++){
                    Curso cursoActual = cursosMallaEstudiante.get(h);
                    int indicePeriodoNuevo = malla.periodoDelCurso(cursoActual);
                    if (indicePeriodoNuevo != -1){
                        String codigoCurso = cursoActual.getCodigo();
                        String nombreCurso = cursoActual.getNombre();
                        int indiceCursoSync = mallaSync.getPeriodos()[indicePeriodoNuevo].buscarCurso(codigoCurso, nombreCurso);
                        if(indiceCursoSync != -1){                                    
                            int nota = cursoActual.getNota();
                            TipoProceso tipoProceso = cursoActual.getTipoProceso();
                            int veces = cursoActual.getVecesCursado();
                            boolean aprobado = cursoActual.isAprobado();
                            boolean levantamiento = cursoActual.isRequisitosLevantados();
                            mallaSync.getPeriodos()[indicePeriodoNuevo].modificarCurso(cursoActual, nota, tipoProceso, veces, aprobado, levantamiento);
                        }
                    }
                }
            }                
            return mallaSync;
        }  
        else return null;
    }
    
    public ArrayList<Curso> cursosDependientesDe (Curso curso, MallaCurricular mallaEstudiante){
        ArrayList<Curso> cursosDependientes = new ArrayList<Curso>();
        
        Periodo[] periodos = mallaEstudiante.getPeriodos();
        for (int i = 0; i < periodos.length; i++){
            ArrayList<Curso> cursos = periodos[i].getCursos();
            for (int h = 0; h < cursos.size(); h++){
                Curso cursoActual = cursos.get(h);
                if (cursoActual.getRequisitos() != null ){
                    if (cursoActual.getRequisitos().contains(curso)) cursosDependientes.add(cursoActual);
                }                
            }
        }
        return cursosDependientes;
    }
    
    public boolean cursoHabilitado (Curso curso, MallaCurricular mallaEstudiante){        
        boolean todosLosRequisitosAprobados = false;
        ArrayList<Curso> requisitos = curso.getRequisitos();
        
        if(tieneLevantamientoDeRequisitos(curso, mallaEstudiante)) return true;
        
        if (requisitos != null){
            Periodo[] periodos = mallaEstudiante.getPeriodos();
            for (int i = 0; i < periodos.length; i++){
                ArrayList<Curso> cursos = periodos[i].getCursos();
                for (int h = 0; h < cursos.size(); h++){
                    Curso cursoActual = cursos.get(h);
                    for (int j = 0; j < requisitos.size(); j++){
                        Curso requisitoActual = requisitos.get(j);
                        if(cursoActual.equals(requisitoActual)){
                            if (cursoActual.isAprobado()) todosLosRequisitosAprobados = true;
                            else return false;
                        }
                    }
                }
            }
        }
        else return true;
        return todosLosRequisitosAprobados;        
    }
    
    public boolean tieneLevantamientoDeRequisitos(Curso curso, MallaCurricular mallaEstudiante){
        int indicePeriodoCursoRequisitosLevantados = mallaEstudiante.periodoDelCurso(curso);
        int indiceCursoRequisitosLevantados = mallaEstudiante.getPeriodos()[indicePeriodoCursoRequisitosLevantados].buscarCurso(curso.getCodigo(), curso.getNombre());
        Curso cursoRequisitosLevantados = mallaEstudiante.getPeriodos()[indicePeriodoCursoRequisitosLevantados].getCursos().get(indiceCursoRequisitosLevantados);
        if (cursoRequisitosLevantados.isRequisitosLevantados()) return true; 
        else return false;
    }
    
    public double promedioPonderado (Usuario estudiante){
        int sumatoria = 0;
        int cursosHabiles = 0;
        MallaCurricular mallaEstudiante = estudiante.getMalla();
        for (int i = 0; i < mallaEstudiante.getPeriodos().length; i++){
            ArrayList<Curso> cursos = mallaEstudiante.getPeriodos()[i].getCursos();
            for (int h = 0; h < cursos.size(); h++){
                Curso cursoActual = cursos.get(h);
                if (cursoActual.isAprobado()){
                    sumatoria = sumatoria + cursoActual.getNota();
                    cursosHabiles++;
                }
            }
        }
        if (cursosHabiles == 0) return 0;
        double promedio = sumatoria / cursosHabiles;
        return promedio;
    }
}
