import java.util.Scanner;

public class Main {

    /*
    ABCCCAAAA
    00 0 01 00 10 101 0 001 01 11 0

     */
    public static void main(String[] args) {
        AdaptiveHuffmanEncoder encoder = new AdaptiveHuffmanEncoder(8);
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        encoder.encode(s);
    }
}
