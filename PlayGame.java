import java.util.*;

public class PlayGame {

    private static String GRID[][] = {
        {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9"},
        {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9"},
        {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9"},
        {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9"},
        {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9"},
        {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9"},
        {"G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9"},
        {"H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9"},
        {"I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9"}
    };
    private static ArrayList<String> theGrid = new ArrayList<String>();
    private static String[] battleShipOne = new String[2];
    private static String[] battleShipTwo = new String[3];
    private static String[] battleShipThree = new String[4];
    private static List<String> battleShipOneList;
    private static List<String> battleShipTwoList;
    private static List<String> battleShipThreeList;
    private static String[] correctAnswers = new String[8];
    private static String[] incorrectAnswers = new String[73];
    private static ArrayList<String> allGuesses = new ArrayList<String>();
    private static ArrayList<String> validGuesses = new ArrayList<String>();

    public static void main(String[] args) {

        for(int i = 0; i < GRID.length; i++) {
            for(int j = 0; j < GRID[i].length; j++) {
                theGrid.add(GRID[i][j]);
            }
        }

        placeBattleships();
        guessLoc();
    }

    private static void placeBattleships() {
        int count = 0;
        while(count < 3) {
            //Temp Variables
            float tempOrientation = (float) Math.random() * 2;
            float tempX = (float) Math.random() * 7;
            float tempY = (float) Math.random() * 7;

            //Real Variables
            // orientation = 0 (Horizontal) : orientation = 1 (Vertical)
            int orientation = (int) Math.floor(tempOrientation);
            int x = (int) Math.floor(tempX);
            int y = (int) Math.floor(tempY);

            if(count == 0) {
                battleShipOne[0] = GRID[x][y];
                if(orientation == 0) {
                    try {
                        battleShipOne[1] = GRID[x][y+1];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                } else {
                    try {
                        battleShipOne[1] = GRID[x+1][y];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                }
            } else if(count == 1) {
                battleShipTwo[0] = GRID[x][y];
                if(orientation == 0) {
                    try {
                        battleShipTwo[1] = GRID[x][y+1];
                        battleShipTwo[2] = GRID[x][y+2];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                } else {
                    try {
                        battleShipTwo[1] = GRID[x+1][y];
                        battleShipTwo[2] = GRID[x+2][y];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                }
            }  else if(count == 2) {
                battleShipThree[0] = GRID[x][y];
                if(orientation == 0) {
                    try {
                        battleShipThree[1] = GRID[x][y+1];
                        battleShipThree[2] = GRID[x][y+2];
                        battleShipThree[3] = GRID[x][y+3];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                } else {
                    try {
                        battleShipThree[1] = GRID[x+1][y];
                        battleShipThree[2] = GRID[x+2][y];
                        battleShipThree[3] = GRID[x+3][y];
                    } catch (Exception ex) {
                        placeBattleships();
                    }
                }
            }
            count++;
        }
        System.out.println(battleShipOne[0] + " " + battleShipOne[1]);
        battleShipOneList = Arrays.asList(battleShipOne);
        battleShipTwoList = Arrays.asList(battleShipTwo);
        battleShipThreeList = Arrays.asList(battleShipThree);
        if( (battleShipOneList.contains(battleShipTwo[0])) || (battleShipOneList.contains(battleShipTwo[1])) || (battleShipOneList.contains(battleShipTwo[2])) || 
            (battleShipOneList.contains(battleShipThree[0])) || (battleShipOneList.contains(battleShipThree[1])) || (battleShipOneList.contains(battleShipThree[2])) || (battleShipOneList.contains(battleShipThree[3])) || 
            (battleShipThreeList.contains(battleShipTwo[0])) || (battleShipThreeList.contains(battleShipTwo[1])) || (battleShipThreeList.contains(battleShipTwo[2])) ) {
            
            placeBattleships();
        }
    }

    private static void guessLoc() {
        boolean battleshipOneSank = false;
        boolean battleshipTwoSank = false;
        boolean battleshipThreeSank = false;
        int incorrectGuesses = 0;
        int correctGuesses = 0;
        int guesses = 0;
        String myGuess;
        for(int i = 0; i < GRID.length; i++) {
            System.out.println("");
            for(int j = 0; j < GRID[i].length; j++) {
                System.out.print(GRID[i][j] + " ");
            }
        }
        Scanner scanner = new Scanner(System.in);
        while(correctGuesses < 9) {
            if(guesses > 0) {
                System.out.println("\n\nYou've Guessed...\n" + validGuesses);
            }
            System.out.println("\n\nWhats your guess?");
            myGuess = scanner.nextLine();
            myGuess = myGuess.toUpperCase();
            if(allGuesses.contains(myGuess)) {
                System.out.println("\nYou already guessed " + myGuess + ".\nTry Again.");
            } else if(!theGrid.contains(myGuess)) {
                System.out.println("\nInvalid guess.\nTry Again.");
            } else if( (battleShipOneList.contains(myGuess)) || (battleShipTwoList.contains(myGuess)) || (battleShipThreeList.contains(myGuess)) ) {
                System.out.println("Correct");
                correctAnswers[correctGuesses] = myGuess;
                correctGuesses++;
                guesses++;
                validGuesses.add(myGuess);
                displayGrid();
            } else {
                System.out.println("Incorrect");
                incorrectAnswers[incorrectGuesses] = myGuess;
                incorrectGuesses++;
                guesses++;
                validGuesses.add(myGuess);
                displayGrid();
            }
            allGuesses.add(myGuess);
            if( (allGuesses.contains(battleShipOne[0])) && (allGuesses.contains(battleShipOne[1])) && !(battleshipOneSank) ) {
                System.out.println("\n\nYou Sank Battleship One!");
                battleshipOneSank = true;
            }
            if( (allGuesses.contains(battleShipTwo[0])) && (allGuesses.contains(battleShipTwo[1])) && (allGuesses.contains(battleShipTwo[2])) &&!(battleshipTwoSank) ) {
                System.out.println("You Sank Battleship Two!");
                battleshipTwoSank = true;
            }
            if( (allGuesses.contains(battleShipThree[0])) && (allGuesses.contains(battleShipThree[1])) && (allGuesses.contains(battleShipThree[2])) && (allGuesses.contains(battleShipThree[3])) && !(battleshipThreeSank) ) {
                System.out.println("You Sank Battleship Three!");
                battleshipThreeSank = true;
            }
        }
        System.out.println("\n\nYou Win!");
        System.out.println("\nYou guessed " + guesses + " times!");
        System.out.println("\nPress Any Key to Play Again!\nPress Enter to Exit.");
        String playAgain = scanner.nextLine();
        if(playAgain.length() == 0) {
            
            allGuesses.clear();
            validGuesses.clear();

            for(int i = 0; i < GRID.length; i++) {
                for(int j = 0; j < GRID[i].length; j++) {
                    theGrid.add(GRID[i][j]);
                }
            }

            placeBattleships();
            guessLoc();
        }
        scanner.close();
        System.exit(0);
    }

    private static void displayGrid() {
        List<String> correct = Arrays.asList(correctAnswers);
        List<String> incorrect = Arrays.asList(incorrectAnswers);
        System.out.println("\nX = Hit\nO = Miss");
        for(int i = 0; i < GRID.length; i++) {
            System.out.println("");
            for(int j = 0; j < GRID[i].length; j++) {
                if(correct.contains(GRID[i][j])) {
                    System.out.print(" X ");
                } else if(incorrect.contains(GRID[i][j])) {
                    System.out.print(" O ");
                } else {
                    System.out.print(GRID[i][j] + " ");
                }
            }
        }
    }
    
}