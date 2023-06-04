public class Main {

    public static void main(String[] args) {
        System.out.println("\n---Inheritance---\n");
        RandomAdapterInheritance randomInheritance = new RandomAdapterInheritance();
        int randomNumberInheritance = randomInheritance.nextInt(1, 100);
        System.out.println("Random number using inheritance: " + randomNumberInheritance);

        double randomDoubleInheritance = randomInheritance.nextDouble();
        System.out.println("Random double using inheritance: " + randomDoubleInheritance);

        boolean randomBooleanInheritance = randomInheritance.nextBoolean();
        System.out.println("Random boolean using inheritance: " + randomBooleanInheritance);

        float randomFloatInheritance = randomInheritance.nextFloat();
        System.out.println("Random float using inheritance: " + randomFloatInheritance);

        System.out.println("\n---Composition---\n");

        RandomAdapterComposition randomComposition = new RandomAdapterComposition();
        int randomNumberComposition = randomComposition.nextInt(1, 100);
        System.out.println("Random number using composition: " + randomNumberComposition);

        double randomDoubleComposition = randomComposition.nextDouble();
        System.out.println("Random double using composition: " + randomDoubleComposition);

        boolean randomBooleanComposition = randomComposition.nextBoolean();
        System.out.println("Random boolean using composition: " + randomBooleanComposition);

        float randomFloatComposition = randomComposition.nextFloat();
        System.out.println("Random float using inheritance: " + randomFloatComposition);
    }
}
