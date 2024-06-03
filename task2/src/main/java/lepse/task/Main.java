package lepse.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    /**
     * 2. Написать программу, выполняющую поиск седловой точки в заданной матрице.
     */
    public static void main(String[] args) {
        // matrix1.txt matrix2.txt matrix3.txt matrix4.txt
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("matrix1.txt"))))) {

            // Парсинг файла

            String line = reader.readLine();
            int length = Integer.parseInt(line.substring(0, 1));
            int width = Integer.parseInt(line.substring(2, 3));
            int[][] matrix = new int[length][width];
            for (int indexI = 0; indexI < length; indexI++) {
                line = reader.readLine();
                int position = 0;
                for (int indexJ = 0; indexJ < width; indexJ++) {
                    int digitLength = 1;
                    while (position + digitLength < line.length()
                            && line.charAt(position + digitLength) != ' ') {
                        digitLength++;
                    }
                    matrix[indexI][indexJ] = Integer.parseInt(line.substring(position,
                            position + digitLength));
                    position += digitLength + 1;
                }
            }

            // Обнаружение точки

            boolean findSomething = false;
            for (int indexI = 0; indexI < length * width; indexI++) {
                int current = matrix[indexI / width][indexI % width];
                boolean sedl = true;
                for (int subi = 0; subi < length; subi++) {
                    if (indexI / width != subi && current < matrix[subi][indexI % width]) {
                        sedl = false;
                        break;
                    }
                }
                for (int subj = 0; subj < width && sedl; subj++) {
                    if (indexI % width != subj && current > matrix[indexI / width][subj]) {
                        sedl = false;
                        break;
                    }
                }
                if (sedl) {
                    findSomething = true;
                    System.out.println("Найдена седловая точка на позиции " + (indexI / width + 1) + ", " + (indexI % width + 1));
                }
            }

            if (!findSomething) {
                System.out.println("Седловых точек не найдено");
            }
        } catch (Exception exception) {
            System.out.println("Произошла ошибка: " + exception);
        }
    }
}