import java.util.*;
/*
 * The GameSimulator class simulates a baseball game 
 * Holds game related stats, hits, runs, pitch and hit results
 */
public class GameSimulator {
    public Random randomNumberGenerator;

    private LinkedHashMap<String, ArrayList<Integer>> gameInfo;

    // Represents the status of the bases
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;

    // The score for team 1 and team 2
    private Integer team1Score;
    private Integer team2Score;

    // The total hits for team 1 and team 2
    private Integer team1Hits;
    private Integer team2Hits;

    // The score for the inning, outs, inning, and hits 
    private Integer inningScore;
    private Integer outs;
    private Integer inning;
    private Integer hitsCount;

    // The names of the two teams indicated in the command line argument
    private String team1Name;
    private String team2Name;

    // true represents top of the inning, false represents bottom of the inning. Team1 scores in the top, Team2 scores in the bottom. 
    private boolean halfInning;
    private ArrayList<Integer> inningInfo;

    /**
     * Constructor that sets scores and outs to 0. Also sets the ining to true, starting at the top of the first. 
     */
    public GameSimulator(){
        this.team1Score = 0;
        this.team2Score = 0;
        this.outs = 0;
        this.halfInning = true;
        this.inning = 1;
        this.hitsCount = 0;
        this.inningScore = 0;
        this.team1Hits = 0;
        this.team2Hits = 0;
        this.gameInfo = new LinkedHashMap<String, ArrayList<Integer>>();
        randomNumberGenerator = new Random();

    }

    /**
     * Creates a lineup for the hitters using a Queue
     * 
     * @return lineup made out of a queue of different types of hitters
     */
    public Queue<Hitter> setLineup(){
        Queue<Hitter> lineup = new LinkedList<>();

        // Randomly generate 9 hitters made up of the three different types of hitters (power, contact, regular) and add them to the lineup queue
        while (lineup.size() < 10){
            int hitterType = randomNumberGenerator.nextInt(3) + 1;
            if (hitterType == 1){
                Hitter newHitter = new Hitter();
                lineup.add(newHitter);
                continue;
            } else if (hitterType == 2){
                ContactHitter newContactHitter = new ContactHitter();
                lineup.add(newContactHitter);
                continue;
            } else {
                PowerHitter newPowerHitter = new PowerHitter();
                lineup.add(newPowerHitter);
                continue;
            }
        }
        return lineup;

    }


    /**
     * Helper method for some cases of updateBases. Clears the bases by looping through the array of the bases. Used for triples and home runs
     * 
     * @param team represents the team up to bat
     */
    public void clearBases(int team){
        boolean[] baseList = {firstBase, secondBase, thirdBase};

        // Loop through the bases. If they were true, set them to false and update score of the team
        for (int i = 0; i < 3; i++){
            boolean base = baseList[i];
            if (base == true){
                if (i == 0){
                    firstBase = false;
                } else if (i == 1){
                    secondBase = false;
                } else {
                    thirdBase = false;
                }

                inningScore++;
            }
        }
    }

    /**
     * Upadates the bases depending on the type of hit and the team batting
     * 
     * @param hitType the type of hit:
     *         - 1: Single
     *         - 2: Double
     *         - 3: Triple
     *         - 4: Home Run
     * @param team the Team batting
     */
    public void updateBases(int hitType, int team){

        // Conditionals if a single is hit
        if (hitType == 1){

            // Conditionals for updating bases in different scenarios
            if (firstBase == true && secondBase == true && thirdBase == true){
                // add to the run count, don't need to update bases
                inningScore++;
            } else if (firstBase == false){
                firstBase = true;
            } else if (firstBase == true && secondBase == false){
                secondBase = true;
            } else if (firstBase == true && secondBase == true && thirdBase == false){
                thirdBase = true;
            }

        // Conditionals if a double is hit
        } else if (hitType == 2){

            if (firstBase == true && secondBase == true && thirdBase == true){
                firstBase = false;
                inningScore++;
                inningScore++;
            } else if (firstBase == false && secondBase == false){
                secondBase = true;
            } else if (firstBase == true && secondBase == false && thirdBase == false){
                thirdBase = true;
                secondBase = true;
                firstBase = false;
            } else if (secondBase == true && firstBase == false && thirdBase == true){
                inningScore++;
                inningScore++;
                thirdBase = false;
            } else if (secondBase = true && firstBase == false && thirdBase == false){
                inningScore++;
            } else if (secondBase = true && firstBase == true && thirdBase == false){
                thirdBase = true;
                firstBase = false;
                inningScore++;
            } else {
                inningScore++;
                thirdBase = true;
                secondBase = true;
                firstBase = false;
            }


        // If a triple is hit, clear bases
        } else if (hitType == 3){
            clearBases(team);

            // Hitter is on third base now
            thirdBase = true;

        // If a homerun is hit, clear bases
        } else {
            clearBases(team);
        }
    }

    /**
     * Adds information to store the hits and the runs from each inning in the inningInfo list. 
     * Used to print at the end of the game in the form of a box score
     * 
     * @param tb boolean of the top or bottom of the inning
     */
    public void addInfo(boolean tb){
        inningInfo.add(hitsCount);
        inningInfo.add(inningScore);
        
        // Based on the half inning, add to the overall game info map
        if (tb == true){
            team1Hits = team1Hits + hitsCount;
            team1Score = team1Score + inningScore;
            String gameInfoKey = "top " + inning;
            gameInfo.put(gameInfoKey, inningInfo);
        } else {
            team2Hits = team2Hits + hitsCount;
            team2Score = team2Score + inningScore;
            String gameInfoKey = "bottom " + inning;
            gameInfo.put(gameInfoKey, inningInfo);
        }
    }

