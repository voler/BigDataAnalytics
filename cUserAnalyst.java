import java.io.FileNotFoundException;

public class cUserAnalyst {

  public static void main(String[] args) {

		cDataset ds;
		cAnalytics ads;
		try{
			ds = new cDataset ("C:/Users/voler_000/Dropbox/Northwestern/Study Quarter 3/MSIA431/HW1/WeeklyTemperature.csv");
			ds.display();		
			ds.display(1);
			ds.display(-1);
			ds.display(1,2);
			ds.display(2,1);
			ds.display(-1,2);
			ds.display(1,501);
			ds.display("NAT");
			ds.display("AAA");
			
			ads = new cAnalytics ("C:/Users/voler_000/Dropbox/Northwestern/Study Quarter 3/MSIA431/HW1/WeeklyTemperature.csv");
			
			ads.display();
			System.out.println("Min Temperature is " + ads.minimum());
			System.out.println("Max Temperature is " + ads.maximum());
			System.out.println("Average Temperature is " + ads.calcMean());
			System.out.println("Standard deviation is " + ads.stdev());
			System.out.println();
			
			ads.display(1);
			System.out.println("Min Temperature is " + ads.minimum(1));
			System.out.println("Max Temperature is " + ads.maximum(1));
			System.out.println("Average Temperature is " + ads.calcMean(1));
			System.out.println("Standard deviation is " + ads.stdev(1));
			System.out.println();
			
			ads.display(1,2);
			System.out.println("Min Temperature is " + ads.minimum(1,2));
			System.out.println("Max Temperature is " + ads.maximum(1,2));
			System.out.println("Average Temperature is " + ads.calcMean(1,2));
			System.out.println("Standard deviation is " + ads.stdev(1,2));
			System.out.println();
			
			ads.display("NAT");
			System.out.println("Min Temperature is " + ads.minimum("NAT"));
			System.out.println("Max Temperature is " + ads.maximum("NAT"));
			System.out.println("Average Temperature is " + ads.calcMean("NAT"));
			System.out.println("Standard deviation is " + ads.stdev("NAT"));
			System.out.println();

			System.out.println("NE Standard deviation is " + ads.stdev("NE"));
			System.out.println("ENC Standard deviation is " + ads.stdev("ENC"));
			System.out.println("C Standard deviation is " + ads.stdev("C"));
			System.out.println("SE Standard deviation is " + ads.stdev("SE"));
			System.out.println("WNC Standard deviation is " + ads.stdev("WNC"));
			System.out.println("S Standard deviation is " + ads.stdev("S"));
			System.out.println("SW Standard deviation is " + ads.stdev("SW"));
			System.out.println("NW Standard deviation is " + ads.stdev("NW"));
			System.out.println("W Standard deviation is " + ads.stdev("W"));
			System.out.println("NAT Standard deviation is " + ads.stdev("NAT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
