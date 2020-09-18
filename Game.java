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

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        player1 = new Player();
        player2 = new Player();
        dice = new Dice();
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
        boolean gameStart = false;
        while(continous)
        {
            displayMenu();
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                checkStart(gameStart);
                gameStart = true;
                break;

                case 2:
                gameStart = playOneRound(gameStart);
                break;

                case 3:
                displayPosition(gameStart);
                break;

                case 4:
                displayHelp();
                break;

                case 5:
                continous = false;
                break;

                default:
                System.out.println("Error! Please enter a number between 1-5!");
                break;
            }
        }
    }

    private void checkStart(boolean gameStart)
    {
        Scanner sc = new Scanner(System.in);
        if(gameStart)
        {
            System.out.println("Do you want to start a new game?");
            System.out.println("Enter 1 to start a new game");
            String input = sc.nextLine().trim();
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
        String name1 = sc.nextLine().trim();
        while(!checkName(name1))
        {
            System.out.println("Error! Please enter a valid name!");
            System.out.print("Enter First Player's Name: ");
            name1 = sc.nextLine();
        }
        player1 = new Player();
        player1.setName(name1);
        //player 2
        String name2 = "";
        do{
            System.out.print("Enter Second Player's Name: ");
            name2 = sc.nextLine().trim();
        }
        while(!checkName(name2));
        player2 = new Player();
        player2.setName(name2);
    }

    private boolean checkName(String name)
    {
        if(name.length() == 0)
            return false;
        for(int i = 0;i<name.length();i++)
            if(!Character.isLetter(name.charAt(i)))
                return false;
        return true;
    }

    private boolean playOneRound(boolean gameStart)
    {
        if(!gameStart && player1.getName().length() == 0)// position = 0 or no name
            System.out.println("Error: players has not been set up!");
        else if(!gameStart && player1.getPosition() != 0)
        {
            System.out.println("Game finished. You must start a new game");
            if(player1.getPosition() >= 50 && player2.getPosition() < 50)
                System.out.println("The last game was won by Player " + player1.getName());
            else if (player2.getPosition() >= 50 && player1.getPosition() < 50)
                System.out.println("The last game was won by Player " + player2.getName());
            else if (player1.getPosition() >= 50 && player2.getPosition() >= 50)
                System.out.println("*** The last game was even ***");
        }
        else{
            player1 = rollDice(player1);
            player2 = rollDice(player2);
            if(player1.getPosition() >= 50 && player1.getPosition() > player2.getPosition())
            {
                gameStart = false;
                System.out.println("*** Congratulations, "+ player1.getName() +" have WON this game!! ***");
            }
            else if(player2.getPosition() >= 50 && player2.getPosition() > player1.getPosition())
            {
                gameStart = false;
                System.out.println("*** Congratulations, "+ player2.getName() +" have WON this game!! ***");
            }
            else if( player1.getPosition() == player2.getPosition() && player2.getPosition() >= 50)
            {
                gameStart = false;
                System.out.println("*** This game is draw ***");
            }
        }
        return gameStart;
    }

    private Player rollDice(Player player)
    {
        int previous = player.getPosition();
        int rollNumber = dice.rollDice();
        int nowPosition = previous + rollNumber;
        // check penalty
        if(nowPosition % 11 == 0 && nowPosition < 50)
        {
            int newPosition = nowPosition - 5;
            player.setPosition(newPosition);
            player.increasePenalty();
            System.out.println(player.getName() + " rolled a " + rollNumber + ", and moves from position " + previous +" to " + newPosition + " [Penalty applied]");
        }
        // check bonus - twice
        else if(nowPosition % 5 == 0 && nowPosition % 10 != 0 && nowPosition <= 35)
        {
            System.out.println(player.getName() + " rolled a " + rollNumber + ", and moves from position " + previous +" to " + nowPosition + " [Bonus Immunity Roll]");
            // second roll
            int newRoll = dice.rollDice();
            int newPosition = nowPosition + newRoll;
            player.setPosition(newPosition);
            // get,set bonus + 1
            player.increaseBonus();
            System.out.println(player.getName() + " rolled a " + newRoll + ", and moves from position " + nowPosition +" to " + newPosition);
        }
        else
        {
            player.setPosition(nowPosition);
            System.out.println(player.getName() + " rolled a " + rollNumber + ", and moves from position " + previous +" to " + nowPosition);
        }
        return player;
    }

    private void displayPosition(boolean gameStart)
    {
        if(!gameStart && player1.getName().length() == 0)// position = 0 or no name
            System.out.println("Error: players has not been set up!");
        else if(!gameStart && player1.getPosition() != 0)
        {
            System.out.println("Game finished. You must start a new game");
            if(player1.getPosition() >= 50 && player2.getPosition() < 50)
                System.out.println("The last game was won by Player " + player1.getName());
            else if (player2.getPosition() >= 50 && player1.getPosition() < 50)
                System.out.println("The last game was won by Player " + player2.getName());
            else if (player1.getPosition() >= 50 && player2.getPosition() >= 50)
                System.out.println("*** The last game was draw ***");
        }
        else
        {
            System.out.println(displayPosition(player1));
            System.out.println(displayPosition(player2));
        }
    }

    private String displayPosition(Player player)
    {
        return String.format("Player %s is on position %s [Penalties: %s, Bonuses: %s]", player.getName(), player.getPosition(), player.getPenalty(), player.getBonus());
    }

    private void displayHelp()
    {
        System.out.println("This is game help ... ");
    }
}
