package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class SubScribeTest
{
	public static void main (String[] args)
	{
		new SubScribeTest ().start ();
	}

	void start ()
	{
		ExecutorService poolA = newFixedThreadPool (10);
		Scheduler schedulerA = Schedulers.from (poolA);

		LogLib.log ("Start");

		Observable<String> obs = simple ();

		LogLib.log ("Create");

		Observable<String> obs2 = obs.map (x -> x)
				.filter (x -> true);

		LogLib.log ("Transformed");

		obs2
				.subscribeOn (schedulerA)
				.subscribe ((x) -> {
			LogLib.log ("Got " + x);
		}, Throwable::printStackTrace, () -> {
			LogLib.log ("Completed");
		});

		LogLib.log("Exiting");
	}

	Observable<String> simple() {
		return Observable.create ((s) -> {
			LogLib.log ("Subscribed");

			s.onNext ("A");
			s.onNext ("B");
			s.onNext ("C");
			s.onComplete ();
		});
	}
}
