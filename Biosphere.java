import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Biosphere
{
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "   - - - - - - - - - - ";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    private Critter [][] current; 
    private Critter [][] lifeDeathUpdatedCritters;
    private Critter [][] taminatorUpdatedCritters;
    Scanner in = new Scanner(System.in);
    NeighbourHelper neighbourHelper = new NeighbourHelper(ROWS, COLUMNS);

    public Biosphere(Critter [][] aWorld)
    {

        current = aWorld;
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                current[row][col].setNeighbourHelper(neighbourHelper);
            }
        }

        lifeDeathUpdatedCritters = new Critter[ROWS][COLUMNS];
        taminatorUpdatedCritters = new Critter[ROWS][COLUMNS];
    }

    void displayBorderLines()
    {
        String separator = "       ";
        System.out.print(HORIZONTAL_LINE);
        System.out.print(separator);
        System.out.print(HORIZONTAL_LINE);
        System.out.print(separator);
        System.out.print(HORIZONTAL_LINE);
        System.out.println("");
    }

    void displayRow(int row, Critter[][] critters)
    {
        System.out.printf("%d ", row);
        for (int col = 0; col < COLUMNS; col++)
        {
            System.out.print("|");
            System.out.print(critters[row][col].getAppearance());
        }
        System.out.print("|  ");
    }


    public void display()
    { 
        String separator = "     ";
        System.out.print("   PREVIOUS GENERATION  ");
        System.out.print(separator);
        System.out.print("       BIRTHS & DEATHS  ");
        System.out.print(separator);
        System.out.println("      TAMINATOR  ");
        
        displayBorderLines();
        for (int row = 0; row < ROWS; row++)
        {
            displayRow(row, current);
            System.out.print(separator);

            displayRow(row, lifeDeathUpdatedCritters);
            System.out.print(separator);

            displayRow(row, taminatorUpdatedCritters);
            System.out.println("");
            displayBorderLines();
        }
    }


    public Critter [][] getCurrent() 
    {
        return(current);
    }


    public void runTurn()
    {
        boolean keepRunning = true;
        while (keepRunning)
        {
            playLifeAndDeath();
            playTaminator();
            display();
            System.out.print("Your next input? (q/Q to quit, any other key to continue): ");
            String input = in.nextLine();
            System.out.println("");
            if (input.equals("q") || input.equals("Q"))
            {
                keepRunning = false;
            }
            else
            {
                for (int row = 0; row < ROWS; row++)
                {
                    for (int col = 0; col < COLUMNS; col++)
                    {
                        current[row][col] = taminatorUpdatedCritters[row][col];
                    }
                }        
            }
        }
    }

    boolean isReadytoBeBorn(int numberofNeighbours)
    {
        return numberofNeighbours == 3;
    }

    boolean isReadyToDie(int numberofNeighbours)
    {
        return numberofNeighbours < 2 || numberofNeighbours > 3;
    }

    void playLifeAndDeath()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                // If there is no change in this (row, column) (no death or birth)
                // then copy the critter from current to lifeDeathUpdatedCritters.
                // Otherwise create a new (dead or alive) critter.

                Critter critter = current[row][col];
                int numberofNeighbours = critter.getNumberOfNeighbouringCritters(row, col, current);
                if (critter.exists() && !critter.isTaminator() && isReadyToDie(numberofNeighbours))
                {
                    lifeDeathUpdatedCritters[row][col] = new Critter(Critter.EMPTY);
                }
                else if (!critter.exists() && isReadytoBeBorn(numberofNeighbours))
                {
                    lifeDeathUpdatedCritters[row][col] = new Critter(Critter.DEFAULT_APPEARANCE);
                }
                else
                {
                    lifeDeathUpdatedCritters[row][col] = critter;
                }
                lifeDeathUpdatedCritters[row][col].setNeighbourHelper(neighbourHelper);
            }
        }
    }

    void playTaminator()
    {
        Critter taminator = null;
        int taminatorRow = -1;
        int taminatorCol = -1;

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                taminatorUpdatedCritters[row][col] = lifeDeathUpdatedCritters[row][col];
                if (taminatorUpdatedCritters[row][col].isTaminator())
                {
                    taminatorRow = row;
                    taminatorCol = col;
                    taminator = taminatorUpdatedCritters[row][col];
                }
            }
        }

        if (taminator != null)
        {
            // Step2: Find and remove neighbouring critters
            java.util.ArrayList<Location> neighbours = taminator.getNeighbouringCritters(
                taminatorRow, taminatorCol, taminatorUpdatedCritters);
            for (Location neighbour : neighbours)
            {
                int row = neighbour.getRow();
                int col = neighbour.getColumn();
                taminatorUpdatedCritters[row][col].setAppearance(Critter.EMPTY);
            }

            // Step 3: Move to any random empty location
            java.util.Random random = new java.util.Random();
            boolean locationFound = false;
            while (!locationFound)
            {
                int row = random.nextInt(ROWS);
                int col = random.nextInt(COLUMNS);
                Critter critter = taminatorUpdatedCritters[row][col];
                if (!critter.exists())
                {
                    locationFound = true;
                    taminatorUpdatedCritters[row][col] = taminator;
                    taminatorUpdatedCritters[taminatorRow][taminatorCol] = critter;
                }
            }
        }
    }    
}
