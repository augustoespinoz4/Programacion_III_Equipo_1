package clase_12;

import java.util.*;

class Ataque {
    String tipo;
    int severidad; // Representa la gravedad del ataque (entre 1 y 10)

    public Ataque(String tipo, int severidad) {
        this.tipo = tipo;
        this.severidad = severidad;
    }

    public String getTipo() {
        return tipo;
    }

    public int getSeveridad() {
        return severidad;
    }

    @Override
    public String toString() {
        return "Ataque{" + "tipo='" + tipo + '\'' + ", severidad=" + severidad + '}';
    }
}

class SistemaDeteccion {
    Map<String, String> respuestas = new HashMap<>(); // Respuestas predefinidas a cada ataque

    public SistemaDeteccion() {
        // Definir las respuestas para cada tipo de ataque
        respuestas.put("SQL Injection", "Bloquear IP y registrar evento");
        respuestas.put("DDoS", "Aplicar límite de solicitudes y bloquear IP");
        respuestas.put("Phishing", "Enviar alerta a usuarios");
        respuestas.put("Fuerza Bruta", "Solicitar CAPTCHA y bloquear IP temporalmente");
    }

    public String responderAtaque(Ataque ataque) {
        return respuestas.getOrDefault(ataque.getTipo(), "Respuesta desconocida");
    }

    // Método DFS para recorrer el árbol de decisión
    public void dfs(AtaqueNodo nodo) {
        System.out.println(nodo);
        for (AtaqueNodo hijo : nodo.getHijos()) {
            dfs(hijo);
        }
    }

    // Método BFS para recorrer el árbol de decisión
    public void bfs(AtaqueNodo nodo) {
        Queue<AtaqueNodo> cola = new LinkedList<>();
        cola.add(nodo);

        while (!cola.isEmpty()) {
            AtaqueNodo actual = cola.poll();
            System.out.println(actual);
            cola.addAll(actual.getHijos());
        }
    }

    // Poda alfa-beta para evaluar mejor respuesta ante un ataque
    public int podaAlfaBeta(AtaqueNodo nodo, int alfa, int beta, boolean esMaximizador) {
        if (nodo.getHijos().isEmpty()) {
            return nodo.getAtaque().getSeveridad();
        }

        if (esMaximizador) {
            int maxValor = Integer.MIN_VALUE;
            for (AtaqueNodo hijo : nodo.getHijos()) {
                int valor = podaAlfaBeta(hijo, alfa, beta, false);
                maxValor = Math.max(maxValor, valor);
                alfa = Math.max(alfa, valor);
                if (beta <= alfa) {
                    break; // Poda beta
                }
            }
            return maxValor;
        } else {
            int minValor = Integer.MAX_VALUE;
            for (AtaqueNodo hijo : nodo.getHijos()) {
                int valor = podaAlfaBeta(hijo, alfa, beta, true);
                minValor = Math.min(minValor, valor);
                beta = Math.min(beta, valor);
                if (beta <= alfa) {
                    break; // Poda alfa
                }
            }
            return minValor;
        }
    }
}

// Nodo para representar un árbol de decisiones simplificado
class AtaqueNodo {
    private Ataque ataque;
    private List<AtaqueNodo> hijos;

    public AtaqueNodo(Ataque ataque) {
        this.ataque = ataque;
        this.hijos = new ArrayList<>();
    }

    public Ataque getAtaque() {
        return ataque;
    }

    public List<AtaqueNodo> getHijos() {
        return hijos;
    }

    public void agregarHijo(AtaqueNodo hijo) {
        hijos.add(hijo);
    }

    @Override
    public String toString() {
        return ataque.toString();
    }
}

public class Actividad_1 {
    public static void main(String[] args) {
        // Crear un sistema de detección
        SistemaDeteccion sistema = new SistemaDeteccion();

        // Ejemplos de ataques
        Ataque ataqueSQL = new Ataque("SQL Injection", 8);
        Ataque ataqueDDoS = new Ataque("DDoS", 9);
        Ataque ataquePhishing = new Ataque("Phishing", 5);
        Ataque ataqueFuerzaBruta = new Ataque("Fuerza Bruta", 6);

        // Crear árbol de decisión simplificado
        AtaqueNodo raiz = new AtaqueNodo(ataqueSQL);
        AtaqueNodo nodoDDoS = new AtaqueNodo(ataqueDDoS);
        AtaqueNodo nodoPhishing = new AtaqueNodo(ataquePhishing);
        AtaqueNodo nodoFuerzaBruta = new AtaqueNodo(ataqueFuerzaBruta);

        // Agregar hijos al nodo raíz
        raiz.agregarHijo(nodoDDoS);
        raiz.agregarHijo(nodoPhishing);
        nodoPhishing.agregarHijo(nodoFuerzaBruta);

        // Ejecución DFS y BFS en el árbol de decisiones
        System.out.println("Recorrido DFS:");
        sistema.dfs(raiz);

        System.out.println("\nRecorrido BFS:");
        sistema.bfs(raiz);

        // Aplicar poda alfa-beta para determinar mejor respuesta
        System.out.println("\nAplicación de poda alfa-beta:");
        int valorOptimo = sistema.podaAlfaBeta(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        System.out.println("Valor óptimo de severidad usando poda alfa-beta: " + valorOptimo);
    }
}
