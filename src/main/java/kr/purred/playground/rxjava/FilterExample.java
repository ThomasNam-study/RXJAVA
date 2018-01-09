package kr.purred.playground.rxjava;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class FilterExample
{
	public static void main (String [] args)
	{

		String [] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIALOG", "5 CIRCLE", "6 HEXAGON"};

		Observable<String> source = Observable.fromArray (objs)
			.filter (obj -> obj.endsWith ("CIRCLE"));

		source.subscribe (System.out::println);


		String [] balls = {"1", "3", "5"};

		Maybe<String> source2 = Observable.fromArray (balls)
			.reduce ((b1, b2) -> b2 + "(" + b1 + ")");

		source2.subscribe (System.out::println);


		List<Pair<String, Integer>> sales = new ArrayList<> ();

		sales.add (Pair.of ("TV", 2500));
		sales.add (Pair.of ("Camera", 300));
		sales.add (Pair.of ("TV", 1600));
		sales.add (Pair.of ("Phone", 800));

		Maybe<Integer> tvSales = Observable.fromIterable (sales)
			.filter (sale -> "TV".equals (sale.getLeft ()))
			.map (Pair::getRight)
			.reduce ((sale1, sale2) -> sale1 + sale2);

		tvSales.subscribe ((tot) -> System.out.println ("TV Sales : $" + tot));
	}
}
