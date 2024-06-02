public class Triangle {
    double a;
    double b;
    double c;


    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return this.a + this.b + this.c;


    }

    public double area() {
        double p = (this.a + this.b + this.c) / 2;
        double S = Math.sqrt((p * (p - this.a) * (p - this.b) * (p - this.c)));
        return S;

    }

}