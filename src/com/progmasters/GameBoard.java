package com.progmasters;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by szfilep.
 */
public class GameBoard {

    private Set<Cell> cells = new HashSet<>();

    //the 8 neighbors' relative coordinates
    private int[][] neighbours = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    public void addCell(int x, int y) {
        this.cells.add(new Cell(x, y));
    }

    public boolean isCellAlive(int x, int y) {
        return cells.contains(new Cell(x, y));
    }

    public void nextGeneration() {
        Set<Cell> nextGenCells = new HashSet<>();

        Set<Cell> possibleCells = generateNextGenCells(nextGenCells);

        generateNewCells(nextGenCells, possibleCells);

        cells = nextGenCells;
    }

    private void generateNewCells(Set<Cell> nextGenCells, Set<Cell> possibleCells) {
        for (Cell c : possibleCells) {
            int n = countNeighbours(c);
            if (n == 3) {
                nextGenCells.add(c);
            }
        }
    }

    private Set<Cell> generateNextGenCells(Set<Cell> nextGenCells) {
        Set<Cell> possibleCells = new HashSet<>();
        for (Cell c : cells) {
            int n = countNeighbours(c);
            if (n == 2 || n == 3) {
                nextGenCells.add(c);
            }

            possibleCells.addAll(aroundCell(c));
        }
        return possibleCells;
    }

    private Set<Cell> aroundCell(Cell cell) {
        Set<Cell> neigbourCells = new HashSet<>();
        for (int i = 0; i < neighbours.length; i++) {
            Cell nc = new Cell(cell.getX() + neighbours[i][0], cell.getY() + neighbours[i][1]);
            neigbourCells.add(nc);
        }
        return neigbourCells;
    }

    private int countNeighbours(Cell cell) {
        int count = 0;

        for (int i = 0; i < neighbours.length; i++) {
            Cell nc = new Cell(cell.getX() + neighbours[i][0], cell.getY() + neighbours[i][1]);
            if (cells.contains(nc)) {
                count++;
            }
        }

        return count;
    }

    public boolean hasMoreCell() {
        return !cells.isEmpty();
    }

    public void print() {
        int minX, maxX, minY, maxY;
        minX = minY = Integer.MAX_VALUE;
        maxX = maxY = Integer.MIN_VALUE;
        for (Cell c : cells) {
            if (c.getX() < minX) {
                minX = c.getX();
            }
            if (c.getX() > maxX) {
                maxX = c.getX();
            }
            if (c.getY() < minY) {
                minY = c.getY();
            }
            if (c.getY() > maxY) {
                maxY = c.getY();
            }
        }

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (cells.contains(new Cell(x, y))) {
                    System.out.print('O');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

        System.out.println("------------------------------------------------");
    }
}
