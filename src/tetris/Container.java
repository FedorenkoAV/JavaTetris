package tetris;

import java.util.ArrayList;

/**
 * Класс tetris.Container описывает "поле клеток" игры Тетрис
 */
public class Container {
    //ширина и высота
    private final int width;
    private final int height;

    //матрица поля: любая цифра - клетка занята, 0 - свободна
    private int[][] matrix;

    public Container(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Метод возвращает значение, которое содержится в матрице с координатами (x,y)
     * Если координаты за пределами матрицы, метод возвращает null.
     */
    public Integer getValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    /**
     * Метод устанавливает переданное значение(value) в ячейку матрицы с координатами (x,y)
     */
    public void setValue(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    /**
     * Удаляем заполненные линии
     * возвращает количество удаленных линий
     */
    public int removeFullLines() {
        //Создаем список для хранения линий
        ArrayList<int[]> lines = new ArrayList<>();
        //Копируем все непустые линии в список.
        for (int i = 0; i < height; i++) {
            //подсчитываем количество единиц в строке - просто суммируем все ее значения
            int count = 0;
            for (int j = 0; j < width; j++) {
                if(matrix[i][j] != 0) {
                    count++;
                }
            }
            //Если сумма строки не равна ее ширине - добавляем в список
            if (count != width)
                lines.add(matrix[i]);
        }
        //Добавляем недостающие строки в начало списка.
        int count = 0;
        while (lines.size() < height) {
            lines.add(0, new int[width]);
            count++;
        }
        //Преобразуем список обратно в матрицу
        matrix = lines.toArray(new int[height][width]);
        return count;
    }
}
