public abstract class Person {
    protected int id ;
    protected String username;
    protected String email;
    protected String phone;
    protected String password;
    public abstract int login(String email,String password);
    public abstract void home(int id);

    public Person(){

    }

    public Person(String username, String email, String phone, String password,int id) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
