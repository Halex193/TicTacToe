public class Main
{

    static char[][] signs = new char[][]{
            {'O', ' ', ' '},
            {' ', 'X', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args)
    {
        printBoard();
    }

    public static void printBoard()
    {
        for (int i = 0; i < signs.length; i++)
        {
            for (int j = 0; j < signs[0].length; j++)
            {
                System.out.print(" " + signs[i][j] + " ");
                if (j != signs[0].length - 1)
                    System.out.print("|");
            }

            System.out.println();
            if (i != signs.length - 1)
                System.out.println("-----------");

        }
    }
}
