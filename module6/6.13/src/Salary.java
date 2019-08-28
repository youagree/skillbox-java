abstract class Salary implements Employee, Comparable<Salary>{

    int salary;
    int deferment;
    int income;
    String title;

    String getTitle() {
        return title;
    }
}
