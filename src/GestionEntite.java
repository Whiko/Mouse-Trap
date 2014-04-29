
public class GestionEntite 
{
	private boolean visible;
	private int timer;
	
	public GestionEntite()
	{
		visible = true;
		timer = 600;
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	public void timerMort()
	{
		if(timer > 0)
			timer--;
	}
}
