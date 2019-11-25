package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class MongoDBUtils {

    private static MongoClient mongoClient;
    private static MongoDatabase stores;
    private static MongoCollection<Document> shops;
    private static MongoCollection<Document> products;

    static {
        mongoClient = new MongoClient("localhost", 8080);
        stores = mongoClient.getDatabase("stores");
        shops = stores.getCollection("shop");
        products = stores.getCollection("products");
    }


    public static void addShop(String shopName) {
        if (shopName.isEmpty()) {
            throw new RuntimeException();
        }

        try {
            Document shop = new Document("name", shopName);
            shop.append("products", new ArrayList<String>());

            if (getShop(shopName) == null) {
                MongoDBUtils.shops.insertOne(shop);
                System.out.println("Магазин " + shopName + " добавлен!");
            } else {
                System.out.println("Магазин уже был добавлен!");
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод!");
        }
    }

    public static void addProducts(String input) {
        try {
            String[] var = splitProductNameAndPrice(input);
            String name = var[1];
            Document product = new Document("name", name);
            product.append("price", Integer.parseInt(var[2]));

            if (getProduct(name) == null) {
                products.insertOne(product);
                System.out.println("Продукт " + name + " добавлен!");
            } else {
                System.out.println("Товар уже был добавлен");
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод!");
        }
    }

    public static void addProductsToShop(String input) {
        try {
            String[] var = splitProductNameAndPrice(input);
            String shopName = var[2];
            String productName = var[1];
            shops.updateOne(Filters.eq(getShop(shopName)), new Document("$addToSet",
                    new Document("products", getProduct(productName).get("name"))));
            System.out.println("Продукт " + productName + " добавлен в магазин " + shopName);
        } catch (MongoException e) {
            System.out.println("Магазин или продукт не существуют! ");
            System.out.println(e);
        }
    }

    public static void printStatistic() {
        printInfo(getAggregate());
    }

    private static AggregateIterable<Document> getAggregate() {
        return products.aggregate(
                Arrays.asList(
                        Aggregates.lookup("shop", "name", "products", "shop_list"),
                        Aggregates.unwind("$shop_list"),
                        Aggregates.group("$shop_list.name",
                                Accumulators.sum("count_products", 1),
                                Accumulators.min("min_price", "$price"),
                                Accumulators.max("max_price", "$price"),
                                Accumulators.avg("avg_price", "$price"))
                )
        );
    }

    private static void printInfo(AggregateIterable<Document> documents) throws MongoException {
      try{
        for (Document document : documents) {
          String shopName = (String) document.get("_id");
          System.out.println("Магазин " + shopName);
          System.out.println("Количество товара: " + document.get("count_products"));
          System.out.println("Средняя цена товара: " + document.get("avg_price"));
          System.out.println("Самый дорогой товар:  " + document.get("max_price"));
          System.out.println("Самый дешевый товар:  " + document.get("min_price"));
          System.out
                  .println("Количество товаров, дешевле 100 рублей: " + cheapestProductCount(shopName));
          System.out.println();
        }
      } catch (MongoException e) {
        System.out.println(e);
      }
    }

    private static long cheapestProductCount(String shopName) {
        Document shop = getShop(shopName);
        ArrayList<String> products = (ArrayList<String>) shop.get("products");
        return products.stream().filter(s -> (int) getProduct(s).get("price") < 100).count();
    }

    public static void shutdownDB() {
        stores.drop();
        mongoClient.close();
        System.out.println("Работа завершена!");
    }

    private static Document getShop(String name) {
        return shops.find(new Document("name", name)).first();
    }

    private static Document getProduct(String name) {
        return products.find(new Document("name", name)).first();
    }

    public static String[] split(String var) {
        return var.split(" ", 2);
    }

    public static String[] splitProductNameAndPrice(String var) {
        return var.split(" ", 3);
    }

}
