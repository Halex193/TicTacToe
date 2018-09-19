import java.util.Scanner;

public class Main
{

    char[][] signs = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    final int dim = 3;
    boolean sign = false;
    int x;
    int y;

    public static void main(String[] args)
    {
        new Main().play();
    }

    void play()
    {
        printBoard();
        while (!won())
        {
            newMove();
            printBoard();
        }
        System.out.println(convertSign() + " has won!");
    }

    void printBoard()
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
        System.out.println();
    }

    void newMove()
    {
        x = -1;
        y = -1;
        sign = !sign;
        Scanner s = new Scanner(System.in);
        while (!(isValid(x, y) && signs[y][x] == ' '))
        {
            System.out.println(convertSign() + "\'s turn");
            System.out.print("X: ");
            x = s.nextInt();
            System.out.print("Y: ");
            y = s.nextInt();
            System.out.println();
        }
        signs[y][x] = convertSign();
    }

    boolean won()
    {
        int[][] directions = new int[][]{
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1}
        };
        char chr = convertSign();

        for (int[] dir : directions)
        {
            if (count(x, y, dir, chr) + count(x, y, invert(dir), chr) + 1 == 3) return true;
        }
        return false;
    }

    char convertSign()
    {
        return sign ? 'X' : 'O';
    }

    int count(int x, int y, int[] dir, char chr)
    {
        int count = -1;
        do
        {
            count++;
            x += dir[0];
            y += dir[1];

        } while (isValid(x, y) && signs[y][x] == chr);
        return count;
    }

    int[] invert(int[] dir)
    {
        return new int[]{-dir[0], -dir[1]};
    }

    boolean isValid(int x, int y)
    {
        return checkMax(x, dim) && checkMax(y, dim);
    }

    boolean checkMax(int n, int max)
    {
        return n >= 0 && n < max;
    }
}
