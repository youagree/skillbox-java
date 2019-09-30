import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Loader {


    public static void main(String[] args) throws IOException {
        String csvFile = "src/main/resources/csvTest.csv";

        try (FileReader fileReader = new FileReader(csvFile)){
             List<Customer> beans = new CsvToBeanBuilder<Customer>(fileReader)
                    .withType(Customer.class)
                    .build()
                    .parse();

          beans.stream().collect(Collectors.groupingBy(Customer::getDescription,
                  Collectors.mapping(Summary::fromTransaction,
                  Collectors.reducing(new Summary(0, 0),Summary::merge))))
                  .forEach((a, b) -> System.out.println(a + "\n" + b + "\n"));
        }
    }
}
