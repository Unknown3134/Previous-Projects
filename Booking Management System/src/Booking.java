import java.io.PrintWriter;
import java.util.Scanner;

// Abstract class with the most integral member variables
public abstract class Booking {

	private String bookingID;
	private String destination;
	private String flightNumber;
	private String departureDate;
	private int numPassengers;
	private String passengerNames;
	private double totalCost;
	private double pricePerFlight;

	/* Constructor that throws a Booking Exception 
	 * if the passed Destination is not found in flight records.
	 * Flight records fr is also passed on attempted creation.
	 */
	public Booking(String bookingID, String destination, String departureDate,
			String passengerNames, int numPassengers, double totalCost, FlightRecords fr)
			throws BookingException {

		if (fr.checkFlightDestination(destination)) {
			this.bookingID = bookingID;
			this.destination = destination;
			this.flightNumber = fr.getFlightNumber(destination);
			this.departureDate = departureDate;
			this.numPassengers = numPassengers;
			this.passengerNames = passengerNames;
			this.totalCost = totalCost;

		} else

			throw new BookingException("Error, this Booking could not be created\n"
					+ "the passenger destination is not available from the Flight Records");

	}
	
	/* A secondary constructor that passes File scanner object
	This allows the object to be recreated from a Scanner read file
	*/
	public Booking(Scanner fsc) {
		this.bookingID = fsc.nextLine();
		this.destination = fsc.nextLine();
		this.flightNumber = fsc.nextLine();
		this.departureDate = fsc.nextLine();
		this.passengerNames = fsc.nextLine();
		this.numPassengers = Integer.parseInt(fsc.nextLine());
		this.totalCost = Double.parseDouble(fsc.nextLine());
		this.pricePerFlight = Double.parseDouble(fsc.nextLine());
	}


	/* Very useful print booking details method
	This is called from a few application methods.
	*/
	public void printBookingDetails() {
		System.out.printf("%10s%24s%22d%32s%29s",this.bookingID  ,this.destination,	 
				this.numPassengers , this.flightNumber  , this.departureDate);
	}
	// Secondary print method for invoice printing only
	public void printInvoiceDetails() {
		System.out.print(this.bookingID + "\t\t\t" + this.destination + "\t\t");
	}

	// Abstract method for calculating booking cost, passes in flight records
	public abstract double calculateBookingCost(FlightRecords fr);

	//All necessary accessors
	public String getBookingID() {
		return this.bookingID;
	}
	
	public String getDestination() {
		return this.destination;
	}

	public int getNumPassengers() {
		return this.numPassengers;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public String getPassengerNames() {
		return this.passengerNames;
	}
	public double getPricePerFlight() {
		return this.pricePerFlight;
	}

	
	/* Set total cost mutator. This is called when either
	* of the subclasses calculate Booking cost, they will
	* then set the cost here.
	*/
	public void setTotalCost(double bookingCost) {
		this.totalCost = bookingCost;

	}

	/* This mutator is needed for setting the booking class's
	 * price per flight after retrieving it from Flight records
	 * and passing it to caculateBooking cost
	 */
	public void setPricePerFlight(double tempBookingCost) {
		this.pricePerFlight = tempBookingCost;

	}

	/* Takes a print writer object as a parameter
	 * and prints each member variable to file.
	 * It prints each variable in one line.
	 */
	public void writeDetailsToFile(PrintWriter pw) {
		pw.println(getClass().getSimpleName());
		pw.println(this.bookingID);
		pw.println(this.destination);
		pw.println(this.flightNumber);
		pw.println(this.departureDate);
		pw.println(this.passengerNames);
		pw.println(this.numPassengers);
		pw.println(this.totalCost);
		pw.println(this.pricePerFlight);

	}

}
