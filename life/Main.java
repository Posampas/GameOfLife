package life;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int seed = scan.nextInt();
        World world = new World(n,seed);
        System.out.println(world);

    }
}
