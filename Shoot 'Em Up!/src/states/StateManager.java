package states;

public class StateManager 
{
	private State currentState;
	
	public StateManager()
	{
		currentState = null;
	}

	public StateManager(State currentState)
	{
		this.currentState = currentState;
	}
	
	public void setState(State currentState)
	{
		this.currentState = currentState;
	}
	
	public State getState()
	{
		return currentState;
	}

}
