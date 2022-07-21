package lambda;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.multiply.apply(a, b);
        int d = calc.divide.apply(a, b); //b=0,но делить на ноль нельзя, необходимо использовать другое делимое
        boolean e = calc.isPositive.test(-5);

        calc.println.accept(c);
        calc.println.accept(d);
        calc.print.accept(e);
    }
}
