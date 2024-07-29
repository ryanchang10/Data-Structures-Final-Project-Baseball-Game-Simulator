## Table of Contents
Overview 
Usage
Example
Discussion
Code Errors/Problems


## Overview 
For our final project, we developed a baseball game simulation. In our simulation, players can input team names and specify the number of innings they want simulated. We used hashmaps to store information about each inning, and a queue to progress through the team lineups. We used a Hitter superclass and two subclasses to create three types of hitters: contact, power, and balanced (the superclass hitter with the base hit frequencies). We generated the types of hitters in each team’s lineup randomly, and simulated pitches and the results of those pitches until the simulation completed the user-specified number of innings. The program output includes a breakdown of the hits and runs in each inning, and each team’s total hits and runs for the simulated game.

We chose to create this program because it not only applied the data structures and programming concepts we studied in class but also appealed to our interest in baseball. Looking at a stretch of our project, we envision adding a visual aspect to our project or implementing datasets to simulate real games. For instance, integrating statistics on how well particular hitters perform against certain pitchers would enhance the realism and accuracy of the simulation using real data.

Full project description: 
https://anyaevostinar.github.io/classes/201-w24/final-project


## Usage
After downloading the code, run the following: 

```
$ javac *.java
$ java GameSimulator team1Name team2Name innings
```
where 'team1Name' is the name of the first team, 'team2Name' is the name of the second team, and 'innings' is the desired number of innings you want simulated. 


## Example 
Here is a demonstration of our program running. In the command line, we specified a game between the Yankees and Dodgers lasting 9 innings. 

```
$ javac *.java
$ java GameSimulator.java Yankees Dodgers 9
```
Terminal Output: 

The game's events, in the format of: inning = [hits, runs]:
{top 1=[1, 0], bottom 1=[1, 0], top 2=[1, 0], bottom 2=[1, 0], top 3=[1, 1], bottom 3=[0, 0], top 4=[0, 0], bottom 4=[0, 0], top 5=[2, 4], bottom 5=[1, 0], top 6=[0, 0], bottom 6=[1, 1], top 7=[5, 4], bottom 7=[3, 1], top 8=[4, 4], bottom 8=[0, 0], top 9=[0, 0], bottom 9=[1, 3]}
Team Totals: 
The Yankees scored 13 runs on 14 total hits.
Dodgers scored 5 runs on 8 total hits.
Yankees win!


## Discussion 
Queue:

```
public Queue<Hitter> setLineup(){
       Queue<Hitter> lineup = new LinkedList<>();
```

In lines 54-55 of GameSimulator.java, we create our lineup queue. The lineup represents the order that hitters bat in a baseball game, so a queue was the perfect data type to represent a lineup. We remove the first hitter in the queue to simulate their at bat, then add them to the back of the queue once their at bat is over. 

```
lineup.add(newHitter);
```

HashMap:

```
private LinkedHashMap<String, ArrayList<Integer>> gameInfo;
```

The above code declares our gameInfo variable that stores the information about our game. We used a LinkedHashMap so that it would print each inning's information in chronological order. 

```
this.gameInfo = new LinkedHashMap<String, ArrayList<Integer>>();
```

The above code initializes our gameInfo instance variable as an empty LinkedHashMap with a Strings as keys and ArrayLists of Integers as values. 


Superclass:

```
public class Hitter {
```

Our superclass Hitter creates the instance variables strikeoutRate, hitRate, flyoutRate, and groundoutRate, representing the likelihood of each outcome occurring. In this class, we created hit and pitch result methods that simulate the outcome using a random number generator that determines the outcome of a hit or the outcome of a pitch. Detailed below is how we created subclasses that change these outcomes depending on the type of hitter (contact or power). This superclass is used as a declaration of our variables and methods that are then inherited in our subclasses, detailed below. 

Subclasses:

Our first subclass was a PowerHitter, which inherited from our Hitter class. A power hitter has specific attributes; for example, we wanted a power hitter to have more extra-base hits (doubles and home runs) than contact hitters, along with higher strikeout and groundout probabilities.

Similarly, ContactHitter, our second subclass, implements the same changes to the attributes, but it has different rates for strikeouts and groundouts.

Both subclasses react to different pitches differently than the superclass and each other. For example, a PowerHitter is more likely to take a strike from a curveball and a ContactHitter is more likely to take a strike from a changeup. 


What does your project do that is interesting and substantive?

Our project uses predefined probabilities of outcomes that are very similar to real data from the MLB. It uses random number generators to simulate outcomes, mirroring the variability of baseball games. This project is interesting because it's so real but also affected by randomness. We were able to incorporate different types of hitters using super and subclasses (no lineup in the MLB is comprised of 9 same-typed hitters), as well as imitate a real lineup using a queue. This project incorporates something that we are interested in and creates a realistic baseball game simulation.


Why is inheritance useful for your previously specified superclass and subclasses?

Inheritance is useful because the different types of hitters have the same attributes, which they can inherit from the superclass, but they all have different frequencies of outcomes, which we can change on a per-hitter-type basis. The three hitter types react to various pitch types differently, which adds to the realism of our project. 


For each of the two additional previously-specified class concepts that you used, why is that concept the best to use in your project?

Queues were best to use to represent our batting order because we were able to retrieve and remove the first hitter in the queue for their at-bat, but then add that hitter to the back of the queue. Lineups operate using FIFO, which is a big reason we used queues. 

HashMaps were useful to store game information, and were best because of how easy it is to retrieve information from a HashMap. We could store lists of inning information as the value with the inning number and whether it was the top or bottom of the inning as the key. We used all this information to tally hit and run totals, as well as print the overall game information at the end. 


## Code Errors/Problems

Overall, we didn't run into many errors while coding. One bug we had was with our HashMap printing the same inning information for each inning. This problem ocurred because we were putting the same list object into the HashMap under the same key and just changing the contents of that lists. To fix this, we needed to instantiate a new list rather than use the same list object. 
 

