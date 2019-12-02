package first_exercise;

import java.util.Set;
import redis.clients.jedis.Jedis;

public class City {

    private Jedis client;

    public City(Jedis client) {
        this.client = client;
    }

    public void start(){
        init();
        showCheapest();
        showExpensive();
        removeKey();
    }

    private void init (){
        client.zadd("first_exercise.City",43000, "Auckland");
        client.zadd("first_exercise.City",37000, "Melbourne");
        client.zadd("first_exercise.City",25000, "Tokyo");
        client.zadd("first_exercise.City",10000, "Reykjavik");
        client.zadd("first_exercise.City",40000, "Vancouver");
        client.zadd("first_exercise.City",34000, "Chicago");
        client.zadd("first_exercise.City",8000, "Oslo");
        client.zadd("first_exercise.City",50000, "Los Angeles");
        client.zadd("first_exercise.City",15000, "Liverpool");
    }

    private void showCheapest(){
        Set<String> city = client.zrange("first_exercise.City", 0, 2);
        System.out.println("the cheapest: " + city);
    }

    private void showExpensive(){
        Set<String> city = client.zrevrange("first_exercise.City", 0, 2);
        System.out.println("the most expensive: " + city);
        System.out.println();
    }

    private void removeKey() {
        client.del("first_exercise.City");
    }
}