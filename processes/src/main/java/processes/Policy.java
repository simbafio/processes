/**
 * 
 */
package processes;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface Policy<TR,PR> {
	
	PR execute(Collection<? extends Callable<TR>> tasks) throws ExecutionException;
}
