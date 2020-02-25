package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.TimeUnit;

public class SpeakSample
{
	public static void main (String[] args) throws InterruptedException
	{
		Observable<String> alice = speak ("To be, or not to be: that is the question", 110);
		Observable<String> bob = speak ("Though the be madness, yet there is method in't", 90);
		Observable<String> jane = speak ("There are more things in Heaven and Earth, Horatio, than area dreamt of in your philosophy", 100);

		Observable.concat (
				alice.map (w -> "Alice : " + w),
				bob.map (w -> "Bob : " + w),
				jane.map (w -> "Jane : " + w)
		).subscribe (System.out::println);

		Thread.sleep (10000);
	}

	static Observable<String> speak (String quote, long millIsPerChar)
	{
		String [] tokens = quote.replaceAll ("[:,]", "").split (" ");

		Observable<String> words = Observable.fromArray (tokens);
		Observable<Long> delays = words.map (String::length).map ((len) -> len * millIsPerChar)
				.scan (Long::sum);

		return words.zipWith (delays.startWith (0L), Pair::of)
				.flatMap ((pair) -> Observable.just (pair.getLeft ())
						.delay (pair.getRight (), TimeUnit.MILLISECONDS)
				);

	}
}
