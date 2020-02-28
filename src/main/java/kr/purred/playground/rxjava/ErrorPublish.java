package kr.purred.playground.rxjava;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ErrorPublish
{
	public static void main (String[] args) throws InterruptedException
	{
		Observable
				.timer (1, TimeUnit.SECONDS)
				.subscribe ((zero) -> System.out.println ("Hello"));

		Thread.sleep (2000);
	}

	public static Observable<String> rxLoad (int id)
	{
		return Observable.create ((subscriber) -> {
			try
			{
				subscriber.onNext (String.valueOf (id));
				subscriber.onComplete ();
			}
			catch (Exception ex)
			{
				subscriber.onError (ex);
			}
		});
	}
}
