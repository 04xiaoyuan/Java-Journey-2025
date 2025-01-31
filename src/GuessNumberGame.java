import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        System.out.println("请您猜数字，范围为1~100，请输入:");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while (true) {
            if (num == number) {
                System.out.println("恭喜你，猜对了！");
                break;
            } else if (num > number) {
                System.out.println("猜大了");
                num = sc.nextInt();
            } else {
                System.out.println("猜小了");
                num = sc.nextInt();
            }


        }
    }
}