package tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FigureFactory {
    private static List<Figure> figureList;
    private static final Random random = new Random();

    private FigureFactory() {
    }

    /**
     * Метод выбирает случайный шаблон и создает с ним новую фигурку.
     */
    public static Figure getRandomFigure() {
        if (figureList == null) {
            createFigureList();
        }
        Figure figure = figureList.get(random.nextInt(figureList.size()));
        figure.setCurrentIndex(random.nextInt(4));
        figure.setX(0.0);
        figure.setY(0.0);
        return figure;
    }

    private static void createFigureList() {
        figureList = new ArrayList<>();

        List<int[][]> figure1 = new ArrayList<>();
        figure1.add(TetraminoMatrix.BRICK10);
        figure1.add(TetraminoMatrix.BRICK11);
        figure1.add(TetraminoMatrix.BRICK12);
        figure1.add(TetraminoMatrix.BRICK13);
        figureList.add(new Figure(figure1));

        List<int[][]> figure2 = new ArrayList<>();
        figure2.add(TetraminoMatrix.BRICK20);
        figure2.add(TetraminoMatrix.BRICK21);
        figure2.add(TetraminoMatrix.BRICK22);
        figure2.add(TetraminoMatrix.BRICK23);
        figureList.add(new Figure(figure2));

        List<int[][]> figure3 = new ArrayList<>();
        figure3.add(TetraminoMatrix.BRICK30);
        figure3.add(TetraminoMatrix.BRICK31);
        figure3.add(TetraminoMatrix.BRICK32);
        figure3.add(TetraminoMatrix.BRICK33);
        figureList.add(new Figure(figure3));

        List<int[][]> figure4 = new ArrayList<>();
        figure4.add(TetraminoMatrix.BRICK40);
        figure4.add(TetraminoMatrix.BRICK41);
        figure4.add(TetraminoMatrix.BRICK42);
        figure4.add(TetraminoMatrix.BRICK43);
        figureList.add(new Figure(figure4));

        List<int[][]> figure5 = new ArrayList<>();
        figure5.add(TetraminoMatrix.BRICK50);
        figure5.add(TetraminoMatrix.BRICK51);
        figure5.add(TetraminoMatrix.BRICK52);
        figure5.add(TetraminoMatrix.BRICK53);
        figureList.add(new Figure(figure5));

        List<int[][]> figure6 = new ArrayList<>();
        figure6.add(TetraminoMatrix.BRICK60);
        figure6.add(TetraminoMatrix.BRICK61);
        figure6.add(TetraminoMatrix.BRICK62);
        figure6.add(TetraminoMatrix.BRICK63);
        figureList.add(new Figure(figure6));

        List<int[][]> figure7 = new ArrayList<>();
        figure7.add(TetraminoMatrix.BRICK70);
        figure7.add(TetraminoMatrix.BRICK71);
        figure7.add(TetraminoMatrix.BRICK72);
        figure7.add(TetraminoMatrix.BRICK73);
        figureList.add(new Figure(figure7));

//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK10, TetraminoMatrix.BRICK11, TetraminoMatrix.BRICK12, TetraminoMatrix.BRICK13)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK20, TetraminoMatrix.BRICK21, TetraminoMatrix.BRICK22, TetraminoMatrix.BRICK23)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK30, TetraminoMatrix.BRICK31, TetraminoMatrix.BRICK32, TetraminoMatrix.BRICK33)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK40, TetraminoMatrix.BRICK41, TetraminoMatrix.BRICK42, TetraminoMatrix.BRICK43)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK50, TetraminoMatrix.BRICK51, TetraminoMatrix.BRICK52, TetraminoMatrix.BRICK53)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK60, TetraminoMatrix.BRICK61, TetraminoMatrix.BRICK62, TetraminoMatrix.BRICK63)));
//        figureList.add(new Figure(List.of(TetraminoMatrix.BRICK70, TetraminoMatrix.BRICK71, TetraminoMatrix.BRICK72, TetraminoMatrix.BRICK73)));
    }
}
