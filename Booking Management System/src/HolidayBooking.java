import java.io.PrintWriter;
import java.util.Scanner;

// Just 4 extra member variables for a Holiday
public class HolidayBooking extends Booking {
	private double hotelPricePerNight;
	private int numNights;
	private String checkInDate;
	private String hotelName;

	/* Constructor that throws a Booking Exception 
	 * For proof of concept, I also made this constructor throw a Booking Exception
	 * If the number of nights to stay in a Hotel are more than 100. This was just
	 * an arbitrary check to prove my understanding of exception being thrown from
	 * a constructor.
	 */
	public HolidayBooking(String bookingID, String destination, String departureDate,
			String passengerNames, int numPassengers, double totalCost, FlightRecords fr,
			double hotelPricePerNight, int numNights, String checkInDate,
			String hotelName) throws BookingException {
		super(bookingID, destination, departureDate, passengerNames, numPassengers,
				totalCost, fr);
		if (numNights <= 100) {
			this.hotelPricePerNight = hotelPricePerNight;
			this.numNights = numNights;
			this.checkInDate = checkInDate;
			this.hotelName = hotelName;
		} else
			throw new BookingException("Error! This Booking could not be created\n"
					+ "The maximum number of nights you can book is 100");
	}
	
	/* A secondary constructor that passes File scanner object
	This allows the object to be recreated from a Scanner read file
	*/
	public HolidayBooking(Scanner fsc) {
		super(fsc);
		this.hotelPricePerNight = Double.parseDouble(fsc.nextLine());
		this.numNights = Integer.parseInt(fsc.nextLine());
		this.checkInDate = fsc.nextLine();
		this.hotelName = fsc.nextLine();
		}
	
	// Overridden method which includes super class printDetails
	@Override
	public void printBookingDetails() {
		super.printBookingDetails();
		System.out.printf("%30s", this.hotelName);
		System.out.printf("%24d", this.numNights);
		System.out.printf("%32s", this.checkInDate);
		System.out.println();
	}

	// Overridden secondary print method which includes super class details
	@Override
	public void printInvoiceDetails() {
		super.printInvoiceDetails();
		System.out.printf("%20s%29s%28s%26d%32s" , "$" + super.getPricePerFlight(), "$"
				+ this.hotelPricePerNight , this.hotelName, this.numNights , "$" + super.getTotalCost());
				
		
	}
	
	// A necessary Accessor for number of nights to be staying
	public int getNumNights() {
		return this.numNights;
	}
	// A necessary Mutator for number of nights to be staying
	public void setNumNights(int numNights) {
		this.numNights = numNights;
	}

	/*Overridden calculateBookingCost method that first calls the fr.getFlightPrice
	 * to find out the cost of one ticket. It then works out the cost by using
	 * the amount of passengers (tickets) plus the hotel cost multiplied by nights.
	 * 	
	 */
	@Override
	public double calculateBookingCost(FlightRecords fr) {

		super.setPricePerFlight(fr.getFlightprice(super.getDestination()));
		double tempBookingCost = (fr.getFlightprice(super.getDestination())
				* super.getNumPassengers() + (this.hotelPricePerNight * this.numNights));
		super.setTotalCost(tempBookingCost);
		return tempBookingCost;
	}

	/* Overridden file writing method that includes super class method
	* Plus the extra details for a holiday. It prints each variable in one line
	*/
	@Override
	public void writeDetailsToFile(PrintWriter pw) {
		super.writeDetailsToFile(pw);
		pw.println(this.hotelPricePerNight);
		pw.println(this.numNights);
		pw.println(this.checkInDate);
		pw.println(this.hotelName);
	}
}
