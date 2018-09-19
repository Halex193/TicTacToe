import java.util.Scanner;

public class Main
{

    static char[][] signs = new char[][]{
            {'O', ' ', ' '},
            {' ', 'X', ' '},
            {' ', ' ', ' '}
    };
    static final int dim = 3;

    public static void main(String[] args)
    {
        printBoard();
        int x = -1, y = -1;
        Scanner s = new Scanner(System.in);
        while (!isValid(x, y))
        {
            System.out.print("X: ");
            x = s.nextInt();
            System.out.print("Y: ");
            y = s.nextInt();
        }
        signs[y][x] = 'X';
        printBoard();
    }

    private static boolean isValid(int x, int y)
    {
        return between(x, 0, dim) && between(y, 0, dim) && signs[y][x] == ' ';
    }

    private static boolean between(int n, int min, int max)
    {
        return n >= min && n < max;
    }

    public static void printBoard()
    {
        for (int i = 0; i < dim; i++)
        {
            for (int j = 0; j < dim; j++)
            {
                System.out.print(" " + signs[i][j] + " ");
                if (j != dim - 1)
                    System.out.print("|");
            }

            System.out.println();
            if (i != dim - 1)
                System.out.println("-----------");

        }
    }
}
