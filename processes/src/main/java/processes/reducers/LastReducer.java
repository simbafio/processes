/**
 * 
 */
package processes.reducers;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import processes.Reducer;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class LastReducer<TR> implements Reducer<TR,TR>  {
		
	public TR reduce(Collection<? extends TR> outputs) throws ExecutionException {
		
		if (outputs.size()==0)
				throw new ExecutionException(new IllegalArgumentException("no results for "+this));
		
		Iterator<? extends TR> it = outputs.iterator();
		
		
		TR last = null;
		while (it.hasNext())
			last = it.next();

		return last;
	}
}
