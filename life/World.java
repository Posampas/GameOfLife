package life;
import java.util.Random;

public class World {
    boolean[][] world;
    int n;

    public World(int n, int seed) {
        this.n = n;
        world = generateWorld(new boolean[n][n], seed);
    }

    private boolean[][] generateWorld(boolean[][] world, int seed){
        Random random = new Random(seed);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                world[i][j] = random.nextBoolean();
            }
        }
        return world;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(world[i][j]){
                    result.append('O');
                }else{
                    result.append(' ');
                }
                if (j == n - 1){
                    result.append('\n');
                }
            }
        }
        return result.toString();
    }
    public World nextGeneraion(){

    }
}
