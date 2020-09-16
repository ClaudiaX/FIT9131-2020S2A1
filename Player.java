
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // global this
    private String name;
    private int position;
    private int penalty;
    private int bonus;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        name = "";
        this.position = 0;
        this.penalty = 0;
        this.bonus = 0;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getPosition()
    {
        return this.position;
    }
    
    public void setPosition(int position)
    {
        this.position = position;
    }
    
    public int getPenalty()
    {
        return this.penalty;
    }
    
    public void setPenalty(int penalty)
    {
        this.penalty = penalty;
    }
    
    public int getBonus()
    {
        return this.bonus;
    }
    
    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }
    
    public void increasePenalty()
    {
        this.penalty += 1;
    }
    
    public void increaseBonus()
    {
        this.bonus += 1;
    }
}
