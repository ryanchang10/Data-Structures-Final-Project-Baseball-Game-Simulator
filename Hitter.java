import java.util.Random;
/*
 * Hitter superclass that represents baseball hitter 
 */

public class Hitter {

    public int strikeoutRate;
    public int hitRate;
    public int flyoutRate;
    public int groundoutRate;
    public Random randomNumberGenerator;
    public int strikes;
    public int balls;

    /**
     * Constructor for default hitter. Sets basic frequency rates for strikeouts, hits, flyouts, groundouts
     */
    public Hitter(){
        this.strikeoutRate = 25;
        this.hitRate = 25;
        this.flyoutRate = 25;
        this.groundoutRate = 25;
        this.strikes = 0;
        this.balls = 0;
        randomNumberGenerator = new Random();
    }

    /**
     * Simulates the outcome of a hitter
     * Basic values that will then be edited in the Hitter subclasses
     * @return An integer corresponding to the result of a hit:
     *         - 1: Single
     *         - 2: Double
     *         - 3: Triple
     *         - 4: Home Run
     */
    public int hit(){
        // Generates a random number, 1-100
        int hitValue = randomNumberGenerator.nextInt(101) + 1;

        if (hitValue > 0 && hitValue < 56){
            // a single was hit
            return 1;
        } else if (hitValue >= 56 && hitValue < 76){
            // a double was hit
            return 2;
        } else if (hitValue >= 76 && hitValue < 86){
            // a triple was hit
            return 3;
        } else {
            // a homerun was hit
            return 4;
        }
    }

    /** 
     * Simulates the result of a pitch
     * These outcomes will be edited in the Hitter subclasses
     *      @return An integer representing the result of the pitch:
     *         - 1: Strike
     *         - 2: Hit
     *         - 3: Flyout
     *         - 4: Groundout
     */
    public int pitchResult(){
        // return a number for strike, hit, flyout, or groundout
        return randomNumberGenerator.nextInt(4) + 1;
    }

}


