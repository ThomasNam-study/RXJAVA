package kr.purred.playground.rxjava;

import java.util.Scanner;

import io.reactivex.Observable;

public class RxGugudan
{
	public static void main (String [] args)
	{
		Scanner in = new Scanner (System.in);

		System.out.println ("Gugudan input : ");

		int dan = Integer.parseInt (in.nextLine ());

		Observable<String> source = Observable.just (dan)
			.flatMap ((gugu) -> Observable.range (1, 9), (gugu, i) -> gugu + " * " + i + " = " + (gugu * i));

		source.subscribe (System.out::println);




		/*
		// Version 3
		Observable<String> source = Observable.just (dan).flatMap ((num) -> Observable.range (1, 9).map ((row) -> (dan + " * " + row + " = " + (dan * row))));

		source.subscribe (System.out::println);
		*/

		// Version 2
		/*Function<Integer, Observable<String>> gugudan = (num) -> Observable.range (1, 9).map ((row) -> (dan + " * " + row + " = " + (dan * row)));

		Observable<String> source = Observable.just (dan).flatMap (gugudan);

		source.subscribe (System.out::println);*/

		// Version 1
		/*Observable<Integer> source = Observable.range (1, 9);

		source.subscribe ((row) -> System.out.println (dan + " * " + row + " = " + (dan * row)));*/
	}
}
