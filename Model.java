/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */

import java.util.ArrayList;

public class Model 
{

     static public ArrayList<Wall> walls; 
     private int destX, destY;

     private Pacman pacman;

    public Model() {
        walls = new ArrayList<>(); 
        pacman = new Pacman();
    }

    public Pacman getPacman() {
        return pacman; // Getter method to access Pacman from other classes
    }

    public void update()
    {
        Pacman.update();
    }

    public void checkCollisions() {
        for (Wall wall : walls) {
            if (isColliding(getPacman(), wall)) {
                resolveCollision(getPacman(), wall);
            }
        }
    }

    public boolean isColliding(Pacman pacman, Wall wall) {
        // Basic AABB collision detection
        System.out.println("Wall (x,y) = (" + wall.getX() + ", " + wall.getY() + "), w = " + wall.getW() + ", h = " + wall.getH());
        System.out.println("Pacman (x,y) = (" + pacman.getPacX() + ", " + pacman.getPacY() + "), with width = " + pacman.getPacX() + pacman.WIDTH + " ,with height = " + pacman.getPacY() + pacman.HEIGHT);

        pacman.toString();
        walls.toString();
        return pacman.getPacX() < wall.getX() + wall.getW() &&
               pacman.getPacX() + pacman.WIDTH > wall.getX() &&
               pacman.getPacY() < wall.getY() + wall.getH() &&
               pacman.getPacY() + pacman.HEIGHT > wall.getY();
    }

    // public boolean check = isColliding(getPacman(), Wall);
    
    private void resolveCollision(Pacman pacman, Wall wall) {
        // Simplified resolution logic, adjust as needed
        if (pacman.getPrevX() + pacman.WIDTH <= wall.getX()) {
            pacman.setPacX(wall.getX() - pacman.WIDTH); // Collision from right
        } else if (pacman.getPrevX() >= wall.getX() + wall.getW()) {
            pacman.setPacX(wall.getX() + wall.getW()); // Collision from left
        }
        if (pacman.getPrevY() + pacman.HEIGHT <= wall.getY()) {
            pacman.setPacY(wall.getY() - pacman.HEIGHT); // Collision from bottom
        } else if (pacman.getPrevY() >= wall.getY() + wall.getH()) {
            pacman.setPacY(wall.getY() + wall.getH()); // Collision from top
        }
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
