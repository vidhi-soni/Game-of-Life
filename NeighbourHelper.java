import java.util.ArrayList;

class NeighbourHelper
{
    int totalRows;
    int totalCols;

    NeighbourHelper(int rows, int cols)
    {
        totalRows = rows;
        totalCols = cols;
    }

    private boolean isLocationLegal(int row, int col)
    {
        if (col >= 0 && col < totalCols && row >= 0 && row < totalCols)
            return true;
        else
            return false;
    }    

    java.util.ArrayList<Location> getNeighbours(int currentRow, int currentCol)
    {
        java.util.ArrayList<Location> neighbours = new java.util.ArrayList<Location>();

        for (int row = -1; row <= 1; row++)
        {
            for (int col = -1; col <= 1; col++)
            {
                int adjacentRow = currentRow + row;
                int adjacentCol = currentCol + col;
                if (isLocationLegal(adjacentRow, adjacentCol) && 
                    (adjacentRow != currentRow || adjacentCol != currentCol))
                {
                    neighbours.add(new Location(adjacentRow, adjacentCol));
                }
            }
        }
        return neighbours;
    }    
}