package com.spring30.spring310;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

    
@Entity
@Table(name = "interaction")
public class Interaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

@Column(name = "remarks")
private String remarks;

@Column(name = "interaction_date")
private LocalDate interactionDate;

@ManyToOne(optional = false)
@JoinColumn(name = "customer_id", referencedColumnName = "id")
private Customer customer;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getRemarks() {
    return remarks;
}

public void setRemarks(String remarks) {
    this.remarks = remarks;
}

public LocalDate getInteractionDate() {
    return interactionDate;
}

public void setInteractionDate(LocalDate interactionDate) {
    this.interactionDate = interactionDate;
}

public void setCustomer(Customer selectedCustomer) {
    this.customer = selectedCustomer;
}


    
}


