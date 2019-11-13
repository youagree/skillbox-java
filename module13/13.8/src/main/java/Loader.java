import static utils.MongoUtils.addProducts;
import static utils.MongoUtils.addProductsToShop;
import static utils.MongoUtils.addShop;
import static utils.MongoUtils.printStatistic;
import static utils.MongoUtils.shutdownDB;
import static utils.MongoUtils.split;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {

    private static String commandExample = "Примеры команд:"
            + "\n - ДОБАВИТЬ_МАГАЗИН Девяточка"
            + "\n - ДОБАВИТЬ_ТОВАР Вафли 54"
            + "\n - ВЫСТАВИТЬ_ТОВАР Вафли Девяточка"
            + "\n - СТАТИСТИКА_ТОВАРОВ"
            + "\n - EXIT - завершает работу программы";


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(commandExample);

        for (; ; ) {
            System.out.println("Введите команду:");
            String[] input = split(reader.readLine().trim());

            String instruction = input[0];
            String object = input.length > 1 ? input[1] : "";

            switch (instruction) {
                case "ДОБАВИТЬ_МАГАЗИН":
                    addShop(object);
                    break;
                case "ДОБАВИТЬ_ТОВАР":
                    addProducts(object);
                    break;
                case "ВЫСТАВИТЬ_ТОВАР":
                    addProductsToShop(object);
                    break;
                case "СТАТИСТИКА_ТОВАРОВ":
                    printStatistic();
                    break;
                case "EXIT":
                    shutdownDB();
                    return;
                default:
                    printError();
            }
        }
    }

    private static void printError() {
        System.out.println("Неверный ввод!");
        System.out.println(commandExample);
    }
}
