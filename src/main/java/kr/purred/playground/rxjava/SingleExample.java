package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample
{
	public static void main (String [] args)
	{
		Observable<String> helloOb = Observable.just ("Hello");

		Single.fromObservable (helloOb).subscribe (System.out::println);


		Observable.just ("Hello Single")
			.single ("default item")
			.subscribe (System.out::println);

		String [] colors = {"RED", "BLUE", "GREEN"};

		// 처음 데이터 뿌리기
		Observable.fromArray (colors)
			.first ("Default value")
			.subscribe (System.out::println);
	}
}
