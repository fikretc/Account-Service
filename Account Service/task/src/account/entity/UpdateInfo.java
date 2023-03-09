package account.entity;

public class UpdateInfo {

    String new_password;

    public UpdateInfo() {}

    public void UpdateInfo (String newPassword) {
        this.new_password = newPassword;

    }


    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
