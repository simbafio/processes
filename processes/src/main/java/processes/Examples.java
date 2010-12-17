/**
 * 
 */
package processes;

import static processes.dsl.DSL.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class Examples {

	public static void main(String[] args) throws Exception {
		
		Runnable r1 = new MyRunnable(1);
		Runnable r2 = new MyRunnable(2);
		
		//execute all in background
		execute(r1,r2).inSequence().and().notBlocking();
		
		//execute until one succeeds, ignoring failures
		execute(r1,r2).inSequence().ignoringFailures().stoppingOnSuccess().blocking();

		
		//execute and tolerate no more than 2 failues 
		execute(r1,r2).inSequence().failingOnFailure(2).and().blocking();
		
		execute(r1,r2).inParallel().ignoringFailures().stoppingOnSuccess().blocking();
		

		
		Collection<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		tasks.add(new MyCallable<Integer>(1,1,2000));
		tasks.add(new MyCallable<Integer>(2,2,5000));

		//execute all in background grouping the results
		execute(tasks).inSequence().and().discardingResults().notBlocking();
		
		//executing in background until one succeeds, discarding the result
		execute(tasks).inSequence().ignoringFailures().stoppingOnSuccess().discardingResults().notBlocking();
		
		//execute and stop at first success picking the result, but fail at first failure
		execute(tasks).inSequence().failingOnFailure().stoppingOnSuccess().pickingLastResult().blocking();
		
		//execute until second failure, collecting the results
		execute(tasks).inSequence().stoppingOnFailure(2).and().collectingResults().blocking();
		
		//execute in background until second success, picking last result but fail at second failure
		execute(tasks).inSequence().failingOnFailure(2).stoppingOnSuccess(2).pickingLastResult().notBlocking();
		
		//execute in background until there is a failure discarding all the previous results
		execute(tasks).inSequence().stoppingOnFailure().and().discardingResults().notBlocking();

		Collection<? extends Integer> ints = execute(tasks).inParallel().ignoringFailures().stoppingOnSuccess().collectingResults().blocking().call();
		System.out.println("done");
		System.out.println("done with "+new ArrayList<Integer>(ints));
	}

	public static class MyCallable<T> implements Callable<T> {
		private int i;
		private T t;
		private long sleep;
		
		public MyCallable(int i, T t, long s) {
			this.i=i;
			this.t=t;
			sleep=s;
		}
		
		/**{@inheritDoc}*/
		@Override
		public T call() throws Exception  {
			System.out.println("task "+i);
			Thread.sleep(sleep);
			System.out.println("task "+i+" done");	
			return t;
		}
	}
	
	public static class MyRunnable implements Runnable {
		private int i;
		
		public MyRunnable(int i) {
			this.i=i;
		}
		
		/**{@inheritDoc}*/
		@Override
		public void run()  {
			try {
				System.out.println("task "+i);
				Thread.sleep(2000);
				System.out.println("task "+i+" done");					
			}catch (Exception e) {}
		}
	}
}
