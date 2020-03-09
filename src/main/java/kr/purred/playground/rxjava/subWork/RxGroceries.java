package kr.purred.playground.rxjava.subWork;

import io.reactivex.Observable;

import java.math.BigDecimal;

import static kr.purred.playground.rxjava.LogLib.*;

public class RxGroceries
{
	public Observable<BigDecimal> purchase (String productName, int quantity)
	{
		return Observable.fromCallable (() -> doPurchase (productName, quantity));
	}

	public BigDecimal doPurchase (String productName, int quantity)
	{
		log ("Purchasing " + quantity + " " + productName);

		try
		{
			Thread.sleep (quantity * 500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace ();
		}

		log ("Done " + quantity + " " + productName);

		BigDecimal priceForProduct = new BigDecimal (100 * quantity);

		return priceForProduct;
	}
}
