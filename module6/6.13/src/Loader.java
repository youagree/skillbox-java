public class Loader {

    public static void main(String[] args) {
        Company yandex = new Company();
        Company google = new Company();
        yandex.addEmployee(10);
        google.addEmployee(10);

        Boss boss = new Boss(100000);
        boss.addCompanyAndBecomeBoss(yandex);
        boss.addCompanyAndBecomeBoss(google);
        boss.topFiveAmongCompanies();

        //check no repeats
        System.out.println(yandex.getIncomeAll());
        System.out.println(yandex.getIncomeAll());

        //try with Enum
        yandex.topFiveSalary(SalaryParam.BIG);
    }
}
