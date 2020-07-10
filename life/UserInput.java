package life;

import java.util.Scanner;

public class UserInput {
    private int matrixDim = 0;
    private int randomSeed = 0;
    private int numberOfGenerations =0;
    private static Scanner scanner = new Scanner(System.in);


    public void loadTheData(){

        matrixDim = scanner.nextInt();
        randomSeed = scanner.nextInt();
        numberOfGenerations = scanner.nextInt();
    }

    public int getMatrixDim() {
        return matrixDim;
    }

    public int getRandomSeed() {
        return randomSeed;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }
}
