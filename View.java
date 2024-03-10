/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.util.ArrayList;

public class View extends JPanel
{
	private BufferedImage wallImage;
	private int scrollPosY = 0;
	private Model model;
	private Pacman pacman;

	
	public View(Controller c, Model m, Game g)
	{
		model = m;
		this.addMouseListener(c);

		pacman = new Pacman();

		try {
           // this.wallImage = ImageIO.read(new File("C:\\Users\\Jay\\OneDrive\\Desktop\\Paradigms\\Assignment 4\\First parASt\\wall.png"));
			//this.pacmanImage = ImageIO.read(new File("C:\\Users\\Jay\\OneDrive\\Desktop\\Paradigms\\Assignment 4\\First part\\pacman1.png")); 
			this.wallImage = ImageIO.read(new File("wall.png"));

        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

		
	}

	public void adjustScroll(int amount) {
		scrollPosY += amount;
		repaint();  
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
        ArrayList<Wall> walls = model.getWalls(); 
        for (Wall wall : walls) {
			//System.out.println("Wall at (" + wall.getX() + ", " + wall.getY() + 
			//") with size (" + wall.getW() + "x" + wall.getH() + ")");
            //g.drawImage(this.wallImage, wall.getX(), wall.getY(), wall.getW(), wall.getH(), null);
			g.drawImage(wallImage, wall.getX(), wall.getY() - scrollPosY, wall.getW(), wall.getH(), null);
        }
		model.getPacman().draw(g);
		
    }

}
