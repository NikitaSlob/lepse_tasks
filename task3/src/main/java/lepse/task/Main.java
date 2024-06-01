package lepse.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    /**
     * 3. Дана строка, представляющая собой идентификатор переменной. Идентификатор может содержать только латинские буквы и символы подчеркивания "_".
     * Идентификатор написан с использованием "snake_case", если он начинается со строчной буквы, заканчивается строчной буквой, содержит только строчные буквы и одиночные символы подчеркивания (т.е. несколько подряд символов подчеркивания не допускаются).
     * Идентификатор написан с использованием "camelCase", если он начинается со строчной буквы и содержит только строчные и прописные буквы.
     * <p>
     * Нужно написать программу (метод), преобразующую входную строку из одного формата в другой.
     * Т.е. если входная строка соответствует формату "snake_case", то ее надо преобразовать в формат "camelCase" и наоборот. Если на вход программе передана строка, не соответствующая ни одному из этих форматов - выдать сообщение об ошибке.
     * <p>
     * Например:
     * <p>
     * "some_variable"     ->  "someVariable"
     * "tryToConvertMe" ->  "try_to_convert_me"
     * "unchanged"           ->   "unchanged"
     * "InvalidMethod"     ->  "Error!"
     * "bad_VarName"     ->  "Error!"
     */
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("lines.txt"))))) {

            //Проверка названия

            String line = reader.readLine();
            while (line != null) {
                String newLine = line;
                boolean correct = true;
                boolean camel = false;
                boolean snake = false;

                // Проверка первого символа

                if (newLine.charAt(0) == '_' || Character.isUpperCase(newLine.charAt(0))) {
                    correct = false;
                }
                for (int indexI = 1; correct && indexI < newLine.length(); indexI++) {
                    //Проверка camelCase
                    if (Character.isUpperCase(newLine.charAt(indexI))) {
                        if (snake) {
                            //Если уже были прочерки то ошибка
                            correct = false;
                            break;
                        }
                        camel = true;
                        StringBuilder builder = new StringBuilder(newLine).insert(indexI, "_");
                        indexI++;
                        builder.replace(indexI, indexI + 1, String.valueOf(Character.toLowerCase(builder.charAt(indexI))));
                        newLine = builder.toString();
                    } else if (newLine.charAt(indexI) == '_') {
                        //Проверка snake_case
                        if (camel || (indexI + 1 != newLine.length() && newLine.charAt(indexI + 1) == '_')) {
                            //Если уже были заглавные буквы или несколько прочерков подряд то ошибка
                            correct = false;
                            break;
                        }
                        snake = true;
                        StringBuilder builder = new StringBuilder(newLine).replace(indexI, indexI + 1, "");
                        builder.replace(indexI, indexI + 1, String.valueOf(Character.toUpperCase(builder.charAt(indexI))));
                        newLine = builder.toString();
                    }
                }
                if (correct) {
                    System.out.println(line + " -> " + newLine);
                } else {
                    System.out.println(line + " -> " + "Error!");
                }
                line = reader.readLine();
            }
        } catch (Exception exception) {
            System.out.println("Произошла ошибка: " + exception);
        }
    }
}