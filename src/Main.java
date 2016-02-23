import java.util.*;

/**
 * Created by Ryan Benasutti on 2/15/2016.
 */

public class Main
{
    static boolean visited[][];
    static int moveCount, sizeX, sizeY;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //Number of test cases is first input
        int numBoards = in.nextInt();

        //Loop for each new board
        for (int q = 0; q < numBoards; q++)
        {
            //Get p and q and setup instance variables
            sizeX = in.nextInt();
            sizeY = in.nextInt();
            moveCount = sizeX * sizeY - 1;
            visited = new boolean[sizeX][sizeY];

            //Initialize array
            for (int i = 0; i < sizeX; i++)
            {
                for (int j = 0; j < sizeY; j++)
                {
                    visited[i][j] = false;
                }
            }

            //Print current scenario
            System.out.println("Scenario #" + (q + 1) + ":");

            //Get a queue of movable for stating tile and add starting tile to it
            Queue<Movable> movables = getMovables(0, 0);
            movables.add(new Movable(2, 0, 0));

            //Run knight's tour
            String tour = doTour((movables));

            //Check if tour is a full tour
            if (tour.length() == (sizeX * sizeY) * 2)
                System.out.print(tour);
            else
                System.out.print("impossible");
            System.out.println();
        }
    }

    /**
     * Runs the Knight's Tour algorithm (recursive implementation, lexicographical order). This will not catch
     * impossible tours.
     *
     * @param movables A starting list of movables (based on starting tile at (0,0))
     * @return         A string of all moves needed to complete Knight's Tour
     */
    public static String doTour(Queue<Movable> movables)
    {
        //Current movable
        Movable movable;

        try
        {
            //Get lowest degree movable that hasn't been visited yet
            do
            {
                movable = movables.remove();
            } while (visited[movable.x][movable.y]);

            //If no exception was thrown, we have an unvisited movable
            //Mark it as visited
            visited[movable.x][movable.y] = true;
        }
        //If NoSuchElementException is thrown, there are no more unvisited movables
        catch (NoSuchElementException e)
        {
            //Return an empty string and backtrack
            return "";
        }

        return movable.toString() + doTour(getMovables(movable.x, movable.y));
    }

    /**
     * Returns the degree of the input cell.
     *
     * @param x X-coordinates of the input cell
     * @param y Y-coordinates of the input cell
     * @return  Degree of the input cell
     */
    public static int getDegree(int x, int y)
    {
        int degree = 0;

        if (x - 2 >= 0 && y + 1 < sizeY)
            degree++;

        if (x - 2 >= 0 && y - 1 >= 0)
            degree++;

        if (x - 1 >= 0 && y + 2 < sizeY)
            degree++;

        if (x - 1 >= 0 && y - 2 >= 0)
            degree++;

        if (x + 2 < sizeX && y + 1 < sizeY)
            degree++;

        if (x + 2 < sizeX && y - 1 >= 0)
            degree++;

        if (x + 1 < sizeX && y + 2 < sizeY)
            degree++;

        if (x + 1 < sizeX && y - 2 >= 0)
            degree++;

        return degree;
    }

    /**
     * Returns a PriorityQueue of all movable cells for an input cell.
     *
     * @param x X-coordinate of the input cell
     * @param y Y-coordinate of the input cell
     * @return  A PriorityQueue of all movable cells from the input cell
     */
    public static Queue<Movable> getMovables(int x, int y)
    {
        Queue<Movable> movables = new PriorityQueue<Movable>(1, new MovableComparator());

        if (x - 2 >= 0 && y + 1 < sizeY)
        {
            if (!visited[x-2][y+1])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x - 2, y + 1);
                movable.x = x - 2;
                movable.y = y + 1;
                movables.add(movable);
            }
        }

        if (x - 2 >= 0 && y - 1 >= 0)
        {
            if (!visited[x-2][y-1])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x - 2, y - 1);
                movable.x = x - 2;
                movable.y = y - 1;
                movables.add(movable);
            }
        }

        if (x - 1 >= 0 && y + 2 < sizeY)
        {
            if (!visited[x-1][y+2])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x - 1, y + 2);
                movable.x = x - 1;
                movable.y = y + 2;
                movables.add(movable);
            }
        }

        if (x - 1 >= 0 && y - 2 >= 0)
        {
            if (!visited[x-1][y-2])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x - 1, y - 2);
                movable.x = x - 1;
                movable.y = y - 2;
                movables.add(movable);
            }
        }

        if (x + 2 < sizeX && y + 1 < sizeY)
        {
            if (!visited[x+2][y+1])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x + 2, y + 1);
                movable.x = x + 2;
                movable.y = y + 1;
                movables.add(movable);
            }
        }

        if (x + 2 < sizeX && y - 1 >= 0)
        {
            if (!visited[x+2][y-1])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x + 2, y - 1);
                movable.x = x + 2;
                movable.y = y - 1;
                movables.add(movable);
            }
        }

        if (x + 1 < sizeX && y + 2 < sizeY)
        {
            if (!visited[x+1][y+2])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x + 1, y + 2);
                movable.x = x + 1;
                movable.y = y + 2;
                movables.add(movable);
            }
        }

        if (x + 1 < sizeX && y - 2 >= 0)
        {
            if (!visited[x+1][y-2])
            {
                Movable movable = new Movable();
                movable.degree = getDegree(x + 1, y - 2);
                movable.x = x + 1;
                movable.y = y - 2;
                movables.add(movable);
            }
        }

        return movables;
    }

    /**
     * Class to contain data for a cell (degree, x-coordinate, y-coordinate).
     */
    private static class Movable
    {
        public int degree, x, y;

        public Movable()
        {
            this.degree = 0;
            this.x = 0;
            this.y = 0;
        }

        public Movable(int degree, int x, int y)
        {
            this.degree = degree;
            this.x = x;
            this.y = y;
        }

        /**
         * Returns a string representation of this movable by converting x- and y-coordinates to ASCII text. Column
         * major.
         *
         * @return A string representation of this movable
         */
        @Override
        public String toString()
        {
            return new String(new char[]{(char)(y + 65)}).concat("" + (x + 1));
        }
    }

    /**
     * Custom Comparator for the PriorityQueue returned by getMovables(). Sorts into lexicographic order.
     */
    private static class MovableComparator implements Comparator<Movable>
    {
        public int compare(Movable o1, Movable o2)
        {
            int ycomp = (o1.y < o2.y) ? -1 : ((o1.y == o2.y) ? 0 : 1);
            if (ycomp == 0)
                return (o1.x < o2.x) ? -1 : ((o1.x == o2.x) ? 0 : 1);
            else
                return ycomp;
        }
    }
}
