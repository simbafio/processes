/**
 * 
 */
package processes.dsl;

import java.util.Collection;

import processes.Reducer;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface ReductionStep<TR,PR> {
	
	<R> SyncStep<TR,R> reducingWith(Reducer<TR,R> r);
	SyncStep<TR,TR> pickingLastResult();
	SyncStep<TR,Collection<? extends TR>> collectingResults();
	SyncStep<TR,Void> discardingResults();
}
