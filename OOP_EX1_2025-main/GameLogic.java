import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogic implements PlayableLogic
{
    private Player _player1;
    private Player _player2;
    private Position _movePosition;
    private Disc _moveDisc;

  
    ArrayList<Disc> discArrayList = new ArrayList<Disc>();
    
    @Override
    public boolean locate_disc(Position a, Disc disc)
    {
        if(getDiscAtPosition(a) != null) return false;
        ArrayList<Position> firstSurrounding = a.surrounding1();
        for(Position p: firstSurrounding)
        {
            while(p.isLegal() && !disc.sameOwner(getDiscAtPosition(p)))
            {
                p = new Position(2*p.get_x() - a.get_x(), 2*p.get_y() - a.get_y());
            }
            if(p.isLegal())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Disc getDiscAtPosition(Position position)
    {
        for (Disc disc : discArrayList) {
            if (position.equals(disc.getPosition())) {
                    return disc;
            }
        }
        return null;
    }

    @Override
    public int getBoardSize()
    {
        return 8;
    }

    @Override
    public List<Position> ValidMoves()
     {
        List<Position> ans = new ArrayList<Position>();
        Disc disc;
        for (int i=0; i< 8; i++)
        {
            for(int j=0; j< 8; j++)
            {
                Position toCheck = new Position(i, j);
                if(isFirstPlayerTurn())
                {
                    disc = new SimpleDisc(getFirstPlayer());
                }
                else
                {
                    disc = new SimpleDisc(getSecondPlayer());
                }
                if(locate_disc(toCheck, disc)) ans.add(toCheck);
            }
        }
        return ans;
    }

    @Override
    public int countFlips(Position a) {
        List<Position> validMoveList = ValidMoves();
        if(!validMoveList.contains(a)) return 0;
        Disc disc;
        int counter=0, sum=0;
        if(isFirstPlayerTurn())
                {
                    disc = new SimpleDisc(getFirstPlayer());
                }
                else
                {
                    disc = new SimpleDisc(getSecondPlayer());
                }
        for(Position p: validMoveList)
        {
            for(Position next :p.surrounding1())
            {   
                counter =0;
                if(getDiscAtPosition(next).sameOwner(disc))
                {
                    continue;
                }
                while(next.isLegal() &&!(getDiscAtPosition(next).sameOwner(disc)))
                {
                    counter++;   
                    next = new Position(2*next.get_x() - a.get_x(), 2*next.get_y() - a.get_y());
                }
                if(next.isLegal())
                {
                    sum += counter;
                }
            }
        }
        return sum;
    }

    public void flip(Position position, Disc disc)
    {
        List<Position> validMoveList = ValidMoves();
        if(!validMoveList.contains(a)) return 0;
        Disc disc;
        int counter=0, sum=0;
        if(isFirstPlayerTurn())
                {
                    disc = new SimpleDisc(getFirstPlayer());
                }
                else
                {
                    disc = new SimpleDisc(getSecondPlayer());
                }
        for(Position p: validMoveList)
        {
            for(Position next :p.surrounding1())
            {   
                counter =0;
                if(getDiscAtPosition(next).sameOwner(disc))
                {
                    continue;
                }
                while(next.isLegal() &&!(getDiscAtPosition(next).sameOwner(disc)))
                {
                    counter++;   
                    next = new Position(2*next.get_x() - a.get_x(), 2*next.get_y() - a.get_y());
                }
                if(next.isLegal())
                {
                    sum += counter;
                }
            }
        }
    }

    @Override
    public Player getFirstPlayer() {
        return _player1;
    }

    @Override
    public Player getSecondPlayer() {
        return _player2;
    }

    @Override
    public void setPlayers(Player player1, Player player2) {
        _player1 = player1;
        _player2 = player2;
    }

    @Override
    public boolean isFirstPlayerTurn() {
        return true;
    }

    @Override
    public boolean isGameFinished() {
        List<Position> ans = ValidMoves();
        if((ans.size() == 0) || (discArrayList.size() == 64))
        {
            printEndgame();
            return true;
        }
        return false;
    }

    public void printEndgame()
    {
        int sumPlayer1 =0, sumPlayer2 =0, winnerPlayer, loserPlayer;
        
        for(Disc disc: discArrayList)
        {
                if(disc.getOwner.isPlayerOne)
                {
                    sumPlayer1++;
                }
                else{
                    sumPlayer2++;
                }
        }

        if(sumPlayer1>sumPlayer2)
        {
            winnerPlayer =1;
            loserPlayer = 2;
        }
        else
        {
            winnerPlayer =2;
            loserPlayer = 1;
        }
        int loser, winner;
        loser = Math.min(sumPlayer1, sumPlayer2);
        winner = Math.max(sumPlayer1, sumPlayer2);
        System.out.println("Player " + winnerPlayer + "wins with " + winner + "discs! Player " +loserPlayer+ "had " + loser + " discs");
    }



    @Override
    public void reset() {
        discArrayList.clear();
        discArrayList.add(new SimpleDisc(getFirstPlayer()));
        discArrayList.add(new SimpleDisc(getFirstPlayer()));
        discArrayList.add(new SimpleDisc(getSecondPlayer()));
        discArrayList.add(new SimpleDisc(getSecondPlayer()));
        discArrayList.get(0).setPosition(new Position(3,3));
        discArrayList.get(1).setPosition(new Position(4,4));
        discArrayList.get(2).setPosition(new Position(3,4));
        discArrayList.get(3).setPosition(new Position(4,3));

    }

    @Override
    public void undoLastMove() {
        System.out.println("Undoing last move: \n");
        // few other rules here to look at 
    }


    public void move(Position position, Disc disc)
    {   
        System.out.println("\n");
        self._movePosition = position;
        self._moveDisc = disc;
        discArrayList.add(disc);
        print(position, disc);
    }
    
    public void print(Position position, Disc disc)
    {
        int numberPlayer;
        String discType;
        discType = disc.getType();
        if(isFirstPlayerTurn)
        {
            numberPlayer =1;
        }
        else
        {
            numberPlayer =2;
        }
        
        System.out.println("Player " + numberPlayer + " flipped the " + discType + "in " + "(" + position.get_x + "," + position.get_y +")");
    }
}
