import java.util.ArrayList;

public class Critter
{
    public static final char DEFAULT_APPEARANCE = '*';
    public static final char EMPTY = ' ';
    private char appearance;  
    private NeighbourHelper neighbourHelper;


    public Critter ()
    {
	setAppearance(DEFAULT_APPEARANCE);
    }

    public Critter(char ch)
    {
	setAppearance(ch);
    }

    public char getAppearance()
    {
	return appearance;
    } 

    public void setAppearance(char newAppearance)
    {
        appearance = newAppearance;
    } 
    
    public String toString()
    {
	String s = "" + appearance;
        return(s);
    }

    boolean exists()
    {
        if (appearance == DEFAULT_APPEARANCE)
            return true;
        else
            return false;
    }

    boolean isTaminator()
    {
        return false;
    }

    void setNeighbourHelper(NeighbourHelper neighbourHelper)
    {
        this.neighbourHelper = neighbourHelper;
    }

    java.util.ArrayList<Location> getNeighbouringCritters(int critterRow, 
        int critterCol, Critter[][] critters)
    {
        java.util.ArrayList<Location> allNeighbours = neighbourHelper.getNeighbours(critterRow, critterCol);
        java.util.ArrayList<Location> neighbouringCritters = new java.util.ArrayList<Location>();
        for(Location location : allNeighbours)
        {
            int row = location.getRow();
            int col = location.getColumn();
            Critter critter = critters[row][col];
            if (critter.exists() && !critter.isTaminator())
                neighbouringCritters.add(location);
        }
        return neighbouringCritters;
    } 
    
    int getNumberOfNeighbouringCritters(int critterRow, 
        int critterCol, Critter[][] critters)
    {
        java.util.ArrayList<Location> neighbours = getNeighbouringCritters(critterRow, critterCol, critters);
        return neighbours.size();
    }
}

