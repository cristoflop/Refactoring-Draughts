package usantatecla.draughts.models;

class Turn {

    private Color color;

    Turn() {
        this.color = Color.WHITE;
    }

    void change() {
        this.color = this.getOppositeColor();
    }

    Color getColor() {
        return this.color;
    }

    Color getOppositeColor() {
        return Color.getOppositeColor(this.color);
    }

    void reset() {
        this.color = this.getStartColor();
    }

    Color getStartColor() {
        return Color.getStartColor();
    }

    @Override
    public String toString() {
        return this.color.name();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Turn other = (Turn) obj;
        if (color != other.color)
            return false;
        return true;
    }

}