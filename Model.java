/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */

import java.util.ArrayList;

public class Model 
{

     private ArrayList<Wall> walls; 
     private int destX, destY;

     private Pacman pacman;

    public Model() {
        walls = new ArrayList<>(); 
        pacman = new Pacman();
    }

    public Pacman getPacman() {
        return pacman; // Getter method to access Pacman from other classes
    }

    
    public void addWall(Wall wall) {
        walls.add(wall);
    }


    public void removeWall(Wall wall) {
        walls.remove(wall);
    }


    public void removeWallAt(int x, int y) {
        walls.removeIf(wall -> x >= wall.getX() && x <= wall.getX() + wall.getW() && y >= wall.getY() && y <= wall.getY() + wall.getH());
    }

    
    public ArrayList<Wall> getWalls() {
        return walls;
    }

   
    public void clearWalls() {
        walls.clear();
    }

    public void setDestination(int x, int y) {
        this.destX = x;
        this.destY = y;
        
    }

    public void removeWallIfClicked(int clickX, int clickY) {
        boolean removed = walls.removeIf(wall -> {
            boolean isClicked = wall.isClicked(clickX, clickY);
            if (isClicked) {
                System.out.println("Removing wall at: " + wall.getX() + ", " + wall.getY());
            }
            return isClicked;
        });
        if (!removed) {
            System.out.println("No wall was clicked at: " + clickX + ", " + clickY);
        }
    }

    public void saveWalls() {
        Json wallsList = Json.newList(); 
        for (Wall wall : walls) { 
            Json wallJson = Json.newObject(); 
            wallJson.add("x", wall.getX());
            wallJson.add("y", wall.getY());
            wallJson.add("w", wall.getW());
            wallJson.add("h", wall.getH());
            wallsList.add(wallJson); 
        }
        Json.save(wallsList.toString(), "map.json");
    }
    

   public void loadWalls() {
        Json wallsArray = Json.load("map.json"); 
        walls.clear(); 
        for (int i = 0; i < wallsArray.size(); i++) {
            Json wallJson = wallsArray.get(i); 
            Wall wall = new Wall(
                (int) wallJson.getLong("x"),
                (int) wallJson.getLong("y"),
                (int) wallJson.getLong("w"),
                (int) wallJson.getLong("h")
            );
            walls.add(wall); 
        }
    }


}
