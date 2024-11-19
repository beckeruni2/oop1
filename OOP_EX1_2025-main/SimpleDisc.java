public class SimpleDisc implements Disc {
    private Player _owner;
    private Position _position;

    public SimpleDisc(Player pl, Position position) {
        _owner = pl;
        _position = position;
    }

    @Override
    public Player getOwner() {
        return _owner;
    }

    @Override
    public void setOwner(Player player) {
        _owner = player;
    }

    @Override
    public String getType() {
        return "⬤";
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {
        _position = position;
    }

    @Override
    public boolean sameOwner(Disc other) {
        return (this.getOwner().isPlayerOne() == other.getOwner().isPlayerOne()) || (!this.getOwner().isPlayerOne() == !other.getOwner().isPlayerOne());
    }
}
