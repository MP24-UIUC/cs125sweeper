package com.cs125.cs125sweeper;

import android.content.Context;
import android.widget.Toast;

import com.cs125.cs125sweeper.minefield.Cell;
import com.cs125.cs125sweeper.util.Generator;

public class Game {
    private static Game instance;
    private Context context;
    private Cell[][] GeoffSweeperGrid = new Cell[ROW][COLUMN];
    public static final int NUMBER_OF_MINES = 10;
    public static final int ROW = 10;
    public static final int COLUMN = 10;

    // Starts a game if one hasn't been started.
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // When a tile is tapped, it'll refer to a grid if it was a mine or not.
    public void tap(int x, int y) {
        // If the tile hasn't been tapped before, do the checks.
        if (x >= 0 && y >= 0 && x < ROW && y < COLUMN && !getCellAt(x,y).wasTapped()) {
            getCellAt(x, y).setAsTapped();
            // If the tile ain't a mine, continue on.
            if (getCellAt(x, y).getValue() == 0) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != j) {
                            tap(x + i , y + j);
                        }
                    }
                }
            }
            // If the the tile is a mine, game over.
            if( getCellAt(x,y).isAMine() ){
                gameOver();
            }
        }
        // If no more non-mine tiles are left, the player has won!
        isGameWon();
    }

    // Gets every cell from the grid.
    public Cell getCellAt(int position) {
        int x = position % ROW;
        int y = position / ROW;
        return GeoffSweeperGrid[x][y];
    }

    // Generates the grid based on hard coded values in the Generator class.
    public void generateGrid(Context context) {
        this.context = context;
        int[][] GeneratedGrid = Generator.generateGrid(NUMBER_OF_MINES, ROW, COLUMN);
        setGrid(context,GeneratedGrid);
    }

    // Also self explanatory. If the player taps a tile containing a mine, he/she has lost the game.
    private void gameOver() {
        Toast.makeText(context,"Game Over!", Toast.LENGTH_SHORT).show();
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COLUMN; y++) {
                getCellAt(x,y).setAsRevealed();
            }
        }
    }

    // Gets every cell from the grid.
    public Cell getCellAt(int x, int y) {
        return GeoffSweeperGrid[x][y];
    }


    // Self explanatory. In MineSweeper, a game is won when all non-mine tiles are revealed.
    private boolean isGameWon(){
        int MinesNotFound = NUMBER_OF_MINES;
        int tilesNotRevealed = ROW * COLUMN;
        for (int x = 0 ; x < ROW ; x++) {
            for (int y = 0 ; y < COLUMN ; y++) {
                // Crosses of the number of tiles that are yet to be revealed.
                if (getCellAt(x, y).isItRevealed() || getCellAt(x, y).isItFlagged()) {
                    tilesNotRevealed--;
                }
                // Crosses off the number of mines that were flagged
                if (getCellAt(x, y).isItFlagged() && getCellAt(x, y).isAMine()) {
                    MinesNotFound--;
                }
            }
        }
        // If no mines were tapped, and all tiles haven been reveal, send game won message.
        if (MinesNotFound == 0 && tilesNotRevealed == 0) {
            Toast.makeText(context,"Congratulation! You Won!", Toast.LENGTH_SHORT).show();
        }
        // Otherwise, don't do it yet :(.
        return false;
    }

    // Sets the grid accordingly based on the row and column size.
    private void setGrid(final Context context, final int[][] grid) {
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COLUMN; y++) {
                if (GeoffSweeperGrid[x][y] == null) {
                    GeoffSweeperGrid[x][y] = new Cell(context, x, y);
                }
                GeoffSweeperGrid[x][y].setValue(grid[x][y]);
                GeoffSweeperGrid[x][y].invalidate();
            }
        }
    }

    // Allows the player to flag tiles that they suspect are mines.
    public void placeFlag(int x, int y) {
        boolean isFlagged = getCellAt(x, y).isItFlagged();
        getCellAt(x, y).setAsFlagged(!isFlagged);
        getCellAt(x, y).invalidate();
    }
}
