package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hshvedko on 19.01.2017.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "password", updatable = false)
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(Integer id, String name, String password) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String name, String password) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
    }

    public UsersDataSet(String name) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(name);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
