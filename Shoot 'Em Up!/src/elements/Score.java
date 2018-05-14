package elements;

public class Score implements Comparable<Object>
{
	private String name;
	private long score;

	public Score(String name, long score)
	{
		this.name = name;
		this.score = score;
	}
	
	public String getName()
	{
		return name;
	}
	
	public long getScore()
	{
		return score;
	}
	
	public String toString()
	{
		return name + " : "+ ((Long)(score)).toString();
	}

	@Override
	public int compareTo(Object otherScore) 
	{
		return (int)(score -((Score)(otherScore)).getScore());
	}

}
