package kr.purred.playground.rxjava;

import io.reactivex.Observable;

public class OpExample
{
	public static void main (String [] args) throws InterruptedException
	{
		Observable<Integer> mapOp1 = Observable.range (0, 100)
			.map ((d) -> d * 2);

		mapOp1.subscribe (System.out::println);

		Observable.range (2, 5)
			.flatMap ((d) -> Observable.range (0, d).map ((d1) -> d + " - " + d1))
			.subscribe (System.out::println);

		Observable.range (0, 100)
			.map ((d) -> d * 2)
			.filter ((d) -> d > 50)
			.subscribe (System.out::println);

	}
}
