package com.cafeconsole;

import java.text.DecimalFormat;
import java.util.Scanner;


public class MyScanner {
	private static Scanner scanner = null;
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static String next() {
		checkScanner();
		String message = scanner.next();
		//Logging.info(" -- User input -- " + message);
		return message;
	}
	
	public static void sysout(String message) {
		checkScanner();
		//Logging.info(" -- System message -- " + message);
		System.out.println(message);
	}
	
	public static String scanString() {
		checkScanner();
		return scanner.next();
	}
	public static int scanInt() {
		checkScanner();
		return scanner.nextInt();
	}
	public static double scanDouble() {
		checkScanner();
		double d = scanner.nextDouble();
		long factor = (long) Math.pow(10, 2);
		d = d * factor;
		long tmp = Math.round(d);
		return (double) tmp / factor;
	}
	public static long scanLong() {
		checkScanner();
		return scanner.nextLong();
	}
	
	private static void checkScanner() {
		if(scanner == null ) {
			scanner = new Scanner(System.in);
		}
	}
}
