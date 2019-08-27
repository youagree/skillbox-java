public class Loader {
    public static void main(String[] args) {

        Individual individual = new Individual();
        System.out.println(individual.getAccount());
        individual.setAccountAdd(10);
        System.out.println(individual.getAccount());
        individual.setAccountAdd(10);
        individual.setAccountRemove(5);
        System.out.println(individual.getAccount());


        LegalEntity entity = new LegalEntity();
        System.out.println(entity.getAccount());
        entity.setAccountAdd(50);
        System.out.println(entity.getAccount());
        entity.setAccountRemove(10);
        System.out.println(entity.getAccount());

        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        System.out.println(individualEntrepreneur.getAccount());
        individualEntrepreneur.setAccountAdd(100);
        System.out.println(individualEntrepreneur.getAccount());
        individualEntrepreneur.setAccountAdd(2000);
        System.out.println(individualEntrepreneur.getAccount());
    }
}
