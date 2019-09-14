
public abstract class AbstractEmployee {

    protected String title;

    public abstract int getSalary(Company company);

    public String getTitle() {
        return title;
    }
}
