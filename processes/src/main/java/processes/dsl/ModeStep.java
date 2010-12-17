/**
 * 
 */
package processes.dsl;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface ModeStep<TR,PR> {
	
	FailureStep<TR,PR> inSequence();
	FailureStep<TR,PR> inParallel();
}
