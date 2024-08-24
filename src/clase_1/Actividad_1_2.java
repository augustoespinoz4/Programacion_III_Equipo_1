package clase_1;

import java.math.BigInteger;

public class Actividad_1_2 {
    public static void main (String[] args) {
        // Ejemplo con long
        long num1 = 123456789L;
        long num2 = 987654321L;
        long sumaLong = num1 + num2;
        System.out.println("Suma con long: " + sumaLong);

        // Ejemplo con BigInteger
        BigInteger bigNum1 = new BigInteger("123456789012345678901234567890");
        BigInteger bigNum2 = new BigInteger("987654321098765432109876543210");
        BigInteger sumaBigInteger = bigNum1.add(bigNum2);
        System.out.println("Suma con BigInteger: " + sumaBigInteger);

    }
}
