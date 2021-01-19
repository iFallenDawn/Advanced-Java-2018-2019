
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
public class GLRunner
{
	public static void main(String[] args) throws Exception
	{
		DecimalFormat df = new DecimalFormat("#.00");
		while(true)
		{
			long startTime = System.nanoTime();
			Thread.sleep((int)(Math.random() * 10000));
			long stopTime = System.nanoTime();

			System.out.println("Elapsed time was " + df.format((double)(stopTime - startTime) / 1000000000)  + " seconds.");
		}
	}
}