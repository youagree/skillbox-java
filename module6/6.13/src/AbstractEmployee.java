
public abstract class AbstractEmployee implements Comparable<AbstractEmployee> {

    protected String title;

    public abstract int getSalary();

    public String getTitle() {
        return title;
    }

    public abstract int compareTo(AbstractEmployee o);
}
