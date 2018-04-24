package kr.purred.playground.rxjava;

import io.reactivex.Observable;

public class CreateExample
{
	public static void main (String [] args) throws InterruptedException
	{

		/*Observable.interval (1, TimeUnit.SECONDS)
			.map (time -> time + "데이터")
			.take (10)
			.subscribe (System.out::println);


		Thread.sleep (13000);*/

		Observable.zip(
			Observable.just (100, 200, 300),
			Observable.just (10, 20, 30),
			(a, b) -> a + b
		)
		.zipWith (Observable.just (1, 2, 3), (ab, c) -> ab + c).subscribe (System.out::println);

	}
}
