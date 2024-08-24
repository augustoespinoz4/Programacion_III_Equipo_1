package clase_1;

public class Actividad_1_3 {
    public static void main (String[] args) {
        int num = 5;
        System.out.println("Suma de los " + num + " primeros numeros enteros: " + sumaRecursiva(num));
    }

    public static int sumaRecursiva(int n) {
        if (n == 0) {
            return n;
        }
        else {
            return n + sumaRecursiva(n - 1);
        }
    }
}

