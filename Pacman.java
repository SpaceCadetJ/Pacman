import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Pacman {
    public static int x, y; // Pacman's position
    private Image[] images = new Image[12]; // Array to hold animation frames
    private int currentImageIndex = 0; // To cycle through animation frames
    //private Image image;

    public int WIDTH, HEIGHT;
    public int prevX, prevY;
    private double speed;
    public int IMGindex;

    @Override 
    public String toString()
    {
        return "Pacman (x,y) = (" + x + ", " + y + "), index = (" + IMGindex + ")";
    }

    public Pacman() {
        loadImages();
        x = 100; // Initial X position
        y = 100; // Initial Y position
        WIDTH = 30;
        HEIGHT = 30;
    }

    public void setPrevX(int prevX){
        this.prevX = prevX;
    }
    public int getPrevX() {
        return prevX;
    }

    public void setPrevY(int prevY){
        this.prevY = prevY;
    }

    // Getter for previous Y position
    public int getPrevY() {
        return prevY;
    }

    public void setPacX(int x){
        this.x = x;
    }

    public int getPacX(){
        return x;
    }

    public void setPacY(int y){
        this.y = y;
    }

    public int getPacY(){
        return y;
    }

    public static void update()
    {	}

    private void loadImages() {
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("pacman" + (i + 1) + ".png"));
                //images[i] = ImageIO.read(new File("C:\\Users\\Jay\\OneDrive\\Desktop\\Paradigms\\Assignment 4\\First part\\pacman" + (i + 1) + ".png"));
            }
           // image = ImageIO.read(new File("C:\\Users\\Jay\\OneDrive\\Desktop\\Paradigms\\Assignment 4\\First part\\pacman1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics g) {
        g.drawImage(images[currentImageIndex], x, y, null);
       //g.drawImage(image, x, y, null);
    }

    // Methods to update `currentImageIndex` based on arrow key input will be added here
}
