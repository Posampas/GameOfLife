package life.Model;

import java.awt.*;

public enum CellState {
    ACTIVE {
        @Override
        public Color getColor() {
            return Color.WHITE;
        }
    }
    ,
    NOTACIVE {
        @Override
        public Color getColor() {
            return Color.GRAY;
        }
    };

    public abstract Color getColor();
}

