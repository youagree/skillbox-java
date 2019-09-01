public class Loader {
    public static void main(String[] args) {

        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.replenishAccount(2000.0);
        LegalEntity legalEntity = new LegalEntity();
        individualEntrepreneur.transferOfMoney(legalEntity, 1500.0);

        System.out.println(individualEntrepreneur.getAccount());

        Individual individual = new Individual();
        individual.transferOfMoney(legalEntity, 100);
    }
}
