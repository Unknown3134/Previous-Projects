
/*Adapted from Maleko, M. (2019). 
 * CPT121 Assignment 3 - SP3 2019_update of 28-10-2019.pdf. [ebook] RMIT, pp.1-17. 
 * Available at: https://rmit.instructure.com/courses/63710/files/9864660/download?wrap=1 
 * [Accessed 28 Oct. 2019].
*/

/* File read and writing adapted from Hamilton, C. (2019). 
 * 10.2.2 Maintaining Object persistence. [online] Canvas. 
 * Available at: https://rmit.instructure.com/courses/63710/pages/
 * task-10-dot-2-2-maintaining-object-persistence-videos?module_item_id=1906364 
 * [Accessed 14 Oct. 2019].
 */

/* ### Program Explanation ###
 * Flight and Holiday Booking program. Allows user to Book a flight or holiday
 * They will enter all relevant details and destination/flight will be checked
 * and then added as a new Booking. Hotel details will also take name of
 * Hotel and number of nights to stay from the user.
 * 
 * For a Flight Booking the total cost is the ticket price * the number of passengers.
 * 
 * For a Holiday Booking the total cost is the ticket price * the number of passengers 
 * + the amount of nights to booked * the hotel cost per night.
 *  
 * The user can change their Holiday booking by updating the number of nights they
 * wish to stay. The total price will then be re-calculated. The user will not
 * be able to change a Flight Booking at this time.
 * 
 * Bookings will be saved to file on close of program and reloaded upon reopening the
 *  program. On First time use the program will catch an exception to declare no file 
 *  exists to load.
 */

