/**
 * 
 */
package processes.policies;

import static java.util.concurrent.Executors.*;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import processes.Policy;
import processes.Process;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class AsyncPolicy<TR,PR> implements Policy<TR,Future<PR>> {
	
	Policy<TR,PR> syncPolicy;
	
	public AsyncPolicy(Policy<TR,PR> p) {
		syncPolicy=p;
	}
	
	/**{@inheritDoc}*/
	@Override
	public  Future<PR> execute(Collection<? extends Callable<TR>> tasks) {
		
		ExecutorService service = newSingleThreadExecutor(); 
		Future<PR> future = service.submit(new Process<TR,PR>(tasks,syncPolicy));
		service.shutdown();
		return future;
		
	}
}