/**
 * Interface modelling any kind of person that could join a queue i.e. customer, inspector or colleague 
 * @author Ben Lawton
 */

public interface Person {
	
	public void initialiseServeTime();
	
	public int getServeTime();
}
