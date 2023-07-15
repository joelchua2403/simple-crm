package com.spring30.spring310;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  Customer getCustomer(int id);

  List<Customer> getAllCustomers();

  Customer updateCustomer(int id, Customer customer);

  void deleteCustomer(int id);

    Interaction addInteractionToCustomer(int customerId, Interaction interaction);

}
