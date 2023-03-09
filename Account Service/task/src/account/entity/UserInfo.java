package account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table
@JsonPropertyOrder ({"name","lastname","email","password"})
public class UserInfo {


    @Id
    @JsonIgnore
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty
    @Column
    String name;

    @NotEmpty
    @Column
    String lastname;

    @NotEmpty
    @Column
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@acme.com")
    String email;

    @NotEmpty
    @Column
    String password;

    public UserInfo(){}
    public UserInfo(UserDetails user1) {
        this.email = user1.getUsername();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.email.toLowerCase();
    }

    public String toDebugString() {
        return ( "/Id: " + this.userId + " name: " + this.name + " lastname: " + this.lastname + " email: "
                + this.email + " username: " + this.getUsername() + " password: " + this.getPassword());
    }


    @JsonPropertyOrder ({"id", "name","lastname","email"})
    public class UserView {

        public long getId() {
            return userId;
        }

        public String getName() {
            return name;
        }

         public String getLastname() {
            return lastname;
        }

        public String getEmail() {
            return email;
        }

    }
}

/*
{
        "name": "John",
        "lastname": "Doe",
        "email": "johndoe@acme.com",
        "password": "secret"
}
*/
