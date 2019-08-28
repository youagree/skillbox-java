public class Loader {
    public static void main(String[] args) {

        Individual individual = new Individual();
        System.out.println(individual.getAccount());
        individual.replenishAccount(10);
        System.out.println(individual.getAccount());
        individual.replenishAccount(10);
        individual.withdraw(5);
        System.out.println(individual.getAccount());

        LegalEntity entity = new LegalEntity();
        System.out.println(entity.getAccount());
        entity.replenishAccount(50);
        System.out.println(entity.getAccount());
        entity.replenishAccount(10);
        System.out.println(entity.getAccount());

        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        System.out.println(individualEntrepreneur.getAccount());
        individualEntrepreneur.replenishAccount(100);
        System.out.println(individualEntrepreneur.getAccount());
        individualEntrepreneur.replenishAccount(2000);
        System.out.println(individualEntrepreneur.getAccount());

        Individual individualTransfer = new Individual();
        individualTransfer.replenishAccount(100);

        IndividualEntrepreneur individualEntrepreneurTransfer = new IndividualEntrepreneur();

        individualTransfer.transferOfMoney(individualEntrepreneurTransfer, 500);

        individualEntrepreneurTransfer.transferOfMoney(individualTransfer, 40.0);
        System.out.println(individualTransfer.getAccount());
        System.out.println(individualEntrepreneurTransfer.getAccount());

        entity.transferOfMoney(individualEntrepreneurTransfer, 40);
    }
}
