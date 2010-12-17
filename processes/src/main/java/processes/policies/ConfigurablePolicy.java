/**
 * 
 */
package processes.policies;

import java.util.concurrent.ExecutionException;

import processes.Policy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public abstract class ConfigurablePolicy<TR,PR> implements Policy<TR,PR> {

	int failureThreadshold;
	int failureStopThreshold;
	int successThreshold;
	
	int failures;
	int successes;
	
	protected synchronized boolean failureThreshold(Exception e) throws ExecutionException {
		failures++;
		if (failures==failureThreadshold) 
			throw new ExecutionException(failureThreadshold+" tasks failed, the last with exception",e);
		return failures==failureStopThreshold;
	}
	
	protected synchronized boolean successThreshold() {
		successes++;
		return successes==successThreshold; 
	}
	
	/**{@inheritDoc}*/
	public void setFailureStopThreshold(int n) {
		if (n<1)
			throw new IllegalArgumentException();
		failureStopThreshold=n;
	}
	
	/**{@inheritDoc}*/
	public void setSuccessThreshold(int n) {
		if (n<1)
			throw new IllegalArgumentException();
		successThreshold=n;
	}
	
	/**{@inheritDoc}*/
	public void setFailureThreshold(int n) {
		if (n<1)
			throw new IllegalArgumentException();
		failureThreadshold=n;
	}

}
