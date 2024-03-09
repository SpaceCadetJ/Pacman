/*Name:Jay Krisell
  Date: 02/26/2024
  Description: This program loads a JFrame window that allows the user to build and save maps. There is also handling for adding removing wall images.  
 */
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame 
{
    private Controller controller;
    private View view;
    private Model model;

    public Game() {

        model = new Model();
        controller = new Controller(model);
        view = new View(controller, model, this);
        this.getContentPane().add(view);
    

        this.setTitle("A4 - Pac-Man");
        this.setSize(500, 500);
        this.setFocusable(true);
     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(controller);
        
    }

    public void run()
    {
        while(true)
        {
            view.repaint(); 
            Toolkit.getDefaultToolkit().sync(); 

           
            try
            {
                Thread.sleep(40);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }

}