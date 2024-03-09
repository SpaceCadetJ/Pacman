/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controller implements  MouseListener, KeyListener
{
	private View view;
	private Model model;

	private boolean editMode = false;
    private boolean addingWalls = true;

	

	//private boolean keyLeft;
	//private boolean keyRight;
	//private boolean keyUp;
	//private boolean keyDown;


	public Controller(Model m)
	{
		model = m;
		model.loadWalls();
	}

	public void setView(View v)
	{
		view = v;
	}


	public void mousePressed(MouseEvent e)
	{
		if (editMode) {
			if (addingWalls) {
				
				Wall wall = new Wall(e.getX(), e.getY(), 100, 100); 
				model.addWall(wall);
			} else {
				
				model.removeWallIfClicked(e.getX(), e.getY());
			}
			view.repaint(); 
		}

	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}



	public void keyPressed(KeyEvent e)
	{
		
		final int SCROLL_AMOUNT = 10;
		switch(e.getKeyCode())
		{
		
			case KeyEvent.VK_Q:
			if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') 
			{ 
			System.exit(0);
			} 

            case KeyEvent.VK_ESCAPE: 
			System.exit(0); break;

			case KeyEvent.VK_E:
            editMode = !editMode; 
            if (editMode) 
			{
                addingWalls = true;
            }
            System.out.println("Edit mode: " + (editMode ? "ON" : "OFF"));
            break;

        	case KeyEvent.VK_A:
            if (editMode) 
			{
                addingWalls = !addingWalls; 
                System.out.println("Adding Walls: " + addingWalls);
            }
            break;


        case KeyEvent.VK_C:
            model.clearWalls(); 
            view.repaint(); 
            System.out.println("Cleared all walls");
            break;

		case KeyEvent.VK_UP:
			view.adjustScroll(-SCROLL_AMOUNT);
			break;

		case KeyEvent.VK_DOWN:
			view.adjustScroll(SCROLL_AMOUNT);
			break;
			//repaint();
			
		}	

		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			model.saveWalls();
		} else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
			model.loadWalls();
			view.repaint(); 
		}

		view.repaint();
		
	}


	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}
}
