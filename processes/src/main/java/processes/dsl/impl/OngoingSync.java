/**
 * 
 */
package processes.dsl.impl;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import processes.Policy;
import processes.dsl.SyncStep;
import processes.policies.AsyncPolicy;

import processes.Process;;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class OngoingSync<TRESULT,RESULT> implements SyncStep<TRESULT, RESULT> {
	
	private Collection<? extends Callable<TRESULT>> tasks;
	private Policy<TRESULT,RESULT> policy;
	
	public OngoingSync(Collection<? extends Callable<TRESULT>> ts, Policy<TRESULT,RESULT> p) {
		tasks=ts;
		policy=p;
	}

	/**{@inheritDoc}*/
	@Override
	public Process<TRESULT, RESULT> blocking() {
		return new Process<TRESULT,RESULT>(tasks,policy);
	}

	/**{@inheritDoc}*/
	@Override
	public Process<TRESULT, Future<RESULT>> notBlocking() {
		return new Process<TRESULT, Future<RESULT>>(tasks, new AsyncPolicy<TRESULT,RESULT>(policy)) ;
	}
}
