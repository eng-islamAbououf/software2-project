import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Driver extends Users {
    protected String driver_license ;
    protected String national_id ;
    protected double aveRate ;
    public static int count ;
    protected ArrayList<SuccessfulRide> acceptedRides ;


    Driver() {
        driver_license = "" ;
        national_id = "";
        aveRate=0.0;
        count=1;
        acceptedRides = new ArrayList<>();
    }
    // Update
    public void update(Ride ride,double rate,int id){
        Database.drivers.get(id-1).setAveRate(rate);
        SuccessfulRide successfulRide = new SuccessfulRide();
        successfulRide.setRide(ride);
        successfulRide.setRate(rate);
        acceptedRides.add(successfulRide);

    }

    public void setAveRate(double aveRate) {
        this.aveRate = (this.aveRate+aveRate)/count;
        count++;
    }
    public double getAveRate() {
        return aveRate;
    }

   public ArrayList<Ride> favouriteAreas = new ArrayList<>();

    public ArrayList<Ride> getFavouriteAreas() {
        return favouriteAreas;
    }

    void showFavRides(Driver driver){
        for (Ride ride : driver.getFavouriteAreas()) {
            System.out.println("Ride ID: "+ride.getId());
            System.out.println("Price: "+ride.getPrice());
            System.out.println("Source: "+ride.getSource());

            System.out.println("----------------------------------");
        }
    }

    @Override
    public void home(int id) {
        Scanner input = new Scanner(System.in);
        String in1 ;
        if (id != -1) {
            Driver driver = Database.drivers.get(id - 1);
            while (true) {
                System.out.println("1- Add a Ride \n  2- See my favourite rides \n  3-Remove a Ride \n 4- Show accepted rides \n  5- cancel");
                in1 = input.nextLine();
                if (Objects.equals(in1, "1")) {
                    Ride ride = new Ride();
                    System.out.println("Please enter a source of ride");
                    String source = input.nextLine();

                    System.out.println("Please enter the price of ride");
                    in1 = input.nextLine();
                    double price = Double.parseDouble(in1);
                    ride.addRide(source, price, driver);

                } else if (Objects.equals(in1, "2")) {
                    showFavRides(driver);
                }

                else if(Objects.equals(in1,"3")){
                    int c=0;
                    for (Ride ride : Database.allRides) {
                            if(ride.getDriver().getId()==id){
                                System.out.println("Ride ID: "+ride.getId());
                                System.out.println("Price: "+ride.getPrice());
                                System.out.println("Source: "+ride.getSource());
                                c++;
                                System.out.println("----------------------------------");
                            }
                    }
                   if(c>0){
                       Ride ride = new Ride();
                       System.out.println("Enter number of ride");
                       String in= input.nextLine();
                       int rideId = Integer.parseInt(in);

                       ride.cancelRide(rideId);

                   }else{
                       System.out.println("You don't have any rides");
                   }
                }else if(Objects.equals(in1,"4")){
                    if(acceptedRides.size()!=0){
                        for(SuccessfulRide ride : acceptedRides){
                            System.out.println("Source: "+ride.getRide().getSource());
                            System.out.println("Destination: "+ride.getRide().getDestination());
                            System.out.println("Price: "+ride.getRide().getPrice());
                            System.out.println("Rate: "+ride.getRate());
                        }
                    }else{
                        System.out.println("No accepted rides until now...");
                    }
                }

                else {
                    break;
                }
            }
        }
    }

    @Override
    public int login(String email, String password) {
        for (Driver driver :Database.drivers){
            if(Objects.equals(driver.getEmail(), email) && Objects.equals(driver.getPassword(), password) && driver.isCheck()){
                return driver.getId();
            }
        }
        System.out.println("Invalid email or password");
        return -1;
    }


    @Override
    public boolean signup(Users users) {
        Database.driverPending.add((Driver) users);
        System.out.println("Your request is pending...");
        return true;
    }

    @Override
    public boolean validate(String email) {
        for(Driver driver:Database.drivers){
            if(Objects.equals(driver.getEmail(), email)){
                return false;
            }
        }
        return true;
    }



    public void setFavouriteAreas(ArrayList<Ride> favouriteAreas) {
        this.favouriteAreas = favouriteAreas;
    }

    public Driver(String driver_license, String national_id, String username, String email, String phone, String password, int id) {
        super(username, email, phone, password, id);
        this.driver_license = driver_license;
        this.national_id = national_id;
        acceptedRides = new ArrayList<>();
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

}