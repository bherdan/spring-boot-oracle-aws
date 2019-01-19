package blog.model;

import javax.persistence.*;

@Entity
public class Account {

  @Id   /* specifies it as primary key */
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "USERNAME", nullable = false)
  private String username;

  @Column(name = "ACCOUNT_TYPE", nullable = false)
  private String accountType;

  @Column(name = "BALANCE", nullable = false)
  private double balance;

  public Account () {}

  public Account(String username, String accountType, double balance){
    this.username = username;
    this.accountType = accountType;
    this.balance = balance;
  }

  public Account(long id, String username, String accountType, double balance){
    this.id = id;
    this.username = username;
    this.accountType = accountType;
    this.balance = balance;
  }

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
