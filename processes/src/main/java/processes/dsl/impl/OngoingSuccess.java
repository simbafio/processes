/**
 * 
 */
package processes.dsl.impl;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.dsl.ReductionStep;
import processes.dsl.SuccessStep;
import processes.policies.ConfigurablePolicy;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingSuccess<TR,PR> implements SuccessStep<TR,PR> {
		
		private Collection<? extends Callable<TR>> tasks;
		private ConfigurablePolicy<TR,Collection<? extends TR>> policy;
		
		public OngoingSuccess(Collection<? extends Callable<TR>> ts, ConfigurablePolicy<TR,Collection<? extends TR>> p) {
			tasks=ts;
			policy=p;
		}
		
		public ReductionStep<TR, PR> stoppingOnSuccess() {
			return stoppingOnSuccess(1);
		}
		
		public ReductionStep<TR, PR> stoppingOnSuccess(int n) {
			policy.setSuccessThreshold(n);
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public ReductionStep<TR, PR> and() {
			return nextStep();
		}
		
		private ReductionStep<TR, PR> nextStep() {
			return new OngoingReduce<TR, PR>(tasks, policy);
		}
}
