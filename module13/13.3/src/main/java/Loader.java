import redis.clients.jedis.Jedis;

import java.util.Map;

public class Loader {
    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);

        client.hset("Иванов И.И", "Web-developer", "1");
        client.hset("Иванов И.И", "Data Science", "4");
        Map<String, String> ivanov = client.hgetAll("Иванов И.И");
        ivanov.keySet().forEach(
                s -> System.out.println("Курс " + s + "  - количество выпполненого ДЗ " + ivanov.get(s)));
        client.hincrBy("Иванов И.И", "Data Science", 1);
        System.out
                .println(
                        "Количество дз по Data Science стало " + client.hget("Иванов И.И", "Data Science"));
    }
}
