/**
 * 
 */
package processes.dsl;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface ConfigurationStep<TR,PR> {

	ReductionStep<TR,PR> stoppingAtFailure();
	ReductionStep<TR,PR> quittingAtFailure();
	ReductionStep<TR,PR> stoppingAtSuccess();
	ReductionStep<TR,PR> ignoringFailures();
}
