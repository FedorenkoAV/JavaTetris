package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisGame extends Game {

    public static final int WIDTH = 19;
    public static final int HEIGHT = 21;
    public static final int CONTAINER_WIDTH = 10;
    public static final int CONTAINER_HEIGHT = 20;
    private static final int MAX_TIMER_VALUE = 1000;
    public static final int NEXT_FIGURE_FIELD_HEIGHT = 6;
    public static final int NEXT_FIGURE_FIELD_WIDTH = 6;
    public static final int TEXT_SIZE = 80;
    private static final Color BACKGROUND = Color.GRAY;
    private static final Color CONTAINER_BORDER = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private int score;
    private Figure figure;
    private Figure nextFigure;
    private Container container;
    private int timer;
    private boolean isGameOver;
    private int containerDx;
    private int containerDy;
    private int nextFigureX;
    private int nextFigureY;
    private int infoFieldX;
    private int infoFieldY;
    private boolean isPause;
    private int level;
    private int totalLines;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    /*
    Для создания всех элементов игры
     */
    private void createGame() {
        totalLines = 0;
        score = 0;
        timer = MAX_TIMER_VALUE;
        isGameOver = false;
        figure = FigureFactory.getRandomFigure();
        figure.setX((CONTAINER_WIDTH - figure.getWidth()) / 2.0);
        nextFigure = FigureFactory.getRandomFigure();
        container = new Container(CONTAINER_WIDTH, CONTAINER_HEIGHT);
        containerDx = 1;
        containerDy = 0;
        infoFieldX = 12;
        infoFieldY = 3;
        nextFigureX = infoFieldX + 1;
        nextFigureY = infoFieldY + 1;
        isPause = false;
        setTurnTimer(timer);
        drawOnce();
        drawScene();
        drawNextFigure();
    }

    private void drawOnce() {
        Color color;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (j > CONTAINER_WIDTH + 1) {
                    color = BACKGROUND;
                } else {
                    color = CONTAINER_BORDER;
                }
                setCellValueEx(j, i, color, "");
            }
        }
        print(infoFieldX + 1, 0, BACKGROUND, "LEVEL", TEXT_COLOR, TEXT_SIZE);

        String string = String.valueOf(level + 1);
        print(infoFieldX + 4 - string.length(), 1, BACKGROUND, string, TEXT_COLOR, TEXT_SIZE);
    }

    /*
    Для отрисовки всех элементов игры
     */
    private void drawScene() {
        drawContainer();
        drawFigure();
    }

    /*
    Для отрисовки контейнера
     */
    private void drawContainer() {
        int[][] containerMatrix = container.getMatrix();
        Color color;
        for (int i = 0; i < containerMatrix.length; i++) {
            for (int j = 0; j < containerMatrix[i].length; j++) {
                if (containerMatrix[i][j] == 0) {
                    color = BACKGROUND;
                } else {
                    color = Color.values()[containerMatrix[i][j]];
                }
                setCellValueEx(containerDx + j, containerDy + i, color, "");
            }
        }
    }

    private void drawFigure() {
        Color color;
        int[][] currentMatrix = figure.getCurrentMatrix();
        for (int i = 0; i < currentMatrix[0].length; i++) {//Ширина
            for (int j = 0; j < currentMatrix.length; j++) {//Высота
                if (currentMatrix[j][i] == 0) {
                    color = Color.NONE;
                } else {
                    color = Color.values()[currentMatrix[j][i]];
                }
                setCellValueEx(containerDx + (int) figure.getX() + i, containerDy + (int) figure.getY() + j, color, "");
            }
        }
    }

    private void drawNextFigure() {
        String label = "NEXT:";
        String value = "";
        Color currentColor = TEXT_COLOR;

        //очищаем поле от предыдущей фигуры
        for (int i = 0; i < NEXT_FIGURE_FIELD_HEIGHT; i++) {
            for (int j = 0; j < NEXT_FIGURE_FIELD_WIDTH; j++) {
                setCellValueEx(infoFieldX + 1 + j, infoFieldY + i, BACKGROUND, value, currentColor, TEXT_SIZE);
            }
        }
        print(infoFieldX + 1, infoFieldY, BACKGROUND, label, currentColor, TEXT_SIZE);
        Color color;
        int[][] currentMatrix = nextFigure.getCurrentMatrix();
        for (int i = 0; i < currentMatrix[0].length; i++) {//Ширина
            for (int j = 0; j < currentMatrix.length; j++) {//Высота
                if (currentMatrix[j][i] == 0) {
                    color = Color.NONE;
                } else {
                    color = Color.values()[currentMatrix[j][i]];
                }
                setCellValueEx(nextFigureX + i, nextFigureY + j, color, "");
            }
        }
    }

    private void print(int x, int y, Color backgroundColor, String text, Color textColor, int textSize) {
        for (int i = 0; i < text.length(); i++) {
            setCellValueEx(x + i, y, backgroundColor, String.valueOf(text.charAt(i)), textColor, textSize);
        }
    }

    @Override
    public void onTurn(int step) {
        //Фигурка передвигается на каждом такте игры, поэтому метод
        // moveDown нужно вызывать в методе onTurn. Чтобы сразу увидеть
        // результат движения на экране, нужно вызвать метод moveDown до
        // перерисовки экрана.
        if (isGameOver) {
            stopTurnTimer();
            showMessageDialog(Color.NONE, "Баста карапузики!", Color.RED, 60);
            return;
        }
        moveDown();
        check();
        setScore(score);
        drawScene();
    }

    //Метод check() вспомогательный. В нем проходят различные проверки на каждом шаге игры
    private void check() {
        if (isCurrentPositionAvailable((int) figure.getX(), (int) figure.getY())) {
            return;
        }
        //если разместить фигурку на текущем месте невозможно
        moveUp();               //возвращаем на место
        landed();                //приземляем
        isGameOver = (int) figure.getY() <= 1;//если фигурка приземлилась на самом верху - игра окончена
        int removedLinesCount = container.removeFullLines(); //удаляем заполненные линии
        if (removedLinesCount > 0) { // Если что-то удалили,
            calcScore(removedLinesCount);//то считаем очки, повышаем уровень
        }
        figure = nextFigure; //создаем новую фигурку
        figure.setX((CONTAINER_WIDTH - figure.getWidth()) / 2.0);
        figure.setY(0);
        nextFigure = FigureFactory.getRandomFigure();
        String string = String.valueOf(level + 1);
        print(infoFieldX + 4 - string.length(), 1, BACKGROUND, string, TEXT_COLOR, TEXT_SIZE);
        drawNextFigure();
    }

    private void calcScore(int removedLinesCount) {
        totalLines = totalLines + removedLinesCount;
        if (removedLinesCount == 1) {
            score = score + 100;
        }
        if (removedLinesCount == 2) {
            score = score + 300;
        }
        if (removedLinesCount == 3) {
            score = score + 700;
        }
        if (removedLinesCount == 4) {
            score = score + 1500;
        }
        level = totalLines / 10;
        if (removedLinesCount > 0) {
            timer = MAX_TIMER_VALUE - (level * 50);
            setTurnTimer(timer);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if ((key == Key.SPACE) && (isGameOver)) {
            createGame();
            return;
        }
        if (key == Key.LEFT) {
            moveLeft();
        }
        if (key == Key.RIGHT) {
            moveRight();
        }
        if (key == Key.UP) {
            rotate();
        }
        if (key == tetris.Key.DOWN) {
            moveDown();
        }
        if (key == Key.SPACE) {
            downMaximum();
        }
        if (key == Key.ESCAPE) {
            if (isPause) {
                isPause = false;
                setTurnTimer(timer);
            } else {
                isPause = true;
                stopTurnTimer();
            }
        }
        check();
        setScore(score);
        drawScene();
    }

//    @Override
//    public void on

    void moveLeft() {
        int x = (int) figure.getX();
        x--;
        if (!isCurrentPositionAvailable(x, (int) figure.getY())) {
            return;
        }
        figure.setX(x);
    }

    void moveRight() {
        int x = (int) figure.getX();
        x++;
        if (!isCurrentPositionAvailable(x, (int) figure.getY())) {
            return;
        }
        figure.setX(x);
    }

    void rotate() {
        figure.rotate();
        if (isCurrentPositionAvailable((int) figure.getX(), (int) figure.getY())) {
            return;
        }
        figure.unrotate();
    }

    void moveDown() {
        int y = (int) figure.getY();
        y++;
        figure.setY(y);
    }

    void moveUp() {
        int y = (int) figure.getY();
        y--;
        figure.setY(y);
    }

    /**
     * Двигаем фигурку вниз до тех пор, пока не залезем на кого-нибудь.
     */
    public void downMaximum() {
        int x = (int) figure.getX();
        int y = (int) figure.getY();
        while (isCurrentPositionAvailable(x, y)) {
            y++;
            figure.setY(y);
        }
        y--;
        figure.setY(y);
    }

    /**
     * Проверяем - может ли фигурка находится на текущей позиции:
     * а) не выходит ли она за границы поля
     * б) не заходит ли она на занятые клетки
     */
    public boolean isCurrentPositionAvailable(int x, int y) {
        int[][] gameObjectCurrentMatrix = figure.getCurrentMatrix();
        for (int i = 0; i < gameObjectCurrentMatrix.length; i++) {
            for (int j = 0; j < gameObjectCurrentMatrix[i].length; j++) {
                if (gameObjectCurrentMatrix[i][j] != 0) {
                    if (y + i >= container.getHeight())
                        return false;
                    Integer value = container.getValue(x + j, y + i);
                    if (value == null || value != 0)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Приземляем фигурку - добавляем все ее непустые клетки к клеткам поля.
     */
    public void landed() {
        int line = figure.getHeight();
        int cell = figure.getWidth();
        int[][] matrix = figure.getCurrentMatrix();
        int x = (int) figure.getX();
        int y = (int) figure.getY();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < cell; j++) {
                int color = matrix[i][j];
                if (color != 0) {
                    container.setValue(x + j, y + i, color);
                }
            }
        }
    }

    @Override
    public void start() {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mapColors();
        this.initialize();
        jFrame.addKeyListener(new GameKeyListener());
        jFrame.setTitle("Java Tetris");
        jFrame.setResizable(false);
        jFrame.setUndecorated(false);
        jFrame.getContentPane().add(this.createSwingContent(), "Center");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenu jMenu2 = new JMenu();
        JMenu jMenu3 = new JMenu();
        JMenu jMenu4 = new JMenu();
        JMenuItem jMenuItemExit = new JMenuItem();
        JMenuItem jMenuItemAbout = new JMenuItem();

        jMenuBar.setFocusable(false);
        //jMenuBar.setPreferredSize(new java.awt.Dimension(300, 21));
        jMenu1.setText("Файл");
        jMenu1.setFocusable(false);

        //jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newcalculator/icons/logout-16.png"))); // NOI18N
        jMenuItemExit.setText("Выход");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }

            private void jMenuItemExitActionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar.add(jMenu1);

//        jMenu2.setText("Правка");
//        jMenu2.setFocusable(false);
//
//        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
//        jMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newcalculator/icons/copy.png"))); // NOI18N
//        jMenuItemCopy.setText("Копировать");
//        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemCopyActionPerformed(evt);
//            }
//        });
//        jMenu2.add(jMenuItemCopy);
//
//        jMenuItemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
//        jMenuItemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newcalculator/icons/paste.png"))); // NOI18N
//        jMenuItemPaste.setText("Вставить");
//        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemPasteActionPerformed(evt);
//            }
//        });
//        jMenu2.add(jMenuItemPaste);
//
//        jMenuBar.add(jMenu2);
//
//        jMenu3.setText("Окно");
//        jMenu3.setFocusable(false);
//
//        jCheckBoxMenuItemProtocol.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
//        jCheckBoxMenuItemProtocol.setText("Протокол");
//        jCheckBoxMenuItemProtocol.addItemListener(new java.awt.event.ItemListener() {
//            public void itemStateChanged(java.awt.event.ItemEvent evt) {
//                jCheckBoxMenuItemProtocolItemStateChanged(evt);
//            }
//        });
//        jMenu3.add(jCheckBoxMenuItemProtocol);
//
//        jMenuBar.add(jMenu3);

        jMenu4.setText("Справка");
        jMenu4.setFocusable(false);

        jMenuItemAbout.setText("О программе");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }

            private void jMenuItemAboutActionPerformed(ActionEvent evt) {
                AboutJFrame aboutJFrame = new AboutJFrame();
                aboutJFrame.pack();
                aboutJFrame.setLocationRelativeTo(null);
                aboutJFrame.setVisible(true);
            }
        });
        jMenu4.add(jMenuItemAbout);

        jMenuBar.add(Box.createHorizontalGlue());

        jMenuBar.add(jMenu4);

        jFrame.setJMenuBar(jMenuBar);


        jFrame.pack();
        jFrame.setLocationRelativeTo((Component)null);
        jFrame.setVisible(true);
        System.out.println();
    }

}