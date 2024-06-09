package ru.stqa.geometry.figures;

public class Triangle {
    double a;
    double b;
    double c;


    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a<=0 || b<=0 ||c<=0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть меньше или равна нулю");
        }
        if ((a+b)<=c ||(b+c)<=a ||(c+a)<=b){
            throw new IllegalArgumentException("Сумма двух сторон треугольника не может быть меньше или равна третьей стороне");
        }

    }

    public double perimeter() {
        double p = (this.a + this.b + this.c);
        return p;


    }

    public double area() {
        double S = Math.sqrt(((this.perimeter() / 2) * ((this.perimeter() / 2) - this.a) * ((this.perimeter() / 2) - this.b) * ((this.perimeter() / 2) - this.c)));
        return S;

    }

}