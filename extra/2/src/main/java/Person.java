import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String surname;
    private String name;
    private String fathername;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String value : new String[]{getSurname(), getName(), getFathername()}) {
            if (builder.length() > 0 && value != null && value.length() > 0)
                builder.append(" ");
                builder.append(value);
        }
        return builder.toString();
    }
}
