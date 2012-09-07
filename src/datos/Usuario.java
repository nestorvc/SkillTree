package datos;

import general.TipoUsuario;
import java.io.Serializable;
/**
 *
 * @author Néstor Villalobos Corrales
 */
public class Usuario implements Serializable{
    
    //ATRIBUTOS
    private String id;
    private String login;
    private String nombre;
    private String apellido;
    private String contrasennia;
    private String telefono;
    private boolean activo;
    private TipoUsuario tipo;
    private MallaCurricular malla;
    
    //CONSTRUCTORES
    public Usuario(){
    }

    public Usuario(String id, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipo = tipo;
        definirContrasennia();
        definirLogin();
        this.activo = activo;
        this.malla = null;
    }   
    
    //GETS&SETS

    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public MallaCurricular getMalla() {
        return malla;
    }

    public void setMalla(MallaCurricular malla) {
        this.malla = malla;
    }

    //OTROS MÉTODOS
    
    public void definirLogin(){
        login = nombre.toLowerCase() +"."+ apellido.toLowerCase();
    }

    public void definirContrasennia(){
        contrasennia = "" + nombre.charAt(0) + nombre.length() + apellido.charAt(0) + apellido.length();
        int posicion;
        posicion = this.id.length() - 4;
        int suma = 0;
        for ( int i = posicion; i< id.length(); i++){
            suma = suma + Integer.parseInt( ""+id.charAt(i));
        }
        id.substring(posicion);
        contrasennia = contrasennia + suma;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + "login=" + login + "nombre=" + nombre + "apellido=" + apellido + "contrasennia=" + contrasennia + "telefono=" + telefono + "activo=" + activo + "tipo=" + tipo + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 61 * hash + (this.contrasennia != null ? this.contrasennia.hashCode() : 0);
        return hash;
    }
}