package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

public class FirstExample
{
	public void emit ()
	{
		// 바로 발행 후 구독
		Disposable subscribe = Observable.just ("Hello", "RxJava2")
			.subscribe (System.out::println);

		System.out.println (subscribe.isDisposed ());

		// int 형 발행 후 구독
		Observable.just (1, 2, 3, 4, 5)
			.subscribe (System.out::println);

		// 옵저버 직접 구현
		Observable<Integer> integerObservable = Observable.create ((ObservableEmitter<Integer> emitter) -> {
			emitter.onNext (100);
			emitter.onNext (200);
			emitter.onNext (300);
			emitter.onNext (400);
			emitter.onComplete ();
		});

		integerObservable.subscribe ((data) -> System.out.println ("Result : " + data));

		Integer[] arr = {100, 200, 300};

		// 배열 데이터 구독
		Observable.fromArray (arr)
			.subscribe (System.out::println);


		Observable.create ((ObservableEmitter<String> e) -> {
			e.onNext ("데이터1");
			e.onNext ("데이터2");
			e.onNext ("데이터3");
			e.onNext ("데이터4");

			e.onComplete ();
		}).subscribe ((System.out::println));


	}


	public static void main (String [] args)
	{
		FirstExample demo = new FirstExample ();

		demo.emit ();
	}
}
