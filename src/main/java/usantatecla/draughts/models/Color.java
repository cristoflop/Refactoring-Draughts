package usantatecla.draughts.models;

public enum Color implements usantatecla.draughts.utils.Color {
    WHITE,
    BLACK,
    NULL;

    private final int[] LIMITS = new int[]{5, 2};

    public boolean isInitialRow(final int row) {
        switch (this) {
            case WHITE:
                return row >= LIMITS[this.ordinal()];
            case BLACK:
                return row <= LIMITS[this.ordinal()];
        }
        return false;
    }

    static Color getInitialColor(final Coordinate coordinate) {
        if (coordinate.isBlack())
            for (Color color : Color.values())
                if (!color.isNull() && color.isInitialRow(coordinate.getRow()))
                    return color;
        return Color.NULL;
    }

    static Color getOppositeColor(Color color) {
        Color oppositeColor = Color.NULL;
        switch (color) {
            case WHITE:
                oppositeColor = Color.BLACK;
                break;
            case BLACK:
                oppositeColor = Color.WHITE;
                break;
        }
        return oppositeColor;
    }

    static Color getStartColor() {
        return Color.WHITE;
    }

    @Override
    public boolean isNull() {
        return this == Color.NULL;
    }
}