package Tile;

public enum Direction {

    N("North"), NE("North East"), E("East"), SE("South East"),
    S("South"), SW("South West"), W("West"), NW("North West");

    private final String expandedDirectionName;

    Direction(final String expandedDirectionName){
        this.expandedDirectionName = expandedDirectionName;
    }

    public String getDirectionString() {
        return this.expandedDirectionName;
    }
}
