package lepse.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    /**
     * 1. Реализовать алгоритм определения правильной скобочной последовательности в выражении, например:
     * {(x * y) + (2 * (x + y))} * [y + 3] - правильная скобочная последовательность.
     * ({((x * y) + [2 * (x - y)]}) - неправильная скобочная последовательность.
     * (x * y) + ) 2 * [x - y]( - неправильная скобочная последовательность.
     * <p>
     * В выражении могут быть использованы круглые "()", фигурные "{}" и квадратные "[]" скобки, а также произвольные печатные символы.
     * Программе на вход передается текстовый файл, содержащий несколько выражений, по одному в каждой строке. Необходимо определить, является ли скобочная последовательность в каждом из выражений правильной и вывести результат на экран в виде:
     * <выражение> - правильная скобочная последовательность
     * или
     * <выражение> - неправильная скобочная последовательность
     */
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(

                Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("expressions.txt"))))) {
            String line = reader.readLine();

            //Считаем каждые скобки

            while (line != null) {

                int round = 0;
                int square = 0;
                int braces = 0;
                boolean expressionCorrect = true;

                for (char ch : line.toCharArray()) {
                    switch (ch) {
                        case '(':
                            round++;
                            break;
                        case ')':
                            round--;
                            break;
                        case '[':
                            square++;
                            break;
                        case ']':
                            square--;
                            break;
                        case '{':
                            braces++;
                            break;
                        case '}':
                            braces--;
                            break;
                    }

                    if (round < 0 || square < 0 || braces < 0) {
                        expressionCorrect = false;
                        break;
                    }

                }

                // Под конец количество открывающих и закрывающих должно быть равно и закрывающих никогда не должно быть больше

                if (round != 0 || square != 0 || braces != 0) {
                    expressionCorrect = false;
                }
                if (expressionCorrect) {
                    System.out.println(line + " - правильная скобочная последовательность.");
                } else {
                    System.out.println(line + " - неправильная скобочная последовательность.");
                }
                line = reader.readLine();

            }
        } catch (Exception exception) {
            System.out.println("Произошла ошибка: " + exception);
        }
    }
}