package kr.purred.playground.rxjava;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class WithLastest
{
	public static void main (String[] args) throws InterruptedException
	{
		Observable<String> fast = Observable.interval (10, TimeUnit.MILLISECONDS).map (x -> "F" + x).take (5);
		Observable<String> slow  = Observable.interval (17, TimeUnit.MILLISECONDS).map (x -> "S" + x).take (5);

		// Observable.combineLatest (fast, slow, (x, y) -> x + "=" + y).subscribe (System.out::println);

		slow.withLatestFrom (fast, (x, y) -> x + "=" + y).subscribe (System.out::println);

		Thread.sleep (10 * 1000);
	}
}
