import java.util.Random;

public class RandomData {

    private static Random random = new Random();

    public static int randomSalary() {
        return (random.nextInt(40000) + 20000);
    }

    public static int randomIncome() {
        return random.nextInt(210000);
    }

    public static int randomEmployee() {
        return (random.nextInt(3)+1) ;
    }

    public static int random () {
        return random.nextInt(270);
    }
}
