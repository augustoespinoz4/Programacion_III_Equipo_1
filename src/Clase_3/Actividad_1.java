package clase_3;

import java.util.ArrayList;

public class Actividad_1 {
    public static void main(String[] args) {
        ArrayList<cliente> clientes = new ArrayList<>();
        clientes.add(new cliente(1, "Juan", 1.1));
        clientes.add(new cliente(2, "Juan", 1.8));
        clientes.add(new cliente(3, "Juan", 1.2));
        clientes.add(new cliente(4, "Juan", 1.5));
    

        System.out.println("Scoring maximo: " + scoringMaximo(clientes));
    }
    

    private static cliente scoringMaximo(ArrayList<cliente> clientes) {
        return scoringMaximo(clientes, 0, clientes.size());
    }

    private static cliente scoringMaximo(ArrayList<cliente> clientes, int i, int f) {
        if (i == f-1) return clientes.get(i);
        int mitad = (f + i) / 2;
        cliente izq = scoringMaximo(clientes, i, mitad);
        cliente der = scoringMaximo(clientes, mitad, f);
        return izq.getScoring() > der.getScoring() ? izq : der;
    }

}

class cliente{
    int id;
    String nombre;
    double scoring;

    public cliente(int id, String nombre, double scoring) {
        this.id = id;
        this.nombre = nombre;
        this.scoring = scoring;
    }

    public double getScoring() {
        return scoring;
    }
}
