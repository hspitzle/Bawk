package com.eecs493.bawk;

import java.awt.Point;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Utility
{
    public enum Color {RED, GREEN, YELLOW, BLUE};



    public static Point gridLocation()
    {
        return new Point(4, 200);
    }



    public static Point getLocation(int row, int col)
    {
        if(row > 11 || col > 11)
            return null;

        final int x[] = {0, 35, 70, 105, 140, 188, 236, 284, 332, 367, 402, 437};
        final int y[] = {437, 402, 367, 332, 284, 236, 188, 140, 105, 70, 35, 0};

        Point loc = new Point();
        loc.setLocation(x[col] + gridLocation().getX(), y[row] + gridLocation().getY());
        return loc;
    }

}
