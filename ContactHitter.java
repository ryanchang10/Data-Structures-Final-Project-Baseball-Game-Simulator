/**
 * ContactHitter subclass that extends the Hitter class.
 */
public class ContactHitter extends Hitter {
    

    /** 
     * Constructs a ContactHitter object with a 15% strikeout rate and 35% ground out rate.
     */
    public ContactHitter(){
        super();
        this.strikeoutRate = 15;
        this.groundoutRate = 35;
    }

    /**
     * Simulates the outcome of a hit using likelihood probabilities and a random number generator between 1 and 100.
     * 
     * @return An integer corresponding to the result of a hit:
     *         - 1: Single
     *         - 2: Double
     *         - 3: Triple
     *         - 4: Home Run
     */
    public int hit(){

        // Generates a random number, 1-100
        int hitValue = randomNumberGenerator.nextInt(101) + 1;


        if (hitValue > 0 && hitValue < 66){
            // update bases, a single was hit
            return 1;
        } else if (hitValue >= 66 && hitValue < 76){
            // update bases, a double was hit
            return 2;
        } else if (hitValue >= 76 && hitValue < 96){
            // update bases, a triple was hit
            return 3;
        } else {
            // clear bases (update them), update runs, a homerun was hit
            return 4;
        }
    }

    /**
     * Simulates the result of a pitch using a random number generator between 1 and 100.
     * These probabilities are tailored for a contact hitter (does not strike out often, and hits singles and doubles most of the time)
     * @return An integer representing the result of the pitch:
     *         - 1: Strike
     *         - 2: Hit
     *         - 3: Flyout
     *         - 4: Groundout
     */
    public int pitchResult(){
        // return a number for strike, hit, flyout, or groundout
        int result = randomNumberGenerator.nextInt(100) + 1;
        if (result < 16){
            return 1;
        } else if (result >= 16 && result < 40){
            return 2;
        } else if (result >= 40 && result < 76){
            return 3;
        } else {
            return 4;
        }
    }
}
