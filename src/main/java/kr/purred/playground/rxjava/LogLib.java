package kr.purred.playground.rxjava;

public class LogLib
{
	final static long start = System.currentTimeMillis ();

	public static void log(Object label)
	{
		System.out.println (System.currentTimeMillis () - start + "\t| " + Thread.currentThread ().getName () + "\t| " + label);
	}
}
