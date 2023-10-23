package dscodes.basicds.problems.railwayreservationsystemproject;
import java.time.LocalDate;
import java.util.Scanner;

public class Railways {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketBooking ticketBooking = new TicketBooking(); //create an instance of TicketBooking class
        int seatsinTrain=50;
        Train trainSeats= new Train(seatsinTrain);
        // Adding sample trains
        ticketBooking.addTrain(new Train("123", "Express One", "10:00 AM",50 ));
        ticketBooking.addTrain(new Train("456", "Express Two", "02:30 PM", 50));
        ticketBooking.addTrain(new Train("789", "Express Three", "11:00 AM",50 ));
        ticketBooking.addTrain(new Train("1221", "Express Four", "04:30 PM", 50));

        while (true) {// Provide the options to the user
            System.out.println("---------------------------------------");
            System.out.println("\nWelcome to Railway Reservation System: ");
            System.out.println("Click on any of the options provided below to proceed\n");
            System.out.println("1. Book Ticket");
            System.out.println("2. Check Train Schedules");
            System.out.println("3. Display Passenger Details");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:// Enter the details to book the train ticket
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter train number: ");
                    String trainNumber = scanner.nextLine();
                    System.out.print("Enter the number of seats you want to book: ");
                    int seats= scanner.nextInt();
                    scanner.nextLine();
                    if(trainSeats.getAvailableSeats()>seats){
                        int leftSeats= trainSeats.getAvailableSeats()-seats;
                        trainSeats.setAvailableSeats(leftSeats);
                        for(int i=0;i<seats;i++){//Run the loop to collect passenger details for the number of seats
                            System.out.println("---------------------------------------");
                            System.out.println("Passenger "+(i+1)+" Details\n");
                            System.out.print("Enter passenger First name: ");
                            String passengerFName = scanner.nextLine();
                            System.out.print("Enter passenger Last name: ");
                            String passengerLName = scanner.nextLine();
                            System.out.print("Enter passenger date of birth (YYYY-MM-DD) : ");
                            String dob = scanner.nextLine();
                            LocalDate bookingDate = LocalDate.parse(dob);
                            System.out.println("---------------------------------------");
                            Passenger passenger = new Passenger(passengerFName, passengerLName, dob);
                            //create an instance of Passenger class having parameters name and dob;
                            ticketBooking.bookTicket(trainNumber, passenger);
                        }
                    }
                    else{// if the numbers of seats requested are not available
                        System.out.println("Sorry, the number of seats you've requested for are not available");
                    }
                    break;
                case 2: // display the train schedules by calling the method displayTrainSchedules from ticketBooking
                    ticketBooking.displayTrainSchedules();
                    break;
                case 3: // displays the passenger details by  calling displayPassengerDetails method from ticketBooking
                    ticketBooking.displayPassengerDetails();
                    break;
                case 4:// to cancel the ticket
                    scanner.nextLine(); // Consume newline character
                    System.out.println("---------------------------------------\n");
                    System.out.print("Enter passenger First name for ticket cancellation: ");
                    String cancelPassengerFName = scanner.nextLine();
                    System.out.print("Enter passenger Last name for ticket cancellation: ");
                    String cancelPassengerLName= scanner.nextLine();
                    System.out.print("Enter the train number: ");
                    String cancelPassengerTno= scanner.nextLine();
                    Passenger cancelPassenger = new Passenger(cancelPassengerFName, cancelPassengerLName);//creates an instance
                    //of  passenger to collect the details of the passenger to cancel the ticket.
                    ticketBooking.cancelTicket(cancelPassengerFName, cancelPassengerLName, cancelPassengerTno);
                    break;
                case 5://Exits from the program.
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
