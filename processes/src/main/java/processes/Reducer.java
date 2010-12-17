/**
 * 
 */
package processes;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface Reducer<TR,PR> {
	
	PR reduce(Collection<? extends TR> outputs) throws ExecutionException;

}
