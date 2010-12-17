/**
 * 
 */
package processes.dsl.impl.voidtasks;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.policies.ParallelPolicy;
import processes.policies.SequentialPolicy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingVoidMode implements VoidModeStep {
		
		private Collection<? extends Callable<Void>> tasks;
		
		public OngoingVoidMode(Collection<? extends Callable<Void>> ts) {
			tasks=ts;
		}
		
		public VoidFailureStep inSequence() {
			return new OngoingVoidFailure(tasks,new SequentialPolicy<Void>());
		}
		
		public VoidFailureStep inParallel() {
			return new OngoingVoidFailure(tasks,new ParallelPolicy<Void>());
		}
		
}
