
public class Ride extends AllRides {

    private static int counter = 1;

    // notify
    @Override
    public void bookRide(String destination,int rideId,double rate) {
        Ride ride = Database.allRides.get(rideId-1);
        ride.setDestination(destination);
        ride.getDriver().update(ride,rate,ride.getDriver().getId());
        Database.allRides.remove(rideId-1);
    }

    @Override
    public void addRide(String source,Double price,Driver driver ){
        Ride ride = new Ride();
        ride.setSource(source);
        ride.setPrice(price);
        ride.setDriver(driver);
        ride.setId(counter);
        driver.getFavouriteAreas().add(ride);
        Database.allRides.add(ride);
        counter++;
    }

    @Override
    public void cancelRide(int rideId) {
        Ride ride = Database.allRides.get(rideId-1);
        ride.getDriver().favouriteAreas.remove(rideId-1);
        Database.allRides.remove(rideId-1);
    }
}
