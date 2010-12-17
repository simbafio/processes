/**
 * 
 */
package processes.dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;

import processes.dsl.impl.OngoingMode;
import processes.dsl.impl.voidtasks.OngoingVoidMode;
import processes.dsl.impl.voidtasks.VoidModeStep;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class DSL {

	public static <TR> ModeStep<TR,Void> execute(Collection<? extends Callable<TR>> tasks) {
		return new OngoingMode<TR,Void>(tasks);
	}
	
	public static <TR> ModeStep<TR, Void> execute(Callable<TR> task) {
		return new OngoingMode<TR,Void>(Collections.singleton(task));
	}
	
	public static VoidModeStep execute(Runnable ... tasks) {
		Collection<Callable<Void>> ts = new ArrayList<Callable<Void>>();
		
		for (Runnable r : tasks)
			ts.add(callable(r));
		
		return new OngoingVoidMode(ts);
	}
	
	
	//util
	public static Callable<Void> callable(final Runnable r) {
		return new Callable<Void>() {
			public Void call() throws Exception {
				r.run();
				return null;
			}
		};
	}
}
