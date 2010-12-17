/**
 * 
 */
package processes.dsl.impl;

import java.util.Collection;
import java.util.concurrent.Callable;

import processes.Policy;
import processes.Reducer;
import processes.dsl.ReductionStep;
import processes.dsl.SyncStep;
import processes.policies.ReductionPolicy;
import processes.reducers.LastReducer;
import processes.reducers.VoidReducer;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingReduce<TR,PR> implements ReductionStep<TR,PR>{
		
		private Collection<? extends Callable<TR>> tasks;
		private Policy<TR,Collection<? extends TR>> policy;
		
		public OngoingReduce(Collection<? extends Callable<TR>> ts, Policy<TR,Collection<? extends TR>> p) {
			tasks=ts;
			policy=p;
		}
		
		/**{@inheritDoc}*/
		@Override
		public <R> SyncStep<TR,R> reducingWith(Reducer<TR,R> r) {
			ReductionPolicy<TR,TR,R> rp = new ReductionPolicy<TR,TR,R>(r, policy); 
			return new OngoingSync<TR,R>(tasks,rp);
		}
		
		public SyncStep<TR,Void> discardingResults() {
			ReductionPolicy<TR,TR,Void> rp = new ReductionPolicy<TR,TR,Void>(new VoidReducer<TR>(), policy);
			return new OngoingSync<TR,Void>(tasks,rp);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<TR, Collection<? extends TR>> collectingResults() {
			return new OngoingSync<TR, Collection<? extends TR>>(tasks,policy);
		}
		
		/**{@inheritDoc}*/
		@Override
		public SyncStep<TR, TR> pickingLastResult() {
			
			ReductionPolicy<TR,TR,TR> rp = new ReductionPolicy<TR,TR,TR>(new LastReducer<TR>(), policy);
			
			return new OngoingSync<TR,TR>(tasks,rp);
		}
		
}
