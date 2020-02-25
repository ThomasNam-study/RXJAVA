package kr.purred.playground.rxjava;

import io.reactivex.Observable;

public class SubSc
{
	public static void main (String[] args)
	{
		Observable.create (s -> {
			s.onNext ("Hello world");
			s.onComplete ();
		}).subscribe (System.out::println);

		System.out.println ("실행!!");

		Observable.create ((s) -> {
			s.onNext (1);
			s.onNext (2);
			s.onNext (3);
			s.onComplete ();
		})
				.map ((v) -> "Hello - " + v)
				.subscribe (System.out::println);
	}
}
