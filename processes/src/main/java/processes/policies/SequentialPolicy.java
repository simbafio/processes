/**
 * 
 */
package processes.policies;

import static java.util.concurrent.Executors.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import processes.Policy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class SequentialPolicy<TR> extends ConfigurablePolicy<TR,Collection<? extends TR>> 
									   implements Policy<TR,Collection<? extends TR>> {
	
	/**{@inheritDoc}*/
	public List<TR> execute(Collection<? extends Callable<TR>> tasks) throws ExecutionException {
		
		ExecutorService service = newSingleThreadExecutor();
		
		List<TR> results = new ArrayList<TR>();
		
		try {
				for (Callable<TR> task : tasks) {
			
				try {
					TR result = service.submit(task).get();
					results.add(result);
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
		}
		finally {
			service.shutdown();		
		}

		return results;
	}

}
