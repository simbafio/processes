/**
 * 
 */
package processes.reducers;

import java.util.Collection;

import processes.Reducer;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class VoidReducer<TR> implements Reducer<TR,Void>  {
		
		/**{@inheritDoc}*/
		@Override public Void reduce(Collection<? extends TR> outputs) {
			return null;
		}
}
