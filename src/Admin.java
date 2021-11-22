import java.util.Objects;
import java.util.Scanner;

public class Admin extends Person {

    public Admin(String username, String email, String phone, String password, int id) {
        super(username,email,phone,password,id);
    }

    Scanner input = new Scanner(System.in);

    public void driversPending(){

       if (Database.driverPending.size() != 0){
           System.out.println("All pending drivers:");

           for (int i=0; i<Database.driverPending.size(); i++){
               System.out.println("Driver "+(i+1));

               System.out.println("ID: "+Database.driverPending.get(i).id);
               System.out.println("Username: "+Database.driverPending.get(i).username);
               System.out.println("Email: "+Database.driverPending.get(i).email);
               System.out.println("Phone: "+Database.driverPending.get(i).phone);
               System.out.println("National ID: "+Database.driverPending.get(i).national_id);
               System.out.println("Driver License: "+Database.driverPending.get(i).driver_license);
               System.out.println("Check: "+Database.driverPending.get(i).check);

               System.out.println("///////////////////////////////////////////////");

           }
           System.out.println("Please choose a drivers:");
           int user = input.nextInt();

           System.out.println("1- accept \n" + "2- reject");

           int check = input.nextInt();

           if (check==1){

               Database.driverPending.get(user-1).setCheck(true);
               Database.drivers.add(Database.driverPending.get(user-1));
               Database.driverPending.remove(user-1);

           }else if(check==2){

               Database.driverPending.remove(user-1);

           }else{
               System.out.println("Wrong choice !!!");
           }
       }
       else{
           System.out.println("No drivers pending");
       }

    }

    public void suspend(){
        System.out.println("1- Users \n" + "2- Drivers");

        int choice = input.nextInt();

        if(choice ==1){
           if(Database.users.size() != 0){
               for(Customer customer :Database.users){
                   System.out.println("Users "+ customer.id);
                   System.out.println("Username: "+ customer.username);
                   System.out.println("Email: "+ customer.email);
                   System.out.println("Phone: "+ customer.phone);
                   System.out.println("Suspended: "+!customer.check);
                   System.out.println("///////////////////////////////////////////////");
               }

               System.out.println("Enter user's id ");
               int id=input.nextInt();
               takeAction(id-1,1);
           }else{
               System.out.println("Users not found");
           }
        }

        else if(choice==2){

           if(Database.drivers.size()!=0){
               for(Driver driver:Database.drivers){
                   System.out.println("Driver "+driver.id);
                   System.out.println("Username: "+driver.username);
                   System.out.println("Email: "+driver.email);
                   System.out.println("Phone: "+driver.phone);
                   System.out.println("Suspended: "+!driver.check);
                   System.out.println("National Id: "+driver.national_id);
                   System.out.println("Driver License: "+driver.driver_license);
               }

               System.out.println("Enter driver's id ");
               int id=input.nextInt();
               takeAction(id-1,2);

           }else{
               System.out.println("Drivers not found");
           }
        }

        else{
            System.out.println("Wrong choice...");
        }
    }

    private void takeAction(int id , int type){
        System.out.println("""
                1- Suspend\s
                2- Activate\s
                3- Cancel""");

        int choice2 = input.nextInt();

        if(type == 1){

            if(choice2==1){
                Database.users.get(id).check=false;
            }
            else if(choice2==2){
                Database.users.get(id).check=true;
            }

        }
        else if(type == 2){
            if(choice2==1){
                Database.drivers.get(id).check=false;
            }
            else if(choice2==2){
                Database.drivers.get(id).check=true;
            }
        }
    }

    @Override
    public int login(String email, String password) {
        for(Admin admin:Database.admins){
            if(Objects.equals(admin.getEmail(), email) && Objects.equals(admin.getPassword(), password)){
                return admin.getId();
            }
            else{
                return -1;
            }
        }
        return -1;
    }

    @Override
    public void home(int id) {
        Scanner input = new Scanner(System.in);
        String in1 ;
        //Database database = new Database();
        if (id != -1) {
            while (true) {
                System.out.println("1- Pending Drivers \n2- Suspend User\n3- Cancel");
                in1 = input.nextLine();
                if (Objects.equals(in1, "1")) {
                    Database.admins.get(0).driversPending();
                } else if (Objects.equals(in1, "2")) {
                    Database.admins.get(0).suspend();
                } else {
                    break;
                }
            }
        }
        else
            System.out.println("Invalid Email or username !!");
    }
}
