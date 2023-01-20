package account.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@JsonPropertyOrder ({"name","lastname","email","password"})
public class User {
    @NotEmpty
    String name;

    @NotEmpty
    String lastname;

    @NotEmpty
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@acme.com")
    String email;

    @NotEmpty
    String password;

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

    @JsonPropertyOrder ({"name","lastname","email"})
    public class UserView {

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