    /**
     * Simulates an at bat by taking in a pitch, determining what the hitter does with thta pitch, and returning a boolean if an out was recorded
     * or not. 
     * 
     * @param pitcher the Pitcher currently pitching
     * @param hitter the Hitter currently hitting (taken from the lineup queue)
     * @param balls the current number of balls in the at bat
     * @param strikes the current number of strikes in the at bat
     * @return boolean. Returns true if an out was recorded, false if an out wasn't recorded. 
     */
    public boolean simulateAtBat(Pitcher pitcher, Hitter hitter, int balls, int strikes){
        
        // Checking which team is currently batting
        int team = 1;
        if (halfInning == false){
            team = 2;
        }

        return atBatHelper(pitcher, hitter, balls, strikes, team);
    }

    /**
     * Helper method for simulateAtBat. Performs the recursion aspect of atbats
     * 
     */
    public boolean atBatHelper(Pitcher pitcher, Hitter hitter, int balls, int strikes, int team){
        // Base cases of 4 balls (resulting in a walk) or 3 strikes (resulting in a strikeout)
        if (balls == 4){
            updateBases(1, team);
            return false;
        } else if (strikes == 3){
            outs++;
            return true;
        }

        // Based on the pitch and hitter result if that pitch was a strike, recurse if needed, or return true or false if outs/hits were recorded
        boolean pitchResult = pitcher.pitch(pitcher.pitchType());
        if (pitchResult == true){
            int hitterPitchResult = hitter.pitchResult();
    
            if (hitterPitchResult == 1){
                // Strike was thrown
                strikes++;
                return atBatHelper(pitcher, hitter, balls, strikes, team);
            } else if (hitterPitchResult == 2){
                // flyout
                outs++;
                return true;
            } else if (hitterPitchResult == 3){
                // groundout
                outs++;
                return true;
            } else {
                // hit
                hitsCount++; 
                int hitType = hitter.hit();
                updateBases(hitType, team);
                return false;
            }
        } else {
            balls++;
            return atBatHelper(pitcher, hitter, balls, strikes, team);
        }
    }

    /**
     * Simulates at bats until three outs are made, adds to the game info if necessary 
     * 
     * @param pitcher
     * @param lineup
     */
    public void simulateInning(Pitcher pitcher, Queue<Hitter> lineup){
        int outCount = 0;

        // Simulate at bats until 3 outs are made
        while (outCount < 3){

            // Dequeue to get the current batter, then add them to the back of the queue
            Hitter batter = lineup.remove();
            lineup.add(batter);

            // Simulate at bat, if "true" result, an out was made
            boolean atBat = simulateAtBat(pitcher, batter, 0, 0);
            if (atBat == true){
                outCount++;
            }
            continue;
        }

        inningInfo = new ArrayList<Integer>();

        // Add the hits and runs scored from that inning to the inning info list, then reset those counts for the next inning
        if (halfInning == true){
            addInfo(true);
            hitsCount = 0;
            inningScore = 0;

            halfInning = false;

        } else {
            addInfo(false);
            hitsCount = 0;
            inningScore = 0;

            halfInning = true;
            inning++;
        }
    }


    /**
     * Prints out the game info for hits, runs, and the winner of the game
     */
    public void printGameInfo(){
        // Prints out the hashmap containing the hits and runs per inning
        System.out.println("The game's events, in the format of: inning = [hits, runs]:");
        System.out.println(gameInfo);
        System.out.println();

        int team1score = team1Score;
        int team2score = team2Score;


        // Prints each team's total score and hits at the end of the game
        System.out.println("Team Totals: ");
        System.out.println(team1Name + " scored " + team1score + " runs on " + team1Hits + " total hits.");
        System.out.println(team2Name + " scored " + team2score + " runs on " + team2Hits + " total hits.");


        // Prints out the winner based on score
        if (team1score < team2score){
            System.out.println(team2Name + " wins!");
        } else if (team1score > team2score){
            System.out.println(team1Name + " wins!");
        } else {
            System.out.println(team1Name + " and " + team2Name + " tie.");
        }

       
    }

    /** 
     * Main method that takes in three command line arguments 
     * @args 
     *      - Team 1 Name
     *      - Team 2 Name 
     *      - Number of Innings
     */
    public static void main(String[] args){

        // Usage statement if the user doesn't input 2 team names
        if (args.length != 3){
            System.out.println("Usage: java GameSimulator team1Name team2Name inningsNumber");
            return;
        }

        Pitcher testPitcher = new Pitcher();
        GameSimulator testGS = new GameSimulator();
        Queue<Hitter> lineup = testGS.setLineup();

        testGS.team1Name = args[0];
        testGS.team2Name = args[1];


        // Simulate as many innings as the user specifies
        while (testGS.inning < Integer.parseInt(args[2]) + 1){
            testGS.simulateInning(testPitcher, lineup);
        }

        // Print all the game information
        testGS.printGameInfo();

    }
}
