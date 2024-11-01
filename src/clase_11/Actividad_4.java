package clase_11;

import java.util.*;

class Usuario {
    private String id;
    private String nombre;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "'}";
    }
}

class RedSocial {
    private Map<String, Usuario> usuarios; // Mapa para almacenar usuarios por ID
    private Map<Usuario, List<Usuario>> listaAmistades;

    public RedSocial() {
        usuarios = new HashMap<>();
        listaAmistades = new HashMap<>();
    }

    // Método para agregar un usuario a la red social
    public void agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            System.out.println("Error: El usuario con ID " + usuario.getId() + " ya existe.");
        } else {
            usuarios.put(usuario.getId(), usuario);
            listaAmistades.put(usuario, new ArrayList<>());
            System.out.println("Usuario agregado: " + usuario);
        }
    }

    // Método para conectar dos usuarios como amigos (relación bidireccional)
    public void conectarUsuarios(String idUsuario1, String idUsuario2) {
        Usuario usuario1 = usuarios.get(idUsuario1);
        Usuario usuario2 = usuarios.get(idUsuario2);

        if (usuario1 == null || usuario2 == null) {
            System.out.println("Uno o ambos usuarios no existen.");
            return;
        }

        listaAmistades.get(usuario1).add(usuario2);
        listaAmistades.get(usuario2).add(usuario1);
        System.out.println("Amistad creada entre " + usuario1.getNombre() + " y " + usuario2.getNombre());
    }

    // Método DFS
    public void dfs(String idInicio) {
        Usuario usuarioInicio = usuarios.get(idInicio);
        if (usuarioInicio == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        Set<Usuario> visitados = new HashSet<>();
        System.out.print("Recorrido DFS desde el usuario " + usuarioInicio.getNombre() + ": ");
        dfsRecursivo(usuarioInicio, visitados);
        System.out.println();
    }

    private void dfsRecursivo(Usuario usuario, Set<Usuario> visitados) {
        visitados.add(usuario);
        System.out.print(usuario + " ");
        for (Usuario amigo : listaAmistades.getOrDefault(usuario, new ArrayList<>())) {
            if (!visitados.contains(amigo)) {
                dfsRecursivo(amigo, visitados);
            }
        }
    }

    // Método BFS
    public void bfs(String idInicio) {
        Usuario usuarioInicio = usuarios.get(idInicio);
        if (usuarioInicio == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        Set<Usuario> visitados = new HashSet<>();
        Queue<Usuario> cola = new LinkedList<>();
        cola.add(usuarioInicio);
        visitados.add(usuarioInicio);

        System.out.print("Recorrido BFS desde el usuario " + usuarioInicio.getNombre() + ": ");
        while (!cola.isEmpty()) {
            Usuario usuarioActual = cola.poll();
            System.out.print(usuarioActual + " ");

            for (Usuario amigo : listaAmistades.getOrDefault(usuarioActual, new ArrayList<>())) {
                if (!visitados.contains(amigo)) {
                    visitados.add(amigo);
                    cola.add(amigo);
                }
            }
        }
        System.out.println();
    }
}

public class Actividad_4 {
    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Conectar Usuarios como Amigos");
            System.out.println("3. Realizar recorrido DFS");
            System.out.println("4. Realizar recorrido BFS");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del usuario: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    Usuario usuario = new Usuario(id, nombre);
                    redSocial.agregarUsuario(usuario);
                    break;

                case 2:
                    System.out.print("Ingrese el ID del primer usuario: ");
                    String idUsuario1 = scanner.nextLine();
                    System.out.print("Ingrese el ID del segundo usuario: ");
                    String idUsuario2 = scanner.nextLine();
                    redSocial.conectarUsuarios(idUsuario1, idUsuario2);
                    break;

                case 3:
                    System.out.print("Ingrese el ID del usuario de inicio para DFS: ");
                    String idInicioDFS = scanner.nextLine();
                    redSocial.dfs(idInicioDFS);
                    break;

                case 4:
                    System.out.print("Ingrese el ID del usuario de inicio para BFS: ");
                    String idInicioBFS = scanner.nextLine();
                    redSocial.bfs(idInicioBFS);
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Saliendo de la aplicación.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}
