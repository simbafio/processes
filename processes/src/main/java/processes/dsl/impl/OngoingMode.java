/**
 * 
 */
package processes.dsl.impl;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.dsl.FailureStep;
import processes.dsl.ModeStep;
import processes.policies.ParallelPolicy;
import processes.policies.SequentialPolicy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingMode<TR,PR> implements ModeStep<TR,PR> {
		
		private Collection<? extends Callable<TR>> tasks;
		
		public OngoingMode(Collection<? extends Callable<TR>> ts) {
			tasks=ts;
		}
		
		public FailureStep<TR,PR> inSequence() {
			return new OngoingFailure<TR,PR>(tasks, new SequentialPolicy<TR>());
		}
		
		public FailureStep<TR,PR> inParallel() {
			return new OngoingFailure<TR,PR>(tasks, new ParallelPolicy<TR>());
		}
		
}
