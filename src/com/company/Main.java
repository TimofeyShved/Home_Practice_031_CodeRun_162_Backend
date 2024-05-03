package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;

public class Main {

    /*
	Для чтения входных данных необходимо получить их
	из стандартного потока ввода (System.in).
	Данные во входном потоке соответствуют описанному
	в условии формату. Обычно входные данные состоят
	из нескольких строк. Можно использовать более производительные
	и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.

	С помощью BufferedReader можно прочитать из стандартного потока:
	* строку -- reader.readLine()
	* число -- int n = Integer.parseInt(reader.readLine());
	* массив чисел известной длины (во входном потоке каждое число на новой строке) --
	int[] nums = new int[len];
    for (int i = 0; i < len; i++) {
        nums[i] = Integer.parseInt(reader.readLine());
    }
	* последовательность слов в строке --
	String[] parts = reader.readLine().split(" ");

	Чтобы вывести результат в стандартный поток вывода (System.out),
	Через BufferedWriter можно использовать методы
	writer.write("Строка"), writer.write('A') и writer.newLine().

	Возможное решение задачи "Вычислите сумму чисел в строке":
	int sum = 0;
    String[] parts = reader.readLine().split(" ");
    for (int i = 0; i < parts.length; i++) {
        int num = Integer.parseInt(parts[i]);
        sum += num;
    }
    writer.write(String.valueOf(sum));
	*/
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] loveNum = {5,6,10};

        String n = reader.readLine();
        int k = Integer.parseInt(reader.readLine());

        HashSet<String> list = perebor(n, k);

        for (String s : list){
            System.out.println(s);
        }

        double rez = isLoveNum(list, loveNum);
        Formatter formatter = new Formatter();
        formatter.format("%.10f\n", rez);
        System.out.println(formatter);

        //writer.write(""+rez);

        reader.close();
        writer.close();
    }

    public static HashSet<String> perebor(String n, int k){
        HashSet<String> list = new HashSet<String>();
        char[] c = n.toCharArray();
        for (int i = 0; i < c.length; i++){
            for (int j = i; j < c.length; j++) {
                if (i != j){
                    char newC = c[i];
                    c[i] = c[j];
                    c[j] = newC;
                    String s = new String(c);
                    list.add(s);
                    newC = c[j];
                    c[j] = c[i];
                    c[i] = newC;
                }
            }
        }
        k--;
        if (k>0){
            HashSet<String> list_2 = new HashSet<String>();
            for (String s : list){
                list_2.addAll(perebor(s, k));
            }
            return list_2;
        }
        return list;
    }

    public static double isLoveNum(HashSet<String> h, int[] k){
        int countTrue = 0;
        for (String s : h){
            for (int i = 0; i < k.length; i++){
                if ((Integer.parseInt(s) % k[i]) == 0){
                    countTrue++;
                    continue;
                }
            }
        }
        return (double )countTrue / (double )h.size();
    }

}
