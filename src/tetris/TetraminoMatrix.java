package tetris;

public class TetraminoMatrix {

    private TetraminoMatrix() {
    }

    /**
     * Набор из шаблонов для фигурок
     */
    protected static final int[][] BRICK10 = new int[][]{
            {5, 5, 0},                          //X X
            {0, 5, 5}                          //   X X
    };

    protected static final int[][] BRICK11 = new int[][]{
            {0, 5},                          //  X
            {5, 5},                          //X X
            {5, 0}                           //X
    };

    protected static final int[][] BRICK12 = new int[][]{
            {5, 5, 0},                          //X X
            {0, 5, 5}                           //  X X
    };

    protected static final int[][] BRICK13 = new int[][]{
            {0, 5},                          //  X
            {5, 5},                          //X X
            {5, 0}                           //X
    };

    protected static final int[][] BRICK20 = new int[][]{
            {0, 6, 6},                          //  X X
            {6, 6, 0}                           //X X
    };

    protected static final int[][] BRICK21 = new int[][]{
            {6, 0},                          //X
            {6, 6},                          //X X
            {0, 6}                           //  X
    };

    protected static final int[][] BRICK22 = new int[][]{
            {0, 6, 6},                          //  X X
            {6, 6, 0}                           //X X
    };

    protected static final int[][] BRICK23 = new int[][]{
            {6, 0},                          //X
            {6, 6},                          //X X
            {0, 6}                           //  X
    };

    protected static final int[][] BRICK30 = new int[][]{
            {8, 8, 8},                          //X X X
            {0, 8, 0}                           //  X
    };

    protected static final int[][] BRICK31 = new int[][]{
            {0, 8},                          //  X
            {8, 8},                          //X X
            {0, 8}                           //  X
    };

    protected static final int[][] BRICK32 = new int[][]{
            {0, 8, 0},                          //X X X
            {8, 8, 8}                           //  X
    };

    protected static final int[][] BRICK33 = new int[][]{
            {8, 0},                          //X
            {8, 8},                          //X X
            {8, 0}                           //X
    };

    protected static final int[][] BRICK40 = new int[][]{
            {7, 7, 7},                          //X X X
            {0, 0, 7}                           //    X
    };

    protected static final int[][] BRICK41 = new int[][]{
            {0, 7},                          //  X
            {0, 7},                          //  X
            {7, 7}                           //X X
    };

    protected static final int[][] BRICK42 = new int[][]{
            {7, 0, 0},                          //X
            {7, 7, 7}                           //X X X
    };

    protected static final int[][] BRICK43 = new int[][]{
            {7, 7},                          //X X
            {7, 0},                          //X
            {7, 0}                           //X
    };

    protected static final int[][] BRICK50 = new int[][]{
            {4, 4, 4},                          //X X X
            {4, 0, 0}                           //X
    };

    protected static final int[][] BRICK51 = new int[][]{
            {4, 4},                          //X X
            {0, 4},                          //  X
            {0, 4}                           //  X
    };

    protected static final int[][] BRICK52 = new int[][]{
            {0, 0, 4},                          //    X
            {4, 4, 4}                           //X X X
    };

    protected static final int[][] BRICK53 = new int[][]{
            {4, 0},                          //X
            {4, 0},                          //X
            {4, 4}                           //X X
    };

    protected static final int[][] BRICK60 = new int[][]{
            {3, 3},                          //X X
            {3, 3}                           //X X
    };
    protected static final int[][] BRICK61 = new int[][]{
            {3, 3},                          //X X
            {3, 3}                           //X X
    };
    protected static final int[][] BRICK62 = new int[][]{
            {3, 3},                          //X X
            {3, 3}                           //X X
    };
    protected static final int[][] BRICK63 = new int[][]{
            {3, 3},                          //X X
            {3, 3}                           //X X
    };

    protected static final int[][] BRICK70 = new int[][]{
            {28, 28, 28, 28}                          //X X X X
    };

    protected static final int[][] BRICK71 = new int[][]{
            {28},                          //X
            {28},                          //X
            {28},                          //X
            {28}                           //X
    };
    protected static final int[][] BRICK72 = new int[][]{
            {28, 28, 28, 28}                          //X X X X
    };

    protected static final int[][] BRICK73 = new int[][]{
            {28},                          //X
            {28},                          //X
            {28},                          //X
            {28}                           //X
    };
}
