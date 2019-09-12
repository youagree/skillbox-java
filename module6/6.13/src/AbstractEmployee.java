
public abstract class AbstractEmployee implements Comparable<AbstractEmployee> {

    protected String title;

    public abstract int getSalary();

    public String getTitle() {
        return title;
    }

    public int compareTo(AbstractEmployee o)  {
        return o.getSalary()-this.getSalary();
    };
}
