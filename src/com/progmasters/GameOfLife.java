package com.progmasters;

/**
 * Created by szfilep.
 */
public class GameOfLife {

    private int[][] initCells = {
            {1, 1, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1}
    };


    public void run() throws InterruptedException {
        GameBoard gameBoard = new GameBoard();
        initGameBoard(gameBoard);
        while (gameBoard.hasMoreCell()) {
            gameBoard.print();

            gameBoard.nextGeneration();

            Thread.sleep(400L);
        }


    }

    private void initGameBoard(GameBoard gameBoard) {
        for (int y = 0; y < initCells.length; y++) {
            for (int x = 0; x < initCells[0].length; x++) {
                if (initCells[y][x] == 1) {
                    gameBoard.addCell(x, y);
                }
            }
        }
    }
}
