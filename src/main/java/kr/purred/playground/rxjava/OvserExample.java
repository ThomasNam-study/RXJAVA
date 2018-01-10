package kr.purred.playground.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class OvserExample
{
	public static void main (String [] args)
	{
		Observable<Long> source = io.reactivex.Observable.intervalRange (1, 10, 0L, 100L, TimeUnit.MILLISECONDS)
			.map (data -> (data + 1) * 100);

		source.subscribe (System.out::println);

		/*Observable<Long> source = io.reactivex.Observable.interval (0L, 100L, TimeUnit.MILLISECONDS)
			.map (data -> (data + 1) * 100)
			.take (5);

		source.subscribe (System.out::println);*/

		try
		{
			Thread.sleep (2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace ();
		}

	}
}
