import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Summary {

    double income;
    double withdraw;

    Summary(double income, double withdraw) {
        this.income = income;
        this.withdraw = withdraw;
    }

    static Summary merge(Summary s1, Summary s2) {
        return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
    }

    static Summary fromTransaction(Customer t) {
        return new Summary(convert(t.getComing()), convert(t.getConsumption()));
    }

    public static double convert(String number) {
        Double afterConvert = Double.parseDouble(number.replace(',', '.').trim());
        return afterConvert;
    }
}