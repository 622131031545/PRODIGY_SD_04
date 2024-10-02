package task_04;
/*Implement a Sudoku Solver

Create a program that solves Sudoku puzzles automatically.
 The program should take an input grid representing an unsolved Sudoku puzzle and use
  an algorithm to fill in the missing numbers.

It should use backtracking or other suitable techniques to explore possible solutions
 and find the correct arrangement of numbers for the puzzle. Once solved, the program should display the completed Sudoku grid.
 Filename: C:\Users\Shaibaj\eclipse-workspace\PRODIGY_SD_04\src\task_04\SudokuSolver.java
 Path: C:\Users\Shaibaj\eclipse-workspace\PRODIGY_SD_04\src\task_04\SudokuSolver.java
 Created Date: friaday, Octomber 05 2024, 10:30:43 am
 Author: Shaibaj
 Copyright (c) 2024 */
import java.util.Scanner;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner sc = new Scanner(System.in);

       
        System.out.println("Enter your Sudoku puzzle (use 0 for empty cells):");

       
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.println("Enter row " + (row + 1) + " (9 digits separated by space): ");
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = sc.nextInt();
            }
        }

        System.out.println("Original Sudoku Puzzle:");
        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("\nSolved Sudoku Puzzle:");
            printBoard(board);
        } else {
            System.out.println("Unsolvable puzzle.");
        }
        
        sc.close();
    }

    
    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
               
                if (board[row][col] == 0) {
                   
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            
                            if (solveBoard(board)) {
                                return true; 
                            } else {
                                board[row][col] = 0;  
                            }
                        }
                    }
                    return false;  
                }
            }
        }
        return true;  
    }

    
    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

       
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

       
        int subGridRow = (row / 3) * 3;
        int subGridCol = (col / 3) * 3;
        for (int i = subGridRow; i < subGridRow + 3; i++) {
            for (int j = subGridCol; j < subGridCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

      
        return true;
    }

   
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------"); 
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|"); 
                }
                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
            }
            System.out.println();
        }
    }
}

/*Original Sudoku Puzzle:
5 3 . | . 7 . | . . .
6 . . | 1 9 5 | . . .
. 9 8 | . . . | . 6 .
---------------------
8 . . | . 6 . | . . 3
4 . . | 8 . 3 | . . 1
7 . . | . 2 . | . . 6
---------------------
. 6 . | . . . | 2 8 .
. . . | 4 1 9 | . . 5
. . . | . 8 . | . 7 9

Solved Sudoku Puzzle:
5 3 4 | 6 7 8 | 9 1 2
6 7 2 | 1 9 5 | 3 4 8
1 9 8 | 3 4 2 | 5 6 7
---------------------
8 5 9 | 7 6 1 | 4 2 3
4 2 6 | 8 5 3 | 7 9 1
7 1 3 | 9 2 4 | 8 5 6
---------------------
9 6 1 | 5 3 7 | 2 8 4
2 8 7 | 4 1 9 | 6 3 5
3 4 5 | 2 8 6 | 1 7 9
*/