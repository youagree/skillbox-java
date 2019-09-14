public class Loader {

    public static void main(String[] args) {
        Company yandex = new Company("yandex");
        Company google = new Company("google");
        yandex.addEmployee(10);
        google.addEmployee(10);

        yandex.topFiveSalary(SalaryParam.BIG);
        google.topFiveSalary(SalaryParam.BIG);


        //check no repeats
        System.out.println(yandex.getIncomeAll());
        System.out.println(yandex.getIncomeAll());

        ManagerTop managerTop = new ManagerTop();
        System.out.println(managerTop.getSalary(yandex));
    }
}
