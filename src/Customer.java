import java.util.Objects;
import java.util.Scanner;

public class Customer extends Users {

    public Customer(String username, String email, String phone, String password, int id) {
        super(username, email, phone, password, id);

    }

    @Override
    public int login(String email, String password) {
        for (Customer customer :Database.users){
            if(Objects.equals(customer.email, email) && Objects.equals(customer.password, password) && customer.check){
                return customer.getId();
            }
        }
        System.out.println("Invalid email or password");
        return -1;
    }

    @Override
    public void home(int id) {
        Scanner input = new Scanner(System.in);
        String in1 ;
        if (id != -1) {
            while (true) {
                System.out.println("1- request ride  \n2- exit");
                in1 = input.nextLine();
                if (Objects.equals(in1, "1")) {

                    System.out.println("Enter Your Source");

                    String source = input.nextLine();
                    //////////////////////
                    System.out.println("Enter Your Destination");
                    String destination = input.nextLine();

                    int count = 1;
                    if (Database.allRides.size() != 0) {
                        for (Ride ride : Database.allRides) {
                            if (Objects.equals(ride.getSource(), source)) {
                                System.out.println("Ride number: "+count);
                                System.out.println(" -Username: " + ride.getDriver().getUsername());
                                System.out.println(" -Phone: " + ride.getDriver().getPhone());
                                System.out.println(" -Price: " + ride.getPrice());
                                System.out.println(" -Rate: " + ride.getDriver().getAveRate());
                                System.out.println("******************************************");
                                count++;
                            }
                        }
                        if (count > 1) {
                            System.out.println("Please choose a number of ride");
                            String inID = input.nextLine();
                            int rideId = Integer.parseInt(inID);

                            System.out.println("1- Complete the booking  2-Cancel the booking");
                            String in=input.nextLine();

                            if(Objects.equals(in, "1")){

                                System.out.println("Please enter your rating from 1 to 5");
                                inID = input.nextLine();
                                double rate = Double.parseDouble(inID);
                                Ride ride = new Ride();
                                ride.bookRide(destination, rideId, rate);

                                System.out.println("Booking has been completed");
                            }else {
                                System.out.println("Canceling has been completed");
                            }

                        }else {
                            System.out.println("There aren't any matching");
                        }
                    }else{
                        System.out.println("No rides found");
                    }

                } else {
                    break;
                }
            }
        }
    }

    @Override
    public boolean signup(Users users) {
        Database.users.add((Customer) users);
        System.out.println("Registration is completed");
        return true;
    }

    @Override
    public boolean validate(String email) {
        for(Customer user:Database.users){
            if(Objects.equals(user.getEmail(), email)){
                return false;
            }
        }
        return true;
    }

    Customer(){}

}
