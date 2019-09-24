import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Loader {
    public static void main(String[] args) throws IOException {
        String csvFile = "src/main/resources/csvTest.csv";

        List<Customer> beans = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Customer.class)
                .build()
                .parse();


        double allComing = beans.stream().mapToDouble(e -> Double.parseDouble(e.getComing())).sum();
        double allConsumption = beans.stream()
                .map(e -> e.getConsumption().replace(',', '.').trim())
                .mapToDouble(e -> Double.parseDouble(e)).sum();

        System.out.println("общий приход " + allComing);
        System.out.println("общий расход " + allConsumption);

        beans.stream().sorted(Comparator.comparing(Customer::getDescription)).forEach(System.out::println);
    }
}
