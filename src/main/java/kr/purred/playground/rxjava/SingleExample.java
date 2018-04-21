package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample
{
	public static void main (String [] args)
	{
		Observable<String> helloOb = Observable.just ("Hello");

		Single.fromObservable (helloOb).subscribe (System.out::println);
	}
}
