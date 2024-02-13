package authservice.authservice.model.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

import authservice.authservice.model.IUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User implements IUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = true)
    @JsonProperty("phoneNumber")
    private String phone;

    @Column (nullable = true)
    private String address;

    @Column (nullable = true)
    private String birthdate;


  

    public User(){

    }

    public User(String username, String password, String email, String name){
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public User(String username, String password, String email, String phone, String address, String birthdate){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
      
    }


    public String getUsername(){
        return this.username;
    }


    public void setUsername(String username){
        this.username = username;
    }


    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
}
