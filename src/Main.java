
import java.util.Objects;
import java.util.Scanner;
public class Main {
    static int userId = 1;
    static int driverId = 1;

    public static void main(String[] args) {

        Users p = new Customer();
        Users p1 = new Driver();

        Scanner input = new Scanner(System.in);

        Admin p3 = new Admin("admon", "admin@admin.com", "01118210115", "123456789", 1);
        Database.admins.add(p3);


        while (true) {
            System.out.println("1- Sign up  \n2- Login  \n3- Exit");
            String in = input.nextLine();

            if (Objects.equals(in, "1")) {
                System.out.println("1- user  2- driver");
                String in1 = input.nextLine();

                System.out.println("Enter username");
                String username = input.nextLine();

                System.out.println("Enter email");
                String email = input.nextLine();

                if(Objects.equals(in1, "1")){
                    while(!p.validate(email)){
                        System.out.println("Email already exist try again!!");
                        System.out.println("Enter email");
                        email = input.nextLine();

                    }
                }else{
                    while(!p1.validate(email)){
                        System.out.println("Email already exist try again!!");
                        System.out.println("Enter email");
                        email = input.nextLine();

                    }
                }

                System.out.println("Enter password");
                String password = input.nextLine();

                System.out.println("Enter phone");
                String phone = input.nextLine();


                if (Objects.equals(in1, "1")) {
                    p = new Customer(username, email, phone, password, userId);
                    p.signup(p);
                    userId++;

                } else if (Objects.equals(in1, "2")) {
                    System.out.println("Enter your driver license");
                    String driver_license = input.nextLine();

                    System.out.println("Enter your national id");
                    String national_id = input.nextLine();


                    p1 = new Driver(driver_license, national_id, username, email, phone, password, driverId);
                    p1.setCheck(false);
                    p1.signup(p1);
                    driverId++;

                }

            } else if (Objects.equals(in, "2")) {
                System.out.println("Enter email");
                String email = input.nextLine();

                System.out.println("Enter password");
                String password = input.nextLine();


                System.out.println("1- user  2- driver  3-admin  4- Exit");
                String in1 = input.nextLine();

                if (Objects.equals(in1, "1")) {
                    int id = p.login(email, password);
                    p.home(id);
                    //user home
                }

                else if (Objects.equals(in1, "2")) {
                    int id = p1.login(email, password);
                    p1.home(id);
                    // driver home
                }

                else if (Objects.equals(in1, "3")) {
                    int id = p3.login(email, password);
                    p3.home(id);
                    // admin home
                }

                else if (Objects.equals(in1, "4")) {
                    break;
                } else {
                    System.out.println("Wrong choice try again");
                }
            } else if (Objects.equals(in, "3")) {
                System.out.println("Exit...");
                break;
            } else {
                System.out.println("Wrong choice try again");
            }
        }
    }
}
