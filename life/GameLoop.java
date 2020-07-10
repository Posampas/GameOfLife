package life;

public class GameLoop {
    ProgramState state;
    World world;
    UserInput input;
    int nubmerOfGenerations;
    boolean isGameActive = true;


    public GameLoop() {
       state(true,false,false,false);
       input = new UserInput();
    }

    private void state (boolean loadData, boolean initialize , boolean inGame, boolean terminated){
        if (loadData) state = ProgramState.LOAD_DATA_FROM_USER;
        else if ( initialize) state = ProgramState.INITIALIZE;
        else if ( inGame ) state = ProgramState.INSIDE_THE_GAME;
        else if (terminated) state = ProgramState.TERMINATED;

    }

    private void gameLoop(){
        while(isGameActive){
            if (state == ProgramState.LOAD_DATA_FROM_USER){
                input.loadTheData();
                nubmerOfGenerations = input.getNumberOfGenerations();
                state(false,true,false,false );
            }else if (state == ProgramState.INITIALIZE){
                world = new World(input.getMatrixDim(),input.getRandomSeed());

                state(false,false,true,false);

            }else if(state == ProgramState.INSIDE_THE_GAME){

                state(false, false, false, true);
            }else if(state == ProgramState.TERMINATED){

                isGameActive=false;
            }


        }
    }

}
