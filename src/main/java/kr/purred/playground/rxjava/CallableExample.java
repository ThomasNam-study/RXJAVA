package kr.purred.playground.rxjava;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;

public class CallableExample
{
	public static void main (String [] args) throws InterruptedException
	{

		Callable<String> callable = () -> {
			Thread.sleep (1000);
			return "Hello Callable";
		};

		Observable<String> source = Observable.fromCallable (callable);

		source.subscribe (System.out::println);

		Future<String> future = Executors.newSingleThreadExecutor ().submit (() -> {
			Thread.sleep (1000);
			return "Hello future";
		});

		Observable.fromFuture (future).subscribe (System.out::println);

		Thread.sleep (5000);

		System.out.println ("종료");
	}
}


