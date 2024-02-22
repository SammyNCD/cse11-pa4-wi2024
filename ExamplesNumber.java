import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    Number getMax(Number other);
    String toString();
    double toDouble();
  }

class WholeInteger implements Number {

    int n;

    WholeInteger(int n) {
        this.n = n;
    }

    public int numerator() {
        return n;
    }

    public int denominator() {
        return 1;
    }

    public Number add(Number other) {
        if (other instanceof WholeInteger) {
            int otherNum = other.numerator();
            return new WholeInteger(n + otherNum);
        } else {
            int num = other.numerator();
            int denom = other.denominator();
            int sumNum = this.n * denom + num;
            return new Fraction(sumNum, denom);
        }
    }

    public Number multiply(Number other) {
            int val = this.n * other.numerator();
            return new WholeInteger(val);
    }
    
    public Number getMax(Number other) {
        int otherNum = other.numerator();
        return n >= otherNum ? this : other;
    }
    
    public String toString() {
        return "" + this.n;
    }

    public double toDouble() {
        return (double) this.n;
    }

}

class Fraction implements Number {

    int numerator;
    int denominator;

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int numerator() {
        return this.numerator;
    }

    public int denominator() {
        return this.denominator;
    }

    public Number add(Number other) {
        int otherNum = other.numerator();
        int otherDenom = other.denominator();
        int num = numerator * otherDenom + otherNum * denominator;
        int denom = denominator * otherDenom;
        return new Fraction(num, denom);
    }

    public Number multiply(Number other) {
        int otherNum = other.numerator();
        int otherDenom = other.denominator();
        return new Fraction(numerator * otherNum, denominator * otherDenom);
    }

    public Number getMax(Number other) {
        double thisValue = (double) numerator / denominator;
        double othervalue = other.toDouble();
        return thisValue >= othervalue ? this : other;
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }
}

class ExampleNumbers {

    Number num1 = new WholeInteger(3);
    Number num2 = new WholeInteger(2);
    Number num3 = new Fraction(1,5);
    Number num4 = new Fraction(1, 10);
    Number num5 = new Fraction(3, 10);

    void testNumerator(Tester t) {
        t.checkExpect(num1.numerator(), 3);
        t.checkExpect(num2.numerator(), 2);
        t.checkExpect(num3.numerator(), 1);
        t.checkExpect(num4.numerator(), 1);
        t.checkExpect(num5.numerator(), 3);
    }

    void testDenominator(Tester t) {
        t.checkExpect(num3.denominator(), 5);
        t.checkExpect(num4.denominator(), 10);
        t.checkExpect(num5.denominator(), 10);

    }

    // void testAdd(Tester t) {
    //     t.checkExpect(num1.add(num2), 5);
    //     t.checkExpect(num1.add(num2).add(num3), 5.2);
    // }

    public static void main(String[] args) {
        Number num1 = new WholeInteger(3);
        Number num2 = new WholeInteger(2);
        Number num3 = new Fraction(1,5);
        // Number num4 = new Fraction(1, 10);
        // Number num5 = new Fraction(3, 10);
        Number val1 = num1.add(num2);
        Number val2 = num1.add(num2).add(num3);

        String output = val2.toString();
        double output1 = val2.toDouble();

        System.out.println(val1);
        //System.out.println(val2);
        System.out.println(output);
        System.out.println(output1);
    }

}

/*
 1. double val = 0.1 + 0.2 + 0.3;
 2. double val2 = 0.1 + (0.2 + 0.3);

    Fraction fraction1 = new Fraction(1,10);
    Fraction fraction2 = new Fraction(1,5);
    Fraction fraction3 = new Fraction(3,10);

    Number res1 = new Fraction fraction1.add(fraction2).add(fractoin3);
    Number res2 = new Fraction fraction2.add(fraction2.add(fraction3));

    3. String res1str = res1.toString();
    4. String res2str = res2.toString();

 */

