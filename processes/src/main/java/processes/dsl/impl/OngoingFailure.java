/**
 * 
 */
package processes.dsl.impl;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.dsl.FailureStep;
import processes.dsl.ReductionStep;
import processes.dsl.SuccessStep;
import processes.policies.ConfigurablePolicy;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingFailure<TR,PR> implements FailureStep<TR,PR> {
		
		private Collection<? extends Callable<TR>> tasks;
		private ConfigurablePolicy<TR,Collection<? extends TR>> policy;
		
		public OngoingFailure(Collection<? extends Callable<TR>> ts, ConfigurablePolicy<TR,Collection<? extends TR>> p) {
			tasks=ts;
			policy=p;
		}
		
		public ReductionStep<TR, PR> stoppingOnSuccess() {
			return stoppingOnSuccess(1);
		}
		
		public ReductionStep<TR, PR> stoppingOnSuccess(int n) {
			policy.setSuccessThreshold(n);
			return and();
		}
		
		/**{@inheritDoc}*/
		@Override
		public ReductionStep<TR, PR> and() {
			return new OngoingReduce<TR, PR>(tasks, policy);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SuccessStep<TR, PR> stoppingOnFailure() {
			return stoppingOnFailure(1);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SuccessStep<TR, PR> stoppingOnFailure(int n) {
			policy.setFailureStopThreshold(n);
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public SuccessStep<TR, PR> ignoringFailures() {
			return nextStep();
		}
		
		private SuccessStep<TR, PR> nextStep() {
			return new OngoingSuccess<TR, PR>(tasks, policy);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SuccessStep<TR, PR> failingOnFailure() {
			return failingOnFailure(1);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SuccessStep<TR, PR> failingOnFailure(int n) {
			policy.setFailureThreshold(n);
			return nextStep();
		}
		
}
