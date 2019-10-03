import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Customer {

    @CsvBindByName(column = "Тип счёта", required = true)
    private String type;

    @CsvBindByName(column = "Номер счета", required = true)
    private String number;

    @CsvBindByName(column = "Валюта", required = true)
    private String value;

    @CsvBindByName(column = "Дата операции", required = true)
    private String dateOperations;

    @CsvBindByName(column = "Референс проводки", required = true)
    private String refWiring;

    @CsvBindByName(column = "Описание операции", required = true)
    private String description;

    @CsvBindByName(column = "Приход", required = true)
    private String coming;

    @CsvBindByName(column = "Расход", required = true)
    private String consumption;

    public static String getNameCompany(Customer customer) {
        String[] transaction = customer.description.trim().split(" {3,}");
        String[] temp = transaction[1].split("/");
        String[] suppliesTemp = temp[temp.length - 1].split("\\\\");
        String supplies = suppliesTemp[suppliesTemp.length - 1];
        return supplies;
    }
}
