package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

public class ScanTEst
{
	public static void main (String[] args)
	{
		Observable<Long> progress = Observable.just (10L, 14L, 12L, 13L, 14L, 16L);

		progress.scan ((total, chunk) -> total + chunk).subscribe (System.out::println);

		Single<List<Object>> collect = Observable.range (1, 10)
				.collect (ArrayList::new, List::add);
	}
}
