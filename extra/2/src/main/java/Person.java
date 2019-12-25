import lombok.Data;

@Data
public class Person {

    private String surname;
    private String name;
    private String fathername;

    public Person() {
        this("", "", "");
    }

    public Person(String surname, String name, String fathername) {
        this.surname = surname;
        this.name = name;
        this.fathername = fathername;
    }

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
