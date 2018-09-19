import java.util.Scanner;

public class Main
{
    final int winNum;
    final int dim;
    char[][] signs;
    boolean sign = false;
    int x;
    int y;
    int moves = 0;

    public static void main(String[] args)
    {
        System.out.println("Choose board size: ");
        Scanner s = new Scanner(System.in);
        int size;
        while ((size = s.nextInt()) > 0)
        {
            int winNum = 0;
            while (!(winNum > 2 && winNum <= size))
            {
                System.out.println("Choose the number of signs to win: ");
                winNum = s.nextInt();
            }
            new Main(size, winNum).play();
            System.out.println("\nChoose new board size: ");
        }
        System.out.println("Have a good day!");
    }

    Main(int dim, int winNum)
    {
        this.dim = dim;
        signs = new char[dim][];
        for (int i = 0; i < dim; i++)
        {
            signs[i] = new char[dim];
            for (int j = 0; j < dim; j++)
            {
                signs[i][j] = ' ';
            }
        }
        this.winNum = winNum;
    }

    void play()
    {
        printBoard();
        while (!won())
        {
            newMove();
            printBoard();
        }
        if (moves != dim*dim)
            System.out.println(convertSign() + " has won!");
        else
            System.out.println("The game has ended in a tie!");
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
            {
                for (int j = 0; j < dim; j++)
                {
                    System.out.print("----");
                }
                System.out.println();
            }
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
        moves++;
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
            if (count(x, y, dir, chr) + count(x, y, invert(dir), chr) + 1 == winNum) return true;
        }
        return moves == dim*dim;
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
