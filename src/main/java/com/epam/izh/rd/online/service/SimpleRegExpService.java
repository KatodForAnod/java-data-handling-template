package com.epam.izh.rd.online.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String strings = "";
        try {
            String basePath = new File("").getAbsolutePath();

            Scanner sc = new Scanner(new File(
                    basePath + "\\src\\main\\resources\\sensitive_data.txt"));

            strings = sc.nextLine();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Pattern pattern = Pattern.compile("(?<=\\d\\s)(\\d{4} \\d{4})(?=\\s\\d)");
        Matcher matcher = pattern.matcher(strings);

        return matcher.replaceAll("**** ****");
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String buffer = "";
        try {
            String basePath = new File("").getAbsolutePath();
            Scanner sc = new Scanner(new File(
                    basePath + "\\src\\main\\resources\\sensitive_data.txt"));
            buffer = sc.nextLine();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Pattern pattern = Pattern.compile("\\$\\{payment_amount}");
        Matcher matcher = pattern.matcher(buffer);

        buffer = matcher.replaceAll("" + (int) paymentAmount);

        pattern = Pattern.compile("\\$\\{balance}");
        matcher = pattern.matcher(buffer);

        return matcher.replaceAll("" + (int) balance);
    }
}
