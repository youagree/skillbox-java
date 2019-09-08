public class Loader {
    public static void main(String[] args) {

        Individual individual = new Individual();
        individual.replenishAccount(100.0);
        individual.withdraw(1000.0);
        System.out.println(individual.getBalance());


        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.replenishAccount(100.0);
        individualEntrepreneur.withdraw(1000.0);
        System.out.println(individualEntrepreneur.getBalance());

        LegalEntity legalEntity = new LegalEntity();
        legalEntity.replenishAccount(100.0);
        legalEntity.withdraw(1000.0);
        legalEntity.replenishAccount(50);
        System.out.println(legalEntity.getBalance());

        individual.transferOfMoney(individualEntrepreneur, 1000.0);
        System.out.println(individual.getBalance());

        individualEntrepreneur.transferOfMoney(legalEntity, 1000.0);
        System.out.println(legalEntity.getBalance());

        legalEntity.transferOfMoney(legalEntity, 1000.0);
        System.out.println(individual.getBalance());
    }
}
