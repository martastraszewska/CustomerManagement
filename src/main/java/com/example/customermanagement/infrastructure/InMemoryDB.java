package com.example.customermanagement.infrastructure;

import com.example.customermanagement.app.Customer;
import com.example.customermanagement.app.CustomerReader;
import com.example.customermanagement.app.CustomerStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Component
public class InMemoryDB implements CustomerReader, CustomerStorage {

    private static List<Customer> storage = new ArrayList<>();


    @Override
    public Optional<Customer> findById(Customer customer) {
        return storage.stream()
                .filter(c -> Objects.equals(c.getId(), customer.getId()))
                .findFirst();
    }

    @Override
    public Customer store(Customer customer) {
        storage.add(customer);
        return customer;
    }
}
