    /**
     * PowerHitter class that represents a hitter 
     * Extends the HitterClass 
     */
    public class PowerHitter extends Hitter{

    /**
     * Constructs PowerHitter object 
     * 
     * Different strike out and groundout rate than contact hitter 
     */
    public PowerHitter(){

        // Call the Hitter constructor, then alter the strikeoutRate and groundoutRate specific to a PowerHitter
        super();
        this.strikeoutRate = 35;
        this.groundoutRate = 15;
    }

    /**
     * Simulates the outcome of a hit for a PowerHitter 
     * @return An integer corresponding to the result of a hit:
     *        - 1: Single
     *        - 2: Double
     *        - 3: Triple
     *        - 4: Home Run
     */
    public int hit(){

        // Generates a random number, 1-100
        int hitValue = randomNumberGenerator.nextInt(101) + 1;


        if (hitValue > 0 && hitValue < 46){
            // update bases, a single was hit
            return 1;
        } else if (hitValue >= 46 && hitValue < 76){
            // update bases, a double was hit
            return 2;
        } else {
            // clear bases (update them), update runs, a homerun was hit
            // Note that a PowerHitter doesn't hit triples
            return 4;
        }
    }

    /**
     * Simulates the result of a pitch for a PowerHitter
     * @return An integer representing the result of the pitch:
     *         - 1: Strike
     *         - 2: Hit
     *         - 3: Flyout
     *         - 4: Groundout
     */
    public int pitchResult(){
        // return a number for strike, hit, flyout, or groundout
        int result = randomNumberGenerator.nextInt(100) + 1;
        if (result < 36){
            return 1;
        } else if (result >= 36 && result < 60){
            return 2;
        } else if (result >= 60 && result < 76){
            return 3;
        } else {
            return 4;
        }
    }
}
