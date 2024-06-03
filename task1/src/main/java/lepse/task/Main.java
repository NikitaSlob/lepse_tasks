package lepse.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

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
            Stack<Integer> stack = new Stack<>();

            while (line != null) {
                boolean expressionCorrect = true;
                stack.clear();
                for (char ch : line.toCharArray()) {
                    switch (ch) {
                        case '(':
                            stack.add(0);
                            break;
                        case ')':
                            if (stack.isEmpty() || stack.pop() != 0) {
                                expressionCorrect = false;
                            }
                            break;
                        case '[':
                            stack.add(1);
                            break;
                        case ']':
                            if (stack.isEmpty() || stack.pop() != 1) {
                                expressionCorrect = false;
                            }
                            break;
                        case '{':
                            stack.add(2);
                            break;
                        case '}':
                            if (stack.isEmpty() || stack.pop() != 2) {
                                expressionCorrect = false;
                            }
                            break;
                    }

                    if (!expressionCorrect) {
                        break;
                    }

                }

                if (!stack.isEmpty()) {
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