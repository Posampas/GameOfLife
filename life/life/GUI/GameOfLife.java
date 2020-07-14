package life.GUI;

import life.Model.ProgramState;
import life.Model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class GameOfLife extends JFrame {

    static ProgramState programState;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        initComponents();

        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel Alive = new JLabel("Alive");
        JLabel Generation = new JLabel("Generation");
        JLabel AliveLabel = new JLabel("" + 0);
        JLabel GenerationLabel = new JLabel( "" + 1);
        AliveLabel.setName("AliveLabel");
        GenerationLabel.setName("GenerationLabel");

        JToggleButton PlayToggleButton = new JToggleButton("PlayToggleButton");
        PlayToggleButton.setName("PlayToggleButton");

        JButton ResetButton = new JButton("ResetButton");
        ResetButton.setName("ResetButton");


        JPanel north = new JPanel();
        north.setLayout(new GridLayout(2, 3));
        north.add(Alive);
        north.add(AliveLabel);
        north.add(ResetButton);
        north.add(Generation);
        north.add(GenerationLabel);
        north.add(PlayToggleButton);
        add(north, BorderLayout.NORTH);

        WorldPanel worldPanel = new WorldPanel(AliveLabel, GenerationLabel, PlayToggleButton, ResetButton);
        add(BorderLayout.CENTER, worldPanel);





    }

    class WorldPanel extends JPanel implements ActionListener {
        Timer timer = new Timer(500, this);
        boolean isTimerStarted;
        World world;
        JToggleButton startStop;

        JButton reset;

        WorldPanel(JLabel alive, JLabel generation, JToggleButton startStop, JButton reset) {
            this.startStop = startStop;
            this.reset = reset;

            setLayout(new GridLayout());
            world = new World(50,this, alive,generation);

            startStop.addActionListener((e) -> startStopTimer());
            reset.addActionListener((e) -> world = new World(50,this,alive,generation));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                world.nextGeneraion();

                repaint();
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphic = (Graphics2D) g;
            world.drawWorld(graphic);

        }
        private void startStopTimer(){
            if (isTimerStarted){
                timer.stop();
                isTimerStarted = false;
            } else {

                timer.start();
                isTimerStarted = true;
            }
        }


    }


}
