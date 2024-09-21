package clase_3;

import java.util.ArrayList;

public class Actividad3 {
    public static void main(String[] args) {
        // Creamos una lista de clientes
        ArrayList<cliente> clientes = new ArrayList<>();
        clientes.add(new cliente(1, "Juan", 1.1));
        clientes.add(new cliente(2, "Ana", 3.5));
        clientes.add(new cliente(3, "Carlos", 0.9));
        clientes.add(new cliente(4, "Maria", 2.4));
        // Encontramos los dos clientes con los scoring máximos
        cliente[] resultado = encontrarDosMayores(clientes, 0, clientes.size());

        // Imprimimos los resultados
        System.out.println("Cliente con mayor scoring: "  + resultado[0].getNombre() + " con " + resultado[0].getScoring());
        System.out.println("Cliente con segundo mayor scoring: " + resultado[0].getNombre() +" con " + resultado[1].getScoring());
        
    }

    private static cliente[] encontrarDosMayores(ArrayList<cliente> clientes, int i, int f) {
        // Caso base: si la sublista tiene un solo elemento, devolvemos ese cliente
        // y un valor nulo para indicar que no hay un segundo cliente.
        if (f - i == 1) {
            return new cliente[] { clientes.get(i), null };
        }

        // Caso base: si la sublista tiene dos elementos, devolvemos ambos.
        if (f - i == 2) {
            cliente primero = clientes.get(i);
            cliente segundo = clientes.get(i + 1);
            if (primero.getScoring() > segundo.getScoring()) {
                return new cliente[] { primero, segundo };
            } else {
                return new cliente[] { segundo, primero };
            }
        }

        // Dividimos la lista a la mitad
        int mitad = (i + f) / 2;
        cliente[] izq = encontrarDosMayores(clientes, i, mitad);
        cliente[] der = encontrarDosMayores(clientes, mitad, f);

        // Combinamos los resultados de ambas mitades
        return combinarDosMayores(izq, der);
    }

    private static cliente[] combinarDosMayores(cliente[] izq, cliente[] der) {
        // Creamos un array para almacenar los dos clientes con mayores scoring
        cliente[] resultado = new cliente[2];

        // Comparamos los scorings de los clientes en las mitades izquierda y derecha
        if (izq[0].getScoring() > der[0].getScoring()) {
            resultado[0] = izq[0];
            resultado[1] = (izq[1] != null && izq[1].getScoring() > der[0].getScoring()) ? izq[1] : der[0];
        } else {
            resultado[0] = der[0];
            resultado[1] = (der[1] != null && der[1].getScoring() > izq[0].getScoring()) ? der[1] : izq[0];
        }

        return resultado;
    }
}

// Clase cliente
class cliente {
    private int id;
    private String nombre;
    private double scoring;

    public cliente(int id, String nombre, double scoring) {
        this.id = id;
        this.nombre = nombre;
        this.scoring = scoring;
    }

    public double getScoring() {
        return scoring;
    }

    public String getNombre(){
        return nombre;
    }
}
