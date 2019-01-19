package blog.model;

import javax.persistence.*;

@Entity
public class Account {
  @Id   /* specifies it as primary key */
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String username;
  @Column(name = "Account_Type")
  private String accountType;
  private double balance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public double getBalance() { return balance; }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
