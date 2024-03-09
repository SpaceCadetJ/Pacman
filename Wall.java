/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */

public class Wall {
    private int x; 
    private int y; 
    private int w; 
    private int h; 

  
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean isClicked(int clickX, int clickY) {
        return clickX >= x && clickX <= x + w && clickY >= y && clickY <= y + h;
    }

    // Getter for the X-coordinate
    public int getX() {
        return x;
    }

    // Setter for the X-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Getter for the Y-coordinate
    public int getY() {
        return y;
    }

    // Setter for the Y-coordinate
    public void setY(int y) {
        this.y = y;
    }

    // Getter for the width of the wall
    public int getW() {
        return w;
    }

    // Setter for the width of the wall
    public void setW(int w) {
        this.w = w;
    }

    // Getter for the height of the wall
    public int getH() {
        return h;
    }

    // Setter for the height of the wall
    public void setH(int h) {
        this.h = h;
    }


    public Json toJson() {
        Json wallJson = Json.newObject();
        wallJson.add("x", this.x);
        wallJson.add("y", this.y);
        wallJson.add("width", this.w);
        wallJson.add("height", this.h);
        return wallJson;
    }

    public Wall(Json json) {
        this.x = (int) json.getLong("x");
        this.y = (int) json.getLong("y");
        this.w = (int) json.getLong("w");
        this.h = (int) json.getLong("h");
    }
    
    public Json marshal() {
        Json obj = Json.newObject();
        obj.add("x", this.x);
        obj.add("y", this.y);
        obj.add("w", this.w);
        obj.add("h", this.h);
        return obj;
    }
}
