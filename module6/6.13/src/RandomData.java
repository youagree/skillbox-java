import java.util.Random;

public class RandomData {

    public static int randomSalary() {
        Random random = new Random();
        return (random.nextInt(40000) + 20000);
    }

    public static int randomIncome() {
        Random random = new Random();
        return random.nextInt(210000);
    }

    public static int randomEmployee() {
        Random random = new Random();
        return (random.nextInt(3)+1) ;
    }


}
