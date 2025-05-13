package model.B1_2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;

    public User(String u, String p, String e, String phone) {
        this.username = u;
        this.password = p;
        this.email = e;
        this.phone = phone;
    }
}
