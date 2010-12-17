/**
 * 
 */
package processes.dsl;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface FailureStep<TR,PR> {

	SuccessStep<TR,PR> stoppingOnFailure();
	SuccessStep<TR,PR> stoppingOnFailure(int n);
	SuccessStep<TR,PR> failingOnFailure();
	SuccessStep<TR,PR> failingOnFailure(int n);
	SuccessStep<TR,PR> ignoringFailures();
	ReductionStep<TR,PR> and();
}
