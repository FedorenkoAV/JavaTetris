package tetris;

import java.util.List;

public class Figure {
    // Координаты
    private double x;
    private double y;

    private int width;
    private int height;

    //список возможных положений фигуры
    private final List<int[][]> positionList;

    //номер текущего положения фигуры
    private int currentIndex;

    public Figure(List<int[][]> positionList) {
        this.positionList = positionList;
        currentIndex = 0;
        this.x = 0;
        this.y = 0;
        setCurrentMatrix(positionList.get(currentIndex));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void setCurrentMatrix(int[][] newMatrix) {
        width = newMatrix[0].length;//2
        height = newMatrix.length;//3
    }

    public void setCurrentIndex(int newPosition) {
        if (newPosition >= positionList.size()) {
            return;
        }
        this.currentIndex = newPosition;
        setCurrentMatrix(positionList.get(currentIndex));
    }

    public int[][] getCurrentMatrix() {
        return positionList.get(currentIndex);
    }

    public void rotate() {
        currentIndex++;
        if (currentIndex >= positionList.size()) {
            currentIndex = 0;
        }
        setCurrentMatrix(positionList.get(currentIndex));
    }

    public void unrotate() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = positionList.size() - 1;
        }
        setCurrentMatrix(positionList.get(currentIndex));
    }
}
