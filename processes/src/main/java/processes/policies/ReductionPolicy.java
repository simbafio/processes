/**
 * 
 */
package processes.policies;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import processes.Policy;
import processes.Reducer;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class ReductionPolicy<TR,AR,PR> implements Policy<TR,PR> {
	
	Policy<TR,? extends Collection<? extends AR>> inner;
	Reducer<AR,PR> reducer;
	
	public ReductionPolicy(Reducer<AR,PR> r,Policy<TR,? extends Collection<? extends AR>> s) {
		reducer=r;
		inner=s;
	}
	
	/**{@inheritDoc}*/
	@Override
	public PR execute(Collection<? extends Callable<TR>> tasks) throws ExecutionException {
		return reducer.reduce(inner.execute(tasks));
	}
}