package kr.purred.playground.rxjava;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import kr.purred.playground.rxjava.subWork.RxGroceries;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class SubScribeSample
{
	private ExecutorService poolA;

	private Scheduler schedulerA;

	private RxGroceries rxGroceries;

	public static void main (String[] args)
	{
		// new SubScribeSample ().version1 ();
		// new SubScribeSample ().version2 ();
		new SubScribeSample ().version3 ();
	}

	public SubScribeSample ()
	{
		poolA = newFixedThreadPool (10);
		schedulerA = Schedulers.from (poolA);

		rxGroceries = new RxGroceries ();
	}

	private void shutdownWork ()
	{
		//poolA.shutdown ();
		//schedulerA.shutdown ();
	}

	public void version1 ()
	{
		Single<BigDecimal> totalPrice = Observable.just ("bread", "butter", "milk", "tomato", "cheese")
				.subscribeOn (schedulerA)   // 잘못됐다!!.
				.map (prod -> rxGroceries.doPurchase (prod, 1))
				.reduce (BigDecimal::add)
				.toSingle ();

		System.out.println (totalPrice.blockingGet ());

		shutdownWork ();
	}

	public void version2 ()
	{
		// 이벤트 흐름이 하나뿐이고 순차적으로 실행되도록 설계했기 때문에 동시에 실행이 안된다.
		Single<BigDecimal> totalPrice = Observable.just ("bread", "butter", "milk", "tomato", "cheese")
				.subscribeOn (schedulerA)   // 잘못됐다!!.
				.flatMap (prod -> rxGroceries.purchase (prod, 1))
				.reduce (BigDecimal::add)
				.toSingle ();

		totalPrice.subscribe (System.out::println);

		shutdownWork ();
	}

	public void version3 ()
	{
		Single<BigDecimal> totalPrice = Observable.just ("bread", "butter", "milk", "tomato", "cheese")
				.flatMap (prod -> rxGroceries.purchase (prod, 1).subscribeOn (schedulerA))
				.reduce (BigDecimal::add)
				.toSingle ();

		totalPrice.subscribe (System.out::println);

		shutdownWork ();
	}
}
