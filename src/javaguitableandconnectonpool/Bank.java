package javaguitableandconnectonpool;


import java.sql.Date;


public class Bank {


    private final Integer id;
    private final String name;
    private final String surname;
    private final Double balance;
    private final String address;
    private final String gender;
    private final Date birthday;
   
  
    public Bank(Integer id, String name,String surname,Double balance,String address,String gender, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.balance=balance;
        this.address=address;
        this.gender = gender;
        this.birthday=birthday;
    
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Double getBalance() {
        return balance;
    }
    public String getAddress(){
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
     return "name" +" " + " surname";
    }

  
 

}
