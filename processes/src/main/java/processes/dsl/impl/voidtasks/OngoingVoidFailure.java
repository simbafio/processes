/**
 * 
 */
package processes.dsl.impl.voidtasks;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.dsl.SyncStep;
import processes.dsl.impl.OngoingSync;
import processes.policies.ConfigurablePolicy;
import processes.policies.ReductionPolicy;
import processes.reducers.VoidReducer;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingVoidFailure implements VoidFailureStep {
		
		private Collection<? extends Callable<Void>> tasks;
		private ConfigurablePolicy<Void,Collection<? extends Void>> policy;
		
		public OngoingVoidFailure(Collection<? extends Callable<Void>> ts) {
			tasks=ts;
		}
		
		public OngoingVoidFailure(Collection<? extends Callable<Void>> ts,ConfigurablePolicy<Void,Collection<? extends Void>> p) {
			tasks=ts;
			policy=p;
		}
		
		/**{@inheritDoc}*/
		@Override
		public VoidSuccessStep ignoringFailures() {
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public VoidSuccessStep stoppingOnFailure() {
			return stoppingOnFailure(1);
		}
		
		/**{@inheritDoc}*/
		@Override
		public VoidSuccessStep stoppingOnFailure(int n) {
			policy.setFailureStopThreshold(n);
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public VoidSuccessStep failingOnFailure() {
			return failingOnFailure(1);
		}
		
		/**{@inheritDoc}*/
		@Override
		public VoidSuccessStep failingOnFailure(int n) {
			policy.setFailureThreshold(n);
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<Void, Void> and() {
			ReductionPolicy<Void,Void,Void> rp = new ReductionPolicy<Void,Void,Void>(new VoidReducer<Void>(),policy); 
			return new OngoingSync<Void, Void>(tasks,rp);
		}
		
		private VoidSuccessStep nextStep() {
			return new OngoingVoidSuccess(tasks, policy);
		}
	
}
