package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class SubjectExample
{
	public static void main (String [] args) throws InterruptedException
	{
		// 마지막 데이터만 수신한다.
		AsyncSubject<String> asyncSubject = AsyncSubject.create ();

		Observable<String> justOb = Observable.just ("A", "B", "C", "D", "E", "F");

		asyncSubject.subscribe ((data) -> {
			System.out.println ("AsyncSubject : " + data);
		});

		justOb.subscribe (asyncSubject);


		AsyncSubject<String> asyncSubjectDirect = AsyncSubject.create ();

		asyncSubjectDirect.onNext ("DATA1");
		asyncSubjectDirect.onNext ("DATA2");


		asyncSubjectDirect.onComplete ();

		asyncSubjectDirect.subscribe (System.out::println);


		BehaviorSubject<String> behaviorSubject = BehaviorSubject.createDefault ("TEST");

		behaviorSubject.onNext ("DATA1");
		behaviorSubject.onNext ("DATA2");
		behaviorSubject.subscribe (System.out::println);
		behaviorSubject.onNext ("DATA3");
		behaviorSubject.onNext ("DATA4");
		behaviorSubject.onComplete ();;


		Observable<String> justOb2 = Observable.just ("A", "B", "C", "D", "E", "F");

		ConnectableObservable<String> connectableOb = justOb2.publish ();

		connectableOb.subscribe ((data) -> System.out.println ("Sub 1# : " + data));
		connectableOb.subscribe ((data) -> System.out.println ("Sub 2# : " + data));

		connectableOb.connect ();

		Thread.sleep (1000);

		connectableOb.subscribe ((data) -> System.out.println ("Sub 3# : " + data));
	}
}
