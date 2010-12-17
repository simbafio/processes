/**
 * 
 */
package processes.dsl.impl.voidtasks;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface VoidModeStep {
	
	VoidFailureStep inSequence();
	VoidFailureStep inParallel();
}
