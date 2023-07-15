package com.spring30.spring310;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

// import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "first_name")
  @NotBlank(message = "First name is mandatory")
  private String firstName;
  @Column(name = "last_name")
  @NotBlank(message = "Last name is mandatory")
  private String lastName;
  @Column(name = "email")
  @NotBlank(message = "Email is mandatory")
  private String email;
  @Column(name = "contact_no")
  private String contactNo;
  @Column(name = "job_title")
  private String jobTitle;
  @Column(name = "year_of_birth")
  private int yearOfBirth;

  // public Customer() {
  // this.id = UUID.randomUUID().toString();
  // }

  public Customer() {
  }

  public Customer(int id, String firstName, String lastName, String email, String contactNo, String jobTitle,
      int yearOfBirth) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNo = contactNo;
    this.jobTitle = jobTitle;
    this.yearOfBirth = yearOfBirth;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    // this.firstName = "Thanos";
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContactNo() {
    return this.contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public int getYearOfBirth() {
    return this.yearOfBirth;
  }

  public void setYearOfBirth(int yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  public String getJobTitle() {
    return this.jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

}