/* ****** Known bugs ********
 * As per the assignment spec, not all user input is validated. It is assumed that the user
 * will enter the data correctly. However, important, relevant data (destination etc)
 * is checked against records.
 * 
 * Other bugs currently not found at this time.
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManagementSystem {
	// Array list and flight records instantiated here
	private static ArrayList<Booking> bookings = new ArrayList<Booking>();
	private static Scanner sc = new Scanner(System.in);
	private static FlightRecords fr = new FlightRecords();

	// Scanner get String Input method. Allows nextLine to be called to streamline
	// each time scanner needs to be called
	public static String getInput(String display) {
		System.out.print(display);
		return sc.nextLine();
	}

	// Scanner get Double Input method. Allows nextLine to be called to streamline
	// each time scanner needs to be called
	public static double getDoubleInput(String display) {
		System.out.print(display);
		return sc.nextDouble();
	}

	// Scanner get Integer Input method. Allows nextLine to be called to streamline
	// each time scanner needs to be called
	public static int getIntInput(String display) {
		System.out.print(display);
		return sc.nextInt();
	}

	public static void main(String args[]) {

		// On program start, Loads a file of previously saved data
		File file = new File("BookingsData.txt");
		if (file.exists()) {
			loadFromFile();
			System.out.println("Previous Data has been restored Successfully");
			System.out.println();
		} else {
			System.out.println(
					"No Saved Data in System\nPlease add Bookings to create a file (File"
							+ " will save on exit)");
			System.out.println();
		}

		String selection;

		do {

			// display menu options
			System.out.println("   ***** TravelAdventure Company Booking System *****");
			System.out.println("A. Booking a Flight");
			System.out.println("B. Booking for a Holiday");
			System.out.println("C. View Invoice");
			System.out.println("D. View Itinerary");
			System.out.println("E. View Bookings");
			System.out.println("F. Update Check-in Details");
			System.out.println("X. Exit the program");
			System.out.println();

			// prompt user to enter selection

			selection = getInput("Enter selection: ");

			System.out.println();

			// validate selection input length
			if (selection.length() != 1) {
				System.out.println("Error - invalid selection!");
			} else {

				// process user's selection
				switch (selection.toUpperCase()) {

				case "A":
					bookAFlight();
					break;
				case "B":
					bookAHoliday();
					break;
				case "C":
					viewInvoice();
					break;
				case "D":
					viewItinerary();
					break;
				case "E":
					viewBookings();
					break;
				case "F":
					updateCheckinDate();
					break;
				case "X":
					saveFile();
					System.out.println("Exiting the program...");
					break;

				default:
					System.out.println("Error - invalid selection!");
				}
			}
			System.out.println();

		} while (!selection.equalsIgnoreCase("X"));

	}

	// Method allows to search for booking via booking id.
	// Throws an BookingException if booking cannot be found
	private static Booking findABooking(String bookingID) throws BookingException {

		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getBookingID().equalsIgnoreCase(bookingID)) {
				return bookings.get(i);
			}
		}
		throw new BookingException(
				"Error! This Booking could not be found in the system");
	}

	/*
	 * Book a flight method that uses Try and Catch to handle an BookingException
	 * that is thrown from the Booking constructor After Booking is added, the
	 * flight cost is calculated by passing the fr object to the
	 * calculateBookingCost method
	 */
	private static void bookAFlight() {
		String destination = getInput("Enter Destination: ").toUpperCase();
		String departureDate = getInput("Enter departure date (in DD/MM/YYYY): ");
		int numPassengers = getIntInput(
				"Enter how many Passengers to append to the booking: ");
		sc.nextLine();
		String passengerNames = "";
		for (int i = 0; i < numPassengers; i++) {
			passengerNames += getInput("Enter names of Passenger " + (i + 1) + ": ");
			while (passengerNames.isEmpty()) {
				System.out.println();
				passengerNames += getInput(
						"Error! You must enter at least 1 passenger name\nEnter names of Passenger "
								+ (i + 1) + ": ");
			}
			passengerNames += "-";

		}
		String bookingID = getInput("Enter a booking ID to save this: ").toUpperCase();
		while (bookingID.isEmpty()) {
			System.out.println();
			bookingID = getInput(
					"Error!, You must enter a string to save the Booking!\nEnter a booking ID to save this: ")
							.toUpperCase();
		}
		Booking newBooking;
		try {
			newBooking = new FlightBooking(bookingID, destination, departureDate,
					passengerNames, numPassengers, 0.0, fr);
			bookings.add(newBooking);
			newBooking.calculateBookingCost(fr);
			System.out.println();
			System.out.println("Booking Successful!  " + numPassengers
					+ " passengers added to booking");
			System.out.println(
					"The Total cost is (Flights): $" + newBooking.getTotalCost());

		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Book a flight method that uses Try and Catch to handle an BookingException
	 * that is thrown from the HolidayBooking constructor After Booking is added,
	 * the flight cost is calculated by passing the fr object to the
	 * calculateBookingCost method
	 */
	private static void bookAHoliday() {
		String destination = getInput("Enter Destination: ").toUpperCase();
		String departureDate = getInput("Enter departure date in (DD/MM/YYYY): ");
		int numPassengers = getIntInput(
				"Enter how many Passengers to append to the booking: ");
		sc.nextLine();
		String passengerNames = "";
		for (int i = 0; i < numPassengers; i++) {
			passengerNames += getInput("Enter names of Passenger " + (i + 1) + ": ");
			while (passengerNames.isEmpty()) {
				System.out.println();
				passengerNames += getInput(
						"Error! You must enter at least 1 passenger name\nEnter names of Passenger "
								+ (i + 1) + ": ");
			}
			passengerNames += "-";
		}
		Booking newBooking;
		String hotelName = getInput("Enter name of Hotel: ");
		String checkInDate = getInput("Enter Check-in date (in DD/MM/YYYY): ");
		int numNights = getIntInput("How many nights: ");
		sc.nextLine();
		double hotelPricePerNight = getDoubleInput("Enter the Hotel price per night: ");
		sc.nextLine();

		String bookingID = getInput("Enter a booking ID to save this: ").toUpperCase();
		while (bookingID.isEmpty()) {
			System.out.println();
			bookingID = getInput(
					"Error!, You must enter a string to save the Booking!\nEnter a booking ID to save this: ")
							.toUpperCase();
		}
		try {
			newBooking = new HolidayBooking(bookingID, destination, departureDate,
					passengerNames, numPassengers, 0.0, fr, hotelPricePerNight, numNights,
					checkInDate, hotelName);
			bookings.add(newBooking);
			newBooking.calculateBookingCost(fr);
			System.out.println();
			System.out.println("Booking Successful!  " + newBooking.getNumPassengers()
					+ " passengers added to booking. For a total of "
					+ ((HolidayBooking) newBooking).getNumNights() + " nights");
			System.out.println("The Total cost is (Flights and Hotel): $"
					+ newBooking.getTotalCost());

		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Searches for booking. Catches exception if not found Call print booking
	 * details method. Uses Instance of to allow for the two different heading
	 * displays
	 */
	private static void viewItinerary() {

		Booking tempBooking;
		try {
			tempBooking = findABooking(getInput("Enter Booking ID: ").toUpperCase());
			/*
			 * If instance is of Holiday Booking, it will print these headings and call
			 * HolidayBooking printBookingDetails Overridden method.
			 */
			if (tempBooking instanceof HolidayBooking) {
				System.out.println();
				System.out
						.println("Passenger name(s): " + tempBooking.getPassengerNames());
				System.out.println("------------------------------------------------");
				System.out.println();
				System.out.println("Booking ID\t\t" + "Destination\t\t"
						+ "No. of Passengers\t\t" + "Flight Number\t\t"
						+ "Departure Date\t\t" + "Hotel Name (If Booked) \t\t"
						+ "Number of Nights\t\t" + "Check-in date");
				System.out.println(
						"----------------------------------------------------------------"
								+ "------------------------------------------------------"
								+ "------------------------------------------------------"
								+ "-------------------------------------");
				tempBooking.printBookingDetails();
				System.out.println();

				/*
				 * If instance is of FlightBooking, it will print these headings and call
				 * FlightBooking printBookingDetails Overridden method,
				 */
			} else if (tempBooking instanceof FlightBooking) {
				System.out.println();
				System.out
						.println("Passenger name(s): " + tempBooking.getPassengerNames());
				System.out.println("------------------------------------------------");
				System.out.println();
				System.out.println(
						"Booking ID\t\t" + "Destination\t\t" + "No. of Passengers\t\t"
								+ "Flight Number\t\t" + "Departure Date\t\t");
				System.out.println(
						"----------------------------------------------------------------"
								+ "------------------------------------------------------"
								+ "---------------");
				tempBooking.printBookingDetails();
				System.out.println();
			}

		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}

	}

	// Prints all bookings from loop, under the standard headings, using array list
	// size()
	private static void viewBookings() {
		System.out.println();
		System.out.println("Booking ID\t\t" + "Destination\t\t" + "No. of Passengers\t\t"
				+ "Flight Number\t\t" + "Departure Date\t\t"
				+ "Hotel Name (If Booked) \t\t" + "Number of Nights\t\t"
				+ "Check-in date");
		System.out.println(
				"------------------------------------------------------------------------"
						+ "--------------------------------------------------------------"
						+ "--------------------------------------------------------------"
						+ "-------------");

		for (int i = 0; i < bookings.size(); i++) {
			bookings.get(i).printBookingDetails();

		}
		System.out.println();
	}

	/*
	 * Allows user to enter the new amount of nights to say in hotel. First checks
	 * booking exists, catches BookingException if not found If exits, calls Mutator
	 * method, casts as HolidayBooking
	 */
	private static void updateCheckinDate() {
		String bookingID = getInput("Please enter Booking ID to continue: ");
		Booking tempBooking;
		try {
			tempBooking = findABooking(bookingID);
			if (tempBooking instanceof HolidayBooking) {
				int numNights = getIntInput("Enter the new length of stay (in days): ");
				sc.nextLine();
				// Casts tempBooking as a HolidayBooking Object
				((HolidayBooking) tempBooking).setNumNights(numNights);
				tempBooking.calculateBookingCost(fr);
				System.out.println();
				System.out.println("The new Total booking has been update to: $"
						+ tempBooking.getTotalCost());
			} else
				System.out.println("Error! This is not a Holiday Booking!");
		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}

	}
	/*
	 * Searches for booking. Catches exception if not found Call print invoice
	 * details method. Uses Instance of to allow for the two different heading
	 * displays
	 */

	private static void viewInvoice() {
		String bookingID = getInput("Please enter Booking ID to continue: ");
		Booking tempBooking;
		try {
			tempBooking = findABooking(bookingID);
			if (tempBooking instanceof FlightBooking) {

				System.out.println();
				System.out.println();
				System.out
						.println("Passenger name(s): " + tempBooking.getPassengerNames());
				System.out.println("------------------------------------------------");
				System.out.println();
				System.out.println("Booking ID\t\t" + "Destination\t\t"
						+ "Price Per Flight\t\t" + "Total Cost");
				System.out.println(
						"----------------------------------------------------------------"
								+ "-------------------------------");
				tempBooking.printInvoiceDetails();
				System.out.println();
			} else if (tempBooking instanceof HolidayBooking) {

				System.out.println();
				System.out.println();
				System.out
						.println("Passenger name(s): " + tempBooking.getPassengerNames());
				System.out.println("------------------------------------------------");
				System.out.println();
				System.out.println("Booking ID\t\t" + "Destination\t\t"
						+ "Price Per Flight\t\t" + "Price Per Night\t\t"
						+ "Hotel Name\t\t\t" + "Number of Nights\t\t" + "Total Cost");
				System.out.println(
						"----------------------------------------------------------------"
								+ "--------------------------------------------------------------"
								+ "--------------------------------------------------------------"
								+ "-------------------");
				tempBooking.printInvoiceDetails();
				System.out.println();
			}
		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Using try and catch, declares a Print writer and File name for saving Using a
	 * loop to call the writeDetailsMethod of each object (Each object prints it's
	 * own details. Passes printwriter to objects
	 */
	public static void saveFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("BookingsData.txt"));
			for (int i = 0; i < bookings.size(); i++) {

				bookings.get(i).writeDetailsToFile(pw);

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			pw.close();
		}
	}

	/*
	 * Declares a file scanner for loading from text file Will call secondary
	 * constructor which takes file scanner as a parameter Will read the first line
	 * to check what type of Booking it is.
	 */
	public static void loadFromFile() {
		Scanner fsc = null;

		try {
			fsc = new Scanner(new FileReader("BookingsData.txt"));

			while (fsc.hasNext()) {
				String type = fsc.nextLine();
				if (type.equals("FlightBooking")) {
					bookings.add(new FlightBooking(fsc));
				} else if (type.equals("HolidayBooking")) {
					bookings.add(new HolidayBooking(fsc));
				}
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		} finally {
			fsc.close();
		}

	}
}
