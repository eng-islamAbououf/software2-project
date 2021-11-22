public abstract class AllRides {
    protected int id;
    protected String destination="" ;
    protected String source;
    protected double price ;
    protected Driver driver;

    public abstract void bookRide(String destination,int rideId,double rate);
    public abstract void addRide(String source,Double price,Driver driver);
    public abstract void cancelRide(int rideId);

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}