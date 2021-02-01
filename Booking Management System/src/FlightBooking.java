import java.io.PrintWriter;
import java.util.Scanner;

public class FlightBooking extends Booking {
	
	/* A FlightBooking object has no extra member variables
	 * from the abstract Super class. The difference being
	 * that Flight Booking can actually be instantiated and
	 * Booking cannot.
	 * 
	 * Constructor calls the super class constructor
	 */
	public FlightBooking (String bookingID, String destination,
			String departureDate, String passengerNames, int numPassengers, double totalCost, FlightRecords fr) throws BookingException {
		super(bookingID, destination,  departureDate, passengerNames, numPassengers, totalCost, fr);
	}
	// Secondary constructor which takes file scanner. Calling super class constructor
	public FlightBooking (Scanner fsc) {
		super(fsc);
	}
	
	// Overridden method which includes super class printDetails
	@Override
	public void printBookingDetails() {
		super.printBookingDetails();
		System.out.println();
	}
	
	// Overridden secondary print method which includes super class details
	@Override
	public void printInvoiceDetails() {
		super.printInvoiceDetails();
		System.out.print("\t" + "$"+super.getPricePerFlight() + "\t\t\t\t"+ "$"+super.getTotalCost());
	}
	/*Overridden calculateBookingCost method that first calls the fr.getFlightPrice
	 * to find out the cost of one ticket. It then works out the cost by adding
	 * the amount of passengers (tickets).
	 * 	
	 */
	@Override
	public double calculateBookingCost(FlightRecords fr) {
	
		double tempBookingCost = fr.getFlightprice(super.getDestination());
		double totalBookingCost = (tempBookingCost * super.getNumPassengers());
		super.setPricePerFlight(tempBookingCost);
		super.setTotalCost(totalBookingCost);
		return totalBookingCost;
	}
	
	/* Overridden file writing method that includes super class method
	* It calls the super class method. It prints each variable in one line
	*/
	@Override
	public void writeDetailsToFile(PrintWriter pw) {
		super.writeDetailsToFile(pw);
		
	}

}
