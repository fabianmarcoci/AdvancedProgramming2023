package lab1.compulsory;
import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;

        String BinaryString = "10101";
        int BinaryToInt = Integer.parseInt(BinaryString, 2);
        n += BinaryToInt;

        String HexString = "FF";
        int HexToInt = Integer.parseInt(HexString, 16);
        n += HexToInt;

        n *= 6;

        /**
         * I create a loop to be sure that the new n won't have more than one digit
         * I store the sum in variable s then I move the value in variable n
         */
        while(n > 9) {
            int s = 0;
            while (n > 0) {
                s += n % 10;
                n /= 10;
            }
            n = s;
        }
        int MAX_VAL;
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}