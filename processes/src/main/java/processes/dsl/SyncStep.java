/**
 * 
 */
package processes.dsl;

import java.util.concurrent.Future;
import processes.Process;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public interface SyncStep<TRESULT,RESULT> {
	
	Process<TRESULT,RESULT> blocking();
	Process<TRESULT,Future<RESULT>> notBlocking();
}