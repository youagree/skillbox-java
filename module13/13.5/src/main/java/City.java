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
        client.zadd("City",43000, "Auckland");
        client.zadd("City",37000, "Melbourne");
        client.zadd("City",25000, "Tokyo");
        client.zadd("City",10000, "Reykjavik");
        client.zadd("City",40000, "Vancouver");
        client.zadd("City",34000, "Chicago");
        client.zadd("City",8000, "Oslo");
        client.zadd("City",50000, "Los Angeles");
        client.zadd("City",15000, "Liverpool");
    }

    private void showCheapest(){
        Set<String> city = client.zrange("City", 0, 2);
        System.out.println("the cheapest: " + city);
    }

    private void showExpensive(){
        Set<String> city = client.zrevrange("City", 0, 2);
        System.out.println("the most expensive: " + city);
        System.out.println();
    }

    private void removeKey() {
        client.del("City");
    }
}