package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class UserAdministrator extends User {

    @Override
    public UserRole getRole() {
        return UserRole.Administrator;
    }
}
