package second_exercise;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

import java.util.ArrayList;

public class SiteLog {

    private Jedis client;
    private  int count = 0;
    String key = "Users";
    ArrayList<String> setOfUsers = new ArrayList<>();
    String CHANNEL = "DONATE_OPTION";

    public SiteLog(Jedis client) {
        this.client = client;
    }

    public void startViewUsers() {
        setOfUsers = generateListOfUsers();
        emulateSiteWork(setOfUsers);
    }

    private ArrayList<String> generateListOfUsers() {
        removeKey();
        for (long i = 0; i < 20; i++) {
            setOfUsers.add("Users " + i);
        }
        return setOfUsers;
    }

    private void emulateSiteWork(ArrayList<String> list) {
        for (;;) {
            list.stream().forEach(e -> {
                count++;
                client.lpush(key, e);
                System.out.println(client.lpop(key));
                waitSomeTime(1000L);
                if (count % 2 == 0){
                    messageConsumer();
                }
            });
        }
    }

    public void waitSomeTime(long second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException | JedisException exx) {
            exx.printStackTrace();
        }
    }

    protected void donateForPaidOption() {
        for (;;) {
            long randomPosition = (long) (0 + 20 * Math.random());
            sendMessage("User " + randomPosition + " buy paid option");
            sendMessage("stop");
            waitSomeTime(3000L);
        }
    }

    private void removeKey() {
        client.del("Users");
    }

    public void messageConsumer() {
        client.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {

                if (message.equals("stop")) {
                    this.unsubscribe();
                } else {
                    this.subscribe(CHANNEL);
                    System.out.println("message receive " + message + " from channel " + channel);
                }
            }

        }, CHANNEL);
    }

    public boolean sendMessage(String message) {
        try {
            client.publish(CHANNEL, message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return false;
    }
}