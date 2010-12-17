/**
 * 
 */
package processes.dsl;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface SuccessStep<TR,PR> {

	ReductionStep<TR,PR> stoppingOnSuccess();
	ReductionStep<TR,PR> stoppingOnSuccess(int n);
	ReductionStep<TR,PR> and();
}
