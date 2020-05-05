package com.cs125.cs125sweeper.minefield;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.cs125.cs125sweeper.Game;
import com.cs125.cs125sweeper.R;

// This draws the minefield.
public class Cell extends ParentCell implements View.OnClickListener, View.OnLongClickListener {
    public Cell( Context context, int x, int y){
        super(context);
        setPosition(x, y);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    // This draws each tile.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        buttonDraw(canvas);
        if (isItFlagged()) {
            flagDraw(canvas);
        } else if (isItRevealed() && isAMine() && !wasTapped()) {
            mineDraw(canvas);
        } else {
            if (wasTapped()) {
                if (getValue() == -1) {
                    explosionDraw(canvas);
                } else {
                    numberDraw(canvas);
                }
            } else {
                buttonDraw(canvas);
            }
        }
    }

    // This sends a signal to draw the result of said tile being tapped.
    @Override
    public void onClick(View v) {
        Game.getInstance().tap(getXPos(), getYPos());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    // This draws the flags on long taps.
    @Override
    public boolean onLongClick(View v) {
        Game.getInstance().placeFlag(getXPos() , getYPos());
        return true;
    }

    // Draws the buttons that haven't been tapped :(.
    private void buttonDraw(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    // This spaghetti code draws the corresponding number of mines there on around the tile.
    private void numberDraw(Canvas canvas) {
        Drawable drawable = null;
        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    // Draws out flag :D.
    private void flagDraw(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    // Draws the mines needed for game over :(.
    private void mineDraw(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    // Draws the boom boom!
    private void explosionDraw(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
