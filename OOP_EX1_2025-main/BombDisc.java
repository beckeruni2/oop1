public class BombDisc implements Disc {
    final private Player _owner;

    BombDisc(Player pl)
    {
        _owner = pl;
    }
    @Override
    public Player getOwner()
    {
        return _owner;
    }

    @Override
    public void setOwner(Player player)
    {
        // will ever happen?
    }

    @Override
    public String getType()
    {
        return "💣";
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

    }
    @Override
    public boolean sameOwner(Disc other) {
        return (this.getOwner().isPlayerOne() == other.getOwner().isPlayerOne()) || (!this.getOwner().isPlayerOne() == !other.getOwner().isPlayerOne());
    }
}
