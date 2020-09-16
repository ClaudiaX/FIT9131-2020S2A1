import java.util.Scanner;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{

    private Player player1;
    private Player player2;
    private Dice dice;
    private boolean gameStart;
    private String lastResult;

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        player1 = new Player();
        player2 = new Player();
        dice = new Dice();
        gameStart = false;
        lastResult = "";
    }

    private void displayMenu()
    {
        System.out.println("Welcome to the Simple Board Game");
        System.out.println("================================");
        System.out.println("(1) Start a New Game");
        System.out.println("(2) Play One Round");
        System.out.println("(3) Display Players' Positions");
        System.out.println("(4) Display Game Help");
        System.out.println("(5) Exit Game");
        System.out.print("Choose an option:");
    }

    public void mainController()
    {
        Scanner sc = new Scanner(System.in);
        boolean continous = true;
        while(continous)
        {
            displayMenu();
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                checkStart();
                // setPlayerName();
                break;

                case 2:
                playOneRound();
                break;

                case 3:
                displayPosition();
                break;

                case 4:
                displayHelp();
                break;

                case 5:
                // example 1
                // continous = false;

                // example 2
                // System.exit(0);

                // example 3
                exitGame();
                break;

                default:
                System.out.println("Error! Please enter a number between 1-5!");
                break;
            }

            // use if
            // if(choice == 1)
            // setPlayerName();
            // else if(choice == 2)
            // playOneRound();
            // else if (choice == 3)
            // displayPosition();
            // else if(choice == 4)
            // displayHelp();
            // else if(choice == 5)
            // exitGame();
            // else
            // System.out.println("Error! Please enter a number between 1-5!");
        }
    }

    private void checkStart()
    {
        Scanner sc = new Scanner(System.in);
        if(gameStart)
        {
            System.out.println("Do you want to start a new game?");
            System.out.println("Enter 1 to start a new game");
            String input = sc.nextLine();
            if(input.equals("1"))
                setPlayerName();
        }
        else
            setPlayerName();
    }

    private void setPlayerName()
    {
        Scanner sc = new Scanner(System.in);

        //player 1
        System.out.print("Enter First Player's Name: ");
        String name1 = sc.nextLine();
        // if name.trim().length() == 0
        // name.trim().equals(null) X
        while(name1.trim().equals(""))
        {
            System.out.println("Error! Please enter a valid name!");
            System.out.print("Enter First Player's Name: ");
            name1 = sc.nextLine();
        }
        player1 = new Player();
        player1.setName(name1.trim());
        //player 2
        String name2 = "";
        do{
            System.out.print("Enter Second Player's Name: ");
            name2 = sc.nextLine();
        }
        while(name2.trim().length() == 0);
        player2 = new Player();
        player2.setName(name2.trim());

        gameStart = true;

    }

    private void playOneRound()
    {
        if(!gameStart && lastResult.equals(""))
        {
            System.out.println("Error: players has not been set up!");
        }
        else if(!gameStart && lastResult.length() != 0)
        {
            System.out.println("Game finished. You must start a new game");
            System.out.println(lastResult);
        }
        else{
            // player 1 roll dice
            int previous1 = player1.getPosition();
            int rollNumber1 = dice.rollDice();
            int nowPosition1 = previous1 + rollNumber1;
            // check penalty
            if(checkPenalty(nowPosition1))
            {
                int newPosition1 = nowPosition1 - 5;
                player1.setPosition(newPosition1);
                // penalty + 1, no increase method
                // int penalty = player1.getPenalty() + 1
                // player1.setPenalty(penalty)
                player1.increasePenalty(); // need increase method
                System.out.println(player1.getName() + " rolled a " + rollNumber1 + ", and moves from position " + previous1 +" to " + newPosition1 + " [Penalty applied]");
            }
            // check bonus - twice
            else if(checkBonus(nowPosition1))
            {
                System.out.println(player1.getName() + " rolled a " + rollNumber1 + ", and moves from position " + previous1 +" to " + nowPosition1 + " [Bonus Immunity Roll]");
                // second roll
                int newRoll1 = dice.rollDice();
                int newPosition1 = nowPosition1 + newRoll1;
                player1.setPosition(newPosition1);
                // get,set bonus + 1
                player1.increaseBonus();
                System.out.println(player1.getName() + " rolled a " + newRoll1 + ", and moves from position " + nowPosition1 +" to " + newPosition1);
            }
            else
            {
                player1.setPosition(nowPosition1);
                System.out.println(player1.getName() + " rolled a " + rollNumber1 + ", and moves from position " + previous1 +" to " + nowPosition1);
            }

            // player 2
            int previous2 = player2.getPosition();
            int rollNumber2 = dice.rollDice();
            int nowPosition2 = previous2 + rollNumber2;
            // check penalty
            if(checkPenalty(nowPosition2))
            {
                int newPosition2 = nowPosition2 - 5;
                player2.setPosition(newPosition2);
                // penalty + 1, no increase method
                // int penalty = player2.getPenalty() + 1
                // player2.setPenalty(penalty)
                player2.increasePenalty();
                System.out.println(player2.getName() + " rolled a " + rollNumber2 + ", and moves from position " + previous2 +" to " + newPosition2 + " [Penalty applied]");
            }
            // check bonus - twice
            else if(checkBonus(nowPosition2))
            {
                System.out.println(player2.getName() + " rolled a " + rollNumber2 + ", and moves from position " + previous2 +" to " + nowPosition2 + " [Bonus Immunity Roll]");
                // second roll
                int newRoll2 = dice.rollDice();
                int newPosition2 = nowPosition2 + newRoll2;
                player2.setPosition(newPosition2);
                // get,set bonus + 1
                player2.increaseBonus();
                System.out.println(player2.getName() + " rolled a " + newRoll2 + ", and moves from position " + nowPosition2 +" to " + newPosition2);
            }
            else
            {
                player2.setPosition(nowPosition2);
                System.out.println(player2.getName() + " rolled a " + rollNumber2 + ", and moves from position " + previous2 +" to " + nowPosition2);
            }

            // p1 > p2 >=50
            // p1 >= 50 > p2

            // p2 > p1 >= 50
            // p2 >= 50 > p1

            // p1 = p2 >= 50

            if(player1.getPosition() >= 50 && player1.getPosition() > player2.getPosition())
            {
                gameStart = false;
                System.out.println("*** Congratulations, "+ player1.getName() +" have WON this game!! ***");
                lastResult = "The last game was won by Player " + player1.getName();
            }
            else if(player2.getPosition() >= 50 && player2.getPosition() > player1.getPosition())
            {
                gameStart = false;
                System.out.println("*** Congratulations, "+ player2.getName() +" have WON this game!! ***");
                lastResult = "The last game was won by Player " + player2.getName();
            }
            else if( player1.getPosition() == player2.getPosition() && player2.getPosition() >= 50)
            {
                gameStart = false;
                System.out.println("*** This game is even ***");
                lastResult = "Last game was even";
            }
        }

    }

    private boolean checkPenalty(int position)
    {
        if(position % 11 == 0 && position < 50)
            return true;
        else
            return false;
    }

    private boolean checkBonus(int position)
    {
        if(position % 5 == 0 && position % 10 != 0 && position <= 35)
            return true;
        else
            return false;
    }

    private void displayPosition()
    {
        if(!gameStart && lastResult.equals(""))
        {
            System.out.println("Error: players has not been set up!");
        }
        else if(!gameStart && lastResult.length() != 0)
        {
            System.out.println("Game finished. You must start a new game");
            System.out.println(lastResult);
        }
        else
        {
            System.out.println("Player " + player1.getName() + " is on position " + player1.getPosition() + " [Penalties: " + player1.getPenalty() + ", Bonuses: " + player1.getBonus() + "]");
            System.out.println(String.format("Player %s is on position %s [Penalties: %s, Bonuses: %s]", player2.getName(), player2.getPosition(), player2.getPenalty(), player2.getBonus()));
        }
    }

    private void displayHelp()
    {
        System.out.println("This is game help ... ");
    }

    private void exitGame()
    {
        System.out.println("Are you sure to exit game?");
        System.out.println("Enter 1 to exit, enter others to continue");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input.equals("1"))
        {
            System.exit(0);
        }
    }

}
