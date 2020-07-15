package ru.ayupov.task;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Написать программу, которая копирует файл с одной кодировкой в файл с другой.
 */

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("в параметрах нужно указать:");
            System.out.println("имя входного файла");
            System.out.println("кодировку входного файла");
            System.out.println("имя выходного файла");
            System.out.println("кодировку выходного файла");

            //return;
            args = ("text(utf8).txt utf-8 text(windows-1251).txt windows-1251").split(" "); // TODO: 15.07.2020 Для тестирования, если программа запускается без параметров
            System.out.println("Аргументы запуска программы отсутсвуют. Используются тестовые данные");
        }

        String filename1 = args[0];
        String charset1 = args[1];
        String filename2 = args[2];
        String charset2 = args[3];

        convertCharset(filename1, charset1, filename2, charset2);

    }

    private static void convertCharset(String oldFileName, String oldCharset, String newFileName, String newCharset){
        try (FileInputStream fis = new FileInputStream(oldFileName);
             InputStreamReader isr = new InputStreamReader(fis, oldCharset);
             BufferedReader br = new BufferedReader(isr)) {

            writeFile(newFileName, newCharset, br);

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void writeFile(String fileName, String charset, BufferedReader br){
        String txt;

        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
             BufferedWriter bw = new BufferedWriter(osw)) {

            while ((txt = br.readLine()) != null) {
                bw.write(txt);
                bw.newLine();
                bw.flush();
            }
        }catch (IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }



}
