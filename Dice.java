import java.util.Random;
/**
 * Write a description of class Dice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dice
{
    private int number;

    /**
     * Constructor for objects of class Dice
     */
    public Dice()
    {
        this.number = 0;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }
    
    public int rollDice()
    {
        // math 0-1,0 ok, 1 no
        number = (int)(Math.random() * 6) + 1;
        
        // random
        // Random random = new Random();
        // number = random.nextInt(6) + 1;
       
        return number;
    }
}
