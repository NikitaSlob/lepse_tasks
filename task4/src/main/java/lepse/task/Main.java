package lepse.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    /**
     * 4. Даны 6 прямоугольных фанерных листов. Нужно определить, можно ли из этих листов собрать коробку (прямоугольный параллелепипед).
     * Размеры фанерных листов в сантиметрах записаны в текстовом файле, по 2 числа (длина и ширина) в каждой строке, разделенные пробелом, всего 6 строк.
     * Все размеры целочисленные, от 1 до 1000. Написать программу, которая считывает размеры фанерных листов из файла и определяет, возможно или невозможно из этого набора листов собрать коробку (см. рис.).
     * Толщина фанерного листа не учитывается.  На выходе программа должна написать "Возможно" или "Невозможно".
     */
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("box1.txt"))))) {

            int[][] matrix = new int[6][2];
            for (int i = 0; i < 6; i++) {
                String line = reader.readLine();
                int position = 0;
                for (int j = 0; j < 2; j++) {
                    int length = 1;
                    while (position + length < line.length()
                            && line.charAt(position + length) != ' ') {
                        length++;
                    }
                    matrix[i][j] = Integer.parseInt(line.substring(position,
                            position + length));
                    position += length + 1;
                }
            }

            //Для каждой стороны должны быть сторона с такими же длиной и шириной (противоположная) и как минимум 2 с такой же длиной и 2 с такой же длиной шириной (смежные стороны)

            boolean box = true;
            for (int i = 0; i < 6; i++) {
                boolean opposite = false;
                int neighboursLength = 0;
                int neighboursWidth = 0;
                for (int j = 0; j < 6; j++) {
                    if (i != j) {
                        if ((matrix[i][0] == matrix[j][0] && matrix[i][1] == matrix[j][1])
                                || (matrix[i][1] == matrix[j][0] && matrix[i][0] == matrix[j][1])) {
                            opposite = true;
                        } else if (matrix[i][1] == matrix[j][0] || matrix[i][1] == matrix[j][1]) {
                            neighboursLength += 1;
                        } else if (matrix[i][0] == matrix[j][0] || matrix[i][0] == matrix[j][1])  {
                            neighboursWidth += 1;
                        }
                    }
                }
                if (!opposite || neighboursLength < 2 || neighboursWidth < 2) {
                    box = false;
                    break;
                }
            }
            if (box) {
                System.out.println("Возможно");
            } else {
                System.out.println("Невозможно");
            }
        } catch (Exception exception) {
            System.out.println("Произошла ошибка: " + exception);
        }
    }
}