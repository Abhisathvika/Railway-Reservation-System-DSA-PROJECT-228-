package dscodes.basicds.problems.railwayreservationsystemproject;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class TicketBooking {
    private Map<String, Train> trainMap;
    private List<Passenger> passengers; //trainMap and passengers are declared private so that
    // these aren't modified by members of any other class
    int seat;
    Train train_seats= new Train(seat);
    int updatedAvailableSeats;
    public TicketBooking() { //default constructor of TicketBooking wherein you're creating new instance of trainMap&passengers
        trainMap = new HashMap<>();
        passengers = new ArrayList<>();
    }
    public void addTrain(Train train) {
        trainMap.put(train.getTrainNumber(), train);
    } //this method adds the train to the trainMap
    public void bookTicket(String trainNumber, Passenger passenger) { // method to book tickets
        Train train = trainMap.get(trainNumber); //creating an instance of train which contains the trainNumber
        if (train != null) {
            passengers.add(passenger); // code to add passenger to the ArrayList
             updatedAvailableSeats= train.getAvailableSeats()-1; //code to update the seat Availability
            train.setAvailableSeats(updatedAvailableSeats);
            System.out.println("Ticket booked for " + passenger.getFirstname()+ " "+ passenger.getLastname() + " on train " +
                    train.getTrainNumber() + " (" + train.getTrainName() + ") departing at " + train.getDepartureTime());
        } else {
            System.out.println("Invalid train number.");
        }
    }
    public void displayTrainSchedules() { // code to display the Train Schedules
        System.out.println("---------------------------------------");
        System.out.println("Train Schedules:");
        for (Train train : trainMap.values()) { // run the loop to display all the details of all the trains from trainMap
            System.out.println(train.getTrainNumber() + " - " + train.getTrainName() +
                    " (Departure Time: " + train.getDepartureTime() + ")" + " Available seats: "+ train.getAvailableSeats());
        }
    }
    public void displayPassengerDetails() { //code to display the passenger details
        System.out.println("---------------------------------------");
        System.out.println("Passenger Details:");
        int i=1;
        for (Passenger passenger : passengers) {
            System.out.println(i+"."+" Name: " + passenger.getFirstname() + " "+ passenger.getLastname()+"\n   Date Of Birth: " + passenger.getDob());
            System.out.print("\n");
            i++;
        }
        System.out.println("---------------------------------------");
    }
    public void cancelTicket( String firstName, String lastName, String trainNumber) { //code for cancelling the ticket
        boolean foundPassenger = false;
        Passenger foundPassengerObj = null;
        for (Passenger i : passengers) {
            if (i.getFirstname().equals(firstName) && i.getLastname().equals(lastName)) {//check if the firstname and
                // lastname are present in the passengers ArrayList
                foundPassenger = true;
                    foundPassengerObj = i;
                    break;}
        }
        if (foundPassenger) {//if found remove from the ArrayList and then update the seat availability
            if (trainMap.containsKey(trainNumber)) {
                passengers.remove(foundPassengerObj);
                Train canceledTrain = trainMap.get(trainNumber);
                 updatedAvailableSeats= canceledTrain.getAvailableSeats()+1;
                canceledTrain.setAvailableSeats(updatedAvailableSeats);

                System.out.println("\nTicket canceled for " + firstName + " " + lastName);
                System.out.println("---------------------------------------");
            } else {
                System.out.println("\nTrain with number " + trainNumber + " not found.");
                System.out.println("---------------------------------------");
            }
        } else {
            System.out.println("\nPassenger not found.");
            System.out.println("---------------------------------------");
        }
    }
}
