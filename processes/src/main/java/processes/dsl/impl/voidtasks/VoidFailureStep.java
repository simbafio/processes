/**
 * 
 */
package processes.dsl.impl.voidtasks;

import processes.dsl.SyncStep;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface VoidFailureStep {

	VoidSuccessStep stoppingOnFailure();
	VoidSuccessStep stoppingOnFailure(int n);
	VoidSuccessStep failingOnFailure();
	VoidSuccessStep failingOnFailure(int n);
	VoidSuccessStep ignoringFailures();
	SyncStep<Void, Void> and();
}
