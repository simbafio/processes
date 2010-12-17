/**
 * 
 */
package processes;

import java.util.Collection;
import java.util.concurrent.Callable;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class Process<TR,PR> implements Callable<PR> {
	
	private Policy<TR,PR> policy;
	private Collection<? extends Callable<TR>> tasks;
	
	public Process(Collection<? extends Callable<TR>> ts,Policy<TR,PR> p) {
		policy=p;
		tasks=ts;
	}
	
	/**{@inheritDoc}*/
	public PR call() throws Exception {
		return policy.execute(tasks);
	}
	
	Collection<? extends Callable<TR>> tasks() {
		return tasks;
	}
	

}