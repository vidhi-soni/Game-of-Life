import java.util.Scanner;

/* No additional methods and nor attributes to be added. */ 

public class GameOfLife
{
    public static void main (String [] args)
    {
        Scanner in = new Scanner(System.in);
        boolean correctOptionSpecified = false;
        Biosphere biosphere = null;
        while (!correctOptionSpecified)
        {
            System.out.print("Enter the type of Biosphere to create (r/R for regular, p/P for PropsperousBioSphere):");
            String input = in.nextLine();
            if (input.equals("r") || input.equals("R"))
            {
                System.out.println("Creating regular biosphere");
                biosphere = new Biosphere(FileInitialization.read());
                correctOptionSpecified = true;
            }
            else if (input.equals("r") || input.equals("R"))
            {
                System.out.println("Creating propsperous biosphere...");
                biosphere = new ProsperousBiosphere(FileInitialization.read());
                correctOptionSpecified = true;
            }
            else
            {
                System.out.println("Wrong input. Please try again");
            }
        }
        
        biosphere.runTurn();
    }
}



