/**
 * 
 */
package processes.dsl.impl.voidtasks;

import processes.dsl.SyncStep;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface VoidSuccessStep {

	SyncStep<Void,Void> stoppingOnSuccess();
	SyncStep<Void,Void> stoppingOnSuccess(int n);
	SyncStep<Void,Void> and();
}
