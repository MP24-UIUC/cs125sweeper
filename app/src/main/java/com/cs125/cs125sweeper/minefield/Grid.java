package com.cs125.cs125sweeper.minefield;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.cs125.cs125sweeper.Game;

public class Grid extends GridView {
    public Grid(Context context , AttributeSet attrs) {
        super(context, attrs);
        Game.getInstance().generateGrid(context);
        setNumColumns(Game.ROW);
        setAdapter(new GridAdapter());
    }

    // This calls for a bunch of data to be collected. Needed to generate a VALID minefield.
    private class GridAdapter extends BaseAdapter {
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return Game.getInstance().ROW * Game.getInstance().COLUMN;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return Game.getInstance().getCellAt(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
