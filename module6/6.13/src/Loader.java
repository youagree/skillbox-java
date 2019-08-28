import java.util.ArrayList;
import java.util.Collections;

public class Loader {

    public static int incomeAll;

    public static void main(String[] args) {
        incomeAll = 0;
        ArrayList<Salary> allEmployee = new ArrayList<>();

        for (int i = 0; i < 270; i++) {
            switch (RandomData.randomEmployee()) {
                case 1:
                    allEmployee.add(new OperationsOfficer());
                    break;
                case 2:
                    allEmployee.add(new Manager());
                    break;
                case 3:
                    allEmployee.add(new ManagerTop());
                    break;
            }
        }

        Collections.sort(allEmployee);
        System.out.println("Все сотрудники, всего " + allEmployee.size() + " человек.");
        System.out.println("Все менеджеры принесли " + incomeAll + " руб.");
        String premium = incomeAll > 10000000 ? "есть" : "не будет";
        System.out.println("Премия топ manager " + premium);
        allEmployee.forEach((employee) -> {
            System.out.println(employee.getTitle() + "-" + employee.getMonthSalary());
        });

        System.out.println("5 самых больших зарплат - ");
        for (int i = 0; i <= 5; i++) {

            System.out.println(allEmployee.get(i).title + "-" + allEmployee.get(i).getMonthSalary());
        }

        System.out.println("5 самых маленьких зарплат - ");
        for (int i = 269; i >= 264; i--) {

            System.out.println(allEmployee.get(i).title + "-" + allEmployee.get(i).getMonthSalary());
        }
    }

}
