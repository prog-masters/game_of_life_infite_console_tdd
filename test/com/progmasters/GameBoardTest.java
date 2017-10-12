package com.progmasters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by szfilep.
 */
public class GameBoardTest {

    GameBoard gameBoard;

    @Before
    public void init() {
        gameBoard = new GameBoard();
    }

    @Test
    public void testAddCell() {
        gameBoard.addCell(0, 0);

        Assert.assertTrue(gameBoard.isCellAlive(0, 0));
    }

    /**
     * Rule 1: Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
     */
    @Test
    public void testRule1_noNeighbours() {
        gameBoard.addCell(0, 0);

        gameBoard.nextGeneration();

        Assert.assertFalse(gameBoard.isCellAlive(0, 0));

    }

    /**
     * Rule 1: Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
     */
    @Test
    public void testRule1_oneNeighbour() {
        gameBoard.addCell(0, 0);
        gameBoard.addCell(1, 0);

        gameBoard.nextGeneration();

        Assert.assertFalse(gameBoard.isCellAlive(0, 0));

    }

    /**
     * Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
     */
    @Test
    public void testRule2_twoNeighbours() {
        gameBoard.addCell(0, 0);
        gameBoard.addCell(1, 0);
        gameBoard.addCell(1, 1);

        gameBoard.nextGeneration();

        Assert.assertTrue(gameBoard.isCellAlive(0, 0));
    }

    /**
     * Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
     */
    @Test
    public void testRule2_threeNeighbours() {
        gameBoard.addCell(0, 0);
        gameBoard.addCell(1, 0);
        gameBoard.addCell(1, 1);
        gameBoard.addCell(0, 1);

        gameBoard.nextGeneration();

        Assert.assertTrue(gameBoard.isCellAlive(0, 0));
    }

    /**
     * Rule 3: Any live cell with more than three live neighbours dies, as if by overpopulation.
     */
    @Test
    public void testRule3_fourNeigbours() {
        gameBoard.addCell(0, 0);
        gameBoard.addCell(1, 0);
        gameBoard.addCell(1, 1);
        gameBoard.addCell(0, 1);
        gameBoard.addCell(-1, 1);

        gameBoard.nextGeneration();

        Assert.assertFalse(gameBoard.isCellAlive(0, 0));

    }

    @Test
    public void testRule4_resurrection() {
        gameBoard.addCell(1, 1);
        gameBoard.addCell(0, 1);
        gameBoard.addCell(1, 0);

        gameBoard.nextGeneration();

        Assert.assertTrue(gameBoard.isCellAlive(0, 0));
    }

}
