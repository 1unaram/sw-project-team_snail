package final_project;

public class Marker {
    String type;
    String size;
    String color;
    int label;
    String posX;
    String posY;

    Marker(String size, String posX, String posY, String type, int label) {
        this.size = size;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.label = label;
    }

    @Override
    public String toString() {
        String ret = "&markers=type:" + type + "|size:" + size + "|pos:" + posX + "%20" + posY + "|label:"
                + Integer.toString(label);

        return ret;
    }
}