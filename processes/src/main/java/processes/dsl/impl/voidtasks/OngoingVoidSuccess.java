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
public class OngoingVoidSuccess implements VoidSuccessStep {
		
		private Collection<? extends Callable<Void>> tasks;
		private ConfigurablePolicy<Void,Collection<? extends Void>> policy;
		
		public OngoingVoidSuccess(Collection<? extends Callable<Void>> ts) {
			tasks=ts;
		}
		
		public OngoingVoidSuccess(Collection<? extends Callable<Void>> ts,ConfigurablePolicy<Void,Collection<? extends Void>> p) {
			tasks=ts;
			policy=p;
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<Void,Void> and() {
			return nextStep();
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<Void,Void> stoppingOnSuccess() {
			return stoppingOnSuccess(1);
		
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<Void,Void> stoppingOnSuccess(int n) {
			policy.setSuccessThreshold(n);
			return nextStep();
		
		}
		
		private SyncStep<Void, Void> nextStep() {
			ReductionPolicy<Void,Void,Void> rp = new ReductionPolicy<Void,Void,Void>(new VoidReducer<Void>(),policy); 
			return new OngoingSync<Void,Void>(tasks,rp);
		}
		
}
