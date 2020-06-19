import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberIn = scanner.nextInt();
        System.out.println(numberIn%2==0?numberIn+2:numberIn+1);
    }
}