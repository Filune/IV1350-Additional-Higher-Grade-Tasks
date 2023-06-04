import java.util.Random;

/**
 * An adaptation of the Random class using inheritance.
 */
public class RandomAdapterInheritance extends Random {

    /**
     * Generates a random integer within the specified range and rounds it down to the nearest multiple of 10.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return the rounded random integer
     */
    @Override
    public int nextInt(int min, int max) {
        int randomNumber = super.nextInt(max - min + 1) + min;
        return (randomNumber / 10) * 10;
    }

    /**
     * Generates a random double between 0.0 and 1.0, and scales it to a larger range by multiplying by 1000.
     *
     * @return the scaled random double
     */
    @Override
    public double nextDouble() {
        return super.nextDouble() * 1000;
    }

    /**
     * Generates a random boolean with a bias towards true (70% chance of being true).
     *
     * @return the random boolean
     */
    @Override
    public boolean nextBoolean() {
        return super.nextDouble() < 0.7;
    }

    /**
     * Generates a random float between -1.0 and 1.0.
     *
     * @return the random float
     */
    @Override
    public float nextFloat() {
        return (super.nextFloat() * 2) - 1;
    }
}