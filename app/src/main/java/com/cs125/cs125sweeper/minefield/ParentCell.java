package com.cs125.cs125sweeper.minefield;

import android.content.Context;
import android.view.View;

import com.cs125.cs125sweeper.Game;

// This contains a bunch of game logic for cell class to refer to. Should be all understandable.
public abstract class ParentCell extends View {
    private int value;
    private int position;
    private int x;
    private int y;
    private boolean isAMine;
    private boolean isItRevealed;
    private boolean wasTapped;
    private boolean isItFlagged;

    public ParentCell(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setAsRevealed() {
        isItRevealed = true;
        invalidate();
    }

    public boolean isAMine() {
        return isAMine;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.position = y * Game.ROW + x;
        invalidate();
    }

    public int getXPos() {
        return x;
    }

    public boolean wasTapped() {
        return wasTapped;
    }

    public boolean isItFlagged() {
        return isItFlagged;
    }

    public int getYPos() {
        return y;
    }

    public void setValue(int value) {
        isAMine = false;
        isItRevealed = false;
        wasTapped = false;
        isItFlagged = false;
        if (value == -1) {
            isAMine = true;
        }
        this.value = value;
    }

    public void setAsFlagged(boolean flagged) {
        isItFlagged = flagged;
    }

    public boolean isItRevealed() {
        return isItRevealed;
    }

    public void setAsTapped() {
        this.wasTapped = true;
        this.isItRevealed = true;
        invalidate();
    }

}
