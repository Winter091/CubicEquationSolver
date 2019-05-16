public class Main {

    public static void main(String[] args) {

        solve_cubic_equation(1, 6, 12, 8);

    }

    private static void solve_cubic_equation(double a, double b, double c, double d) {

        double f = ((3 * c / a) - (b * b / a / a)) / 3;
        double g = ((2 * Math.pow(b, 3) / Math.pow(a, 3)) - (9 * b * c / a / a) + (27 * d / a)) / 27;
        double h = (g * g / 4) + (Math.pow(f, 3) / 27);

        // 3 equal real roots
        if (h == 0 && g == 0 && f == 0) {
            solve_3_equal(a, d);
        }

        // 1 real root
        else if (h > 0) {
            solve_1_real(a, b, g, h);
        }

        // 3 different real roots, h <= 0
        else {
            solve_3_different(a, b, g, h);
        }
    }

    private static boolean need_round(double a) {
        return (Math.abs(a - Math.round(a)) < 0.00000000000001);
    }

    private static void solve_3_equal(double a, double d) {
        double root = Math.cbrt(d / a) * -1;

        if (need_round(root)) root = Math.round(root);

        System.out.println("All 3 roots are real and equal:");
        System.out.println("x1 = x2 = x3 = " + root);
    }

    private static void solve_1_real(double a, double b, double g, double h) {
        double r = -(g / 2) + Math.cbrt(h);
        double s = Math.cbrt(r);
        double t = -(g / 2) - Math.cbrt(h);
        double u = Math.cbrt(t);
        double x1 = (s + u) - (b / 3 / a);
        double x23 = -(s + u) / 2 - (b / 3 / a);
        double imaginary_part = (s - u) * Math.cbrt(3) / 2;

        if (need_round(x1)) x1 = Math.round(x1);
        if (need_round(x23)) x23 = Math.round(x23);
        if (need_round(imaginary_part)) imaginary_part = Math.round(imaginary_part);

        System.out.println("1 root is real, 2 are imaginary:");
        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x23 + " + i * " + imaginary_part);
        System.out.println("x3 = " + x23 + " - i * " + imaginary_part);
    }

    private static void solve_3_different(double a, double b, double g, double h) {
        double i = Math.cbrt(((g * g / 4) - h));
        double j = Math.cbrt(i);
        double k = Math.acos(-(g / 2 / i));
        double l = j * -1;
        double m = Math.cos(k / 3);
        double n = Math.cbrt(3) * Math.sin(k / 3);
        double p = (b / 3 / a) * -1;
        double x1 = 2 * j * Math.cos(k / 3) - (b / 3 / a);
        double x2 = l * (m + n) + p;
        double x3 = l * (m - n) + p;

        if (need_round(x1)) x1 = Math.round(x1);
        if (need_round(x2)) x2 = Math.round(x2);
        if (need_round(x3)) x3 = Math.round(x3);

        System.out.println("All 3 roots are real and different:");
        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        System.out.println("x3 = " + x3);
    }
}
