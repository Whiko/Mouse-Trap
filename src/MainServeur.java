import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class MainServeur 
{
	public static void main(String[] args) throws SlickException
    {
    	AppGameContainer container = new AppGameContainer(new WindowGameServeur(), 900, 600, false);
        try {
            container.setTargetFrameRate(60);
            container.setMultiSample(4);
            container.setVSync(true);
       	 	container.setShowFPS(false);
            container.setAlwaysRender(true);
            container.start();
       } catch (SlickException e) {e.printStackTrace();}
       
    }
}