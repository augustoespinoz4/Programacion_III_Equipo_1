package clase_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Usuario {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return nombre.equals(usuario.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class RedSocial {
    private Map<Usuario, List<Usuario>> seguidores;  // Cada usuario y su lista de usuarios que sigue
    private Map<Usuario, List<Usuario>> seguidoresDe;  // Cada usuario y la lista de quienes lo siguen

    public RedSocial() {
        seguidores = new HashMap<>();
        seguidoresDe = new HashMap<>();
    }

    // Agregar un nuevo usuario a la red social
    public void agregarUsuario(Usuario usuario) {
        seguidores.putIfAbsent(usuario, new ArrayList<>());
        seguidoresDe.putIfAbsent(usuario, new ArrayList<>());
    }

    // Un usuario sigue a otro
    public void seguir(Usuario seguidor, Usuario seguido) {
        if (seguidor.equals(seguido)) {
            System.out.println(seguidor.getNombre() + " no puede seguirse a sí mismo.");
            return;
        }
        
        agregarUsuario(seguidor);
        agregarUsuario(seguido);
        
        List<Usuario> siguiendo = seguidores.get(seguidor);
        if (!siguiendo.contains(seguido)) {
            siguiendo.add(seguido);
            seguidoresDe.get(seguido).add(seguidor);
        }
    }

    // Un usuario deja de seguir a otro
    public void dejarDeSeguir(Usuario seguidor, Usuario seguido) {
        if (seguidores.containsKey(seguidor)) {
            List<Usuario> siguiendo = seguidores.get(seguidor);
            if (siguiendo.remove(seguido)) {
                seguidoresDe.get(seguido).remove(seguidor);
            }
        }
    }

    // Lista de usuarios que sigue un usuario dado
    public List<Usuario> listarSeguidos(Usuario usuario) {
        return seguidores.getOrDefault(usuario, new ArrayList<>());
    }

    // Lista de usuarios que siguen a un usuario dado
    public List<Usuario> listarSeguidoresDe(Usuario usuario) {
        return seguidoresDe.getOrDefault(usuario, new ArrayList<>());
    }

    // Método para mostrar las conexiones de la red social
    public void mostrarRed() {
        System.out.println("Usuarios y a quienes siguen:");
        for (Map.Entry<Usuario, List<Usuario>> entry : seguidores.entrySet()) {
            System.out.println(entry.getKey().getNombre() + " sigue a " + entry.getValue());
        }
    }
}

public class Actividad_1 {
    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial();

        Usuario usuario1 = new Usuario("Carlos");
        Usuario usuario2 = new Usuario("Ana");
        Usuario usuario3 = new Usuario("Luis");
        Usuario usuario4 = new Usuario("María");

        redSocial.agregarUsuario(usuario1);
        redSocial.agregarUsuario(usuario2);
        redSocial.agregarUsuario(usuario3);
        redSocial.agregarUsuario(usuario4);

        redSocial.seguir(usuario1, usuario2);
        redSocial.seguir(usuario1, usuario3);
        redSocial.seguir(usuario2, usuario4);
        redSocial.seguir(usuario3, usuario4);
        redSocial.seguir(usuario4, usuario1);  // María sigue a Carlos

        redSocial.mostrarRed();

        System.out.println("\nCarlos sigue a: " + redSocial.listarSeguidos(usuario1));
        System.out.println("Ana sigue a: " + redSocial.listarSeguidos(usuario2));

        System.out.println("\nUsuarios que siguen a María: " + redSocial.listarSeguidoresDe(usuario4));
        System.out.println("Usuarios que siguen a Carlos: " + redSocial.listarSeguidoresDe(usuario1));

        // Carlos deja de seguir a Luis
        redSocial.dejarDeSeguir(usuario1, usuario3);
        System.out.println("\nCarlos deja de seguir a Luis...");
        redSocial.mostrarRed();
    }
}
