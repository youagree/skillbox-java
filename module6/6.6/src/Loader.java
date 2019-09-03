public class Loader {
    public static void main(String[] args) {

//        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
//        individualEntrepreneur.replenishAccount(2000.0);
//        LegalEntity legalEntity = new LegalEntity();
//        individualEntrepreneur.transferOfMoney(legalEntity, 1500.0);
//
//        System.out.println(individualEntrepreneur.getAccount());
//
//        Individual individual = new Individual();
//        individual.transferOfMoney(legalEntity, 100);

        LegalEntity legalEntity = new LegalEntity();
        Individual individual = new Individual();
        individual.replenishAccount(500.0);

        individual.transferOfMoney(legalEntity, 600.0);

        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.replenishAccount(100);
        System.out.println(individualEntrepreneur.getAccount());
    }
}
