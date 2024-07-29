import java.util.Random;

/*
 * Pitcher class that represents a baseball pitcher 
 */
public class Pitcher{
    
    public Random randomNumberGenerator;
    
    /**
     * Constructs a Pitcher Object 
     * Initalizes the randomNumberGenerator that will be used to determine type of pitch
     */
    public Pitcher(){
        randomNumberGenerator = new Random();
    }

    /**
     * Generates random pitch type 
     * 
     * @return randomNumberGenerator used to determine pitch type 
     */
    public int pitchType(){
        return randomNumberGenerator.nextInt(3) + 1;
    }

    /**
     * Simulates a pitch in an at bat
     * Determines whether it is a strike
     * 
     * @param pitchType which corresponds to the type of pitch (fastball, curveball, changeup)
     * @return true if the picth is a strike, fale otherwise 
     */
    public boolean pitch(int pitchType){
        int pitchStatus = randomNumberGenerator.nextInt(100) + 1;

        // Randomize three types of pitches (fastball, curveball, changeup), each with different strike rates
        if (pitchType == 1){
            if (pitchStatus < 81){
                return true;
            }
        } else if (pitchType == 2){
            if (pitchStatus < 60){
                return true;
            }
        } else {
            if (pitchStatus < 65){
                return true;
            }
        }
        return false;

    }

}