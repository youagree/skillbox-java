package utils;

public class TestData {

    public static void insertTestData(){
        MongoDBUtils.addShop("ДОБАВИТЬ_МАГАЗИН Spar");
        MongoDBUtils.addShop("ДОБАВИТЬ_МАГАЗИН Девяточка");
        MongoDBUtils.addShop("ДОБАВИТЬ_МАГАЗИН Пятерочка");

        MongoDBUtils.addProducts("ДОБАВИТЬ_ТОВАР Вафли 100");
        MongoDBUtils.addProducts("ДОБАВИТЬ_ТОВАР Хлеб 50");
        MongoDBUtils.addProducts("ДОБАВИТЬ_ТОВАР Печенье 80");

        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Девяточка");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Девяточка");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Девяточка");

        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Пятерочка");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Пятерочка");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Пятерочка");

        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Вафли Spar");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Хлеб Spar");
        MongoDBUtils.addProductsToShop("ВЫСТАВИТЬ_ТОВАР Печенье Spar");
    }
}
