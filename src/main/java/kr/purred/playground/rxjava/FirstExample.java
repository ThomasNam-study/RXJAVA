package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class FirstExample
{
	public void emit ()
	{
		Observable.just ("Hello", "RxJava2")
			.subscribe (System.out::println);

		Observable.just (1, 2, 3, 4, 5)
			.subscribe (System.out::println);

		Observable<Integer> integerObservable = Observable.create ((ObservableEmitter<Integer> emitter) -> {
			emitter.onNext (100);
			emitter.onNext (200);
			emitter.onNext (300);
			emitter.onNext (400);
			emitter.onComplete ();
		});

		integerObservable.subscribe ((data) -> System.out.println ("Result : " + data));

		Integer[] arr = {100, 200, 300};

		Observable.fromArray (arr)
			.subscribe (System.out::println);
	}


	public static void main (String [] args)
	{
		FirstExample demo = new FirstExample ();

		demo.emit ();
	}
}
