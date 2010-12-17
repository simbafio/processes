/**
 * 
 */
package processes.policies;

import static java.util.concurrent.Executors.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

import processes.Policy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class ParallelPolicy<TR> extends ConfigurablePolicy<TR,Collection<? extends TR>> 
									   implements Policy<TR,Collection<? extends TR>> {
	
	
	/**{@inheritDoc}*/
	public List<TR> execute(Collection<? extends Callable<TR>> tasks) throws ExecutionException {
		
		ExecutorService executor = newFixedThreadPool(tasks.size());
		CompletionService<TR> service = new ExecutorCompletionService<TR>(executor);
		
		List<TR> results = new ArrayList<TR>();
		
		for (Callable<TR> task : tasks)
			service.submit(task);
			
		executor.shutdown();

		while(results.size()<tasks.size()) {
			try {
				results.add(service.take().get());
				if (successThreshold()) break;
			}
			catch(ExecutionException e) {
				if (failureThreshold(e))
					break;
			}
			catch(InterruptedException e) {
				//think!
			}
		}

		
		return results;
	}
}
