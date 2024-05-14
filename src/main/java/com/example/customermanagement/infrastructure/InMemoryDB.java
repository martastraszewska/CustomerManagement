package com.example.customermanagement.infrastructure;

import com.example.customermanagement.app.Customer;
import com.example.customermanagement.app.CustomerReader;
import com.example.customermanagement.app.CustomerRemover;
import com.example.customermanagement.app.CustomerStorage;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Component
public class InMemoryDB implements CustomerReader, CustomerStorage, CustomerRemover {

    public static List<Customer> storage = new ArrayList<>(
            List.of(new Customer(UUID.randomUUID().toString(),
                    "firstName",
                    "lastName",
                    "companyName",
                    "city",
                    "street",
                    "123456789",
                            "email@Address",
                    "01/01/2000",
                    Instant.now(), Instant.now()),
                    new Customer(UUID.randomUUID().toString(),
                            "firstName2",
                            "lastName2",
                            "companyName2",
                            "city2",
                            "street2",
                            "123456780",
                            "email@Address2",
                            "01/01/2000",
                            Instant.now(), Instant.now())


            )
    );


    @Override
    public Optional<Customer> findById(String id) {
        return storage.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return storage;
    }

    @Override
    public Customer store(Customer customer) {
        storage.removeIf(c -> Objects.equals(c.getId(), customer.getId()));
        storage.add(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(String id) {
        storage.removeIf(c -> Objects.equals(c.getId(), id));
    }

    public int size() {
        return storage.size();
    }

    public void deleteAll() {
        storage.clear();
    }
}
