
public class GestionEntite 
{
	private boolean visible;
	private int timer;
	
	public GestionEntite()
	{
		visible = true;
		timer = 200000;
	}
	
	public void timerMort()
	{
		while(timer > 0)
			timer--;
	}
}
