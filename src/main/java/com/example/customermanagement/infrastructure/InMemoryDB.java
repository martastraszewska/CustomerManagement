package com.example.customermanagement.infrastructure;

import com.example.customermanagement.app.Customer;
import com.example.customermanagement.app.CustomerExistsByIdReader;
import com.example.customermanagement.app.CustomerReader;
import com.example.customermanagement.app.CustomerStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class InMemoryDB implements CustomerReader, CustomerStorage, CustomerExistsByIdReader {

    private static List<Customer> storage = new ArrayList<>();


    @Override
    public Optional<Customer> findById(String id) {
        return storage.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findFirst();
    }

    @Override
    public Customer store(Customer customer) {
        storage.removeIf(c -> Objects.equals(c.getId(), customer.getId()));
        storage.add(customer);
        return customer;
    }

    @Override
    public boolean existsById(String id) {
        return storage.stream().anyMatch(c -> Objects.equals(c.getId(), id));
    }

    public int size() {
        return storage.size();
    }
}
