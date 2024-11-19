import java.util.ArrayList;


public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int get_x()
    {
        return x;
    }
    public int get_y()
    {
        return y;
    }

    public void set_Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position p)
    {
        return (this.x == p.x && this.y == p.y);
    }

    public ArrayList<Position> surrounding1()
    {
        ArrayList<Position> ans = new ArrayList<Position>();
        if(this.get_x() < 7)
        {
            ans.add(new Position(this.get_x() +1, this.get_y()));
            ans.add(new Position(this.get_x() +1, this.get_y() -1));
        }
        if(this.get_x() > 1)
        {
            ans.add(new Position(this.get_x() - 1, this.get_y()));
            ans.add(new Position(this.get_x() - 1, this.get_y() +1));
        }
        if(this.get_y() < 7)
        {
            ans.add(new Position(this.get_x(), this.get_y() +1));
            ans.add(new Position(this.get_x() -1, this.get_y() +1));
        }
        if(this.get_y() > 0)
        {
            ans.add(new Position(this.get_x(), this.get_y() -1));
            ans.add(new Position(this.get_x() +1, this.get_y() -1));
        }

        if(this.get_x() < 7 && this.get_y() < 7)
        {
            ans.add(new Position(this.get_x() +1, this.get_y() +1));
        }

        if(this.get_x() > 0 && this.get_y() > 0)
        {
            ans.add(new Position(this.get_x() -1, this.get_y() -1));
        }
        return ans;
    }

    public boolean isLegal()
    {
        return ((this.get_x() >= 0 && this.get_y() >= 0) && ((this.get_x() <= 7 && this.get_y() <= 7)));
    }
}
