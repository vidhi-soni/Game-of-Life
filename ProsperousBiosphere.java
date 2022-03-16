public class ProsperousBiosphere extends Biosphere // Students can't change this inheritance relation.
{
    //Students can make any changes except for the relationship.
    public ProsperousBiosphere(Critter [][] aWorld)
    {
        super(aWorld);
    }

    boolean isReadytoBeBorn(int numberofNeighbours)
    {
        return numberofNeighbours == 4;
    }

    boolean isReadyToDie(int numberofNeighbours)
    {
        return numberofNeighbours < 1 || numberofNeighbours > 4;
    }    
}
