package datos;

import general.TipoUsuario;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Néstor Villalobos Corrales
 */
public class RegistroUsuarios implements Serializable{
    
    //ATRIBUTOS
    private ArrayList<Usuario> listaUsuarios;
    
    //CONSTRUCTORES
    public RegistroUsuarios() {
        listaUsuarios = new ArrayList<Usuario>();
    }

    //GETS&SETS
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    //OTROS MÉTODOS
    public boolean agregarUsuario(String id, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo, MallaCurricular malla){
        if(!usuarioRepetido(id))
        {            
            Usuario u = new Usuario(id, nombre, apellido, telefono, tipo, activo);
            u.setMalla(malla);
            listaUsuarios.add(u);
            return true;
        }
        else return false;
    }
    
    public boolean usuarioRepetido(String id){
        Usuario u = new Usuario();
        u.setId(id);        
        int indice = listaUsuarios.indexOf(u);
        if (indice == -1) return false;
        else return true;
    }
    
    public Usuario buscarUsuario (String id){
        Usuario u = new Usuario();
        u.setId(id);        
        int indice = listaUsuarios.indexOf(u);
        if (indice != -1) {
            return listaUsuarios.get(indice);
        }
        else return null;
    }
    
    public void modificarUsuario(String id, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo, String contrasena){
        Usuario u = buscarUsuario(id);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setTelefono(telefono);
        u.setTipo(tipo);
        u.setActivo(activo);
        u.setContrasennia(contrasena);
    }
    
    public void modificarAdministrador(String idOriginal, String idNuevo, String nombre, String apellido, String telefono, TipoUsuario tipo, Boolean activo, String contrasena){
        Usuario u = buscarUsuario(idOriginal);
        u.setId(idNuevo);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setTelefono(telefono);
        u.setTipo(tipo);
        u.setActivo(activo);
        u.setContrasennia(contrasena);
    }
    
    public Usuario buscarLogin (String login){
        for(int i = 0; i<listaUsuarios.size(); i++){
            Usuario usuarioActual = listaUsuarios.get(i);
            if(usuarioActual.getLogin().equals(login)) {
                return usuarioActual;
            }
        }
        return null;
    }
}
