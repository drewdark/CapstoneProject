package com.example.capstone.service;


import com.example.capstone.model.Request;
import com.example.capstone.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<Request> getAllRequests() {
        return repository.findAll();
    }

    public Request postRequest(Request request) {
        FactorCalculator fc = new FactorCalculator();
        String quote = fc.calculateInsuranceQuote(request);
        request.setQuote(quote);
        return repository.save(request);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Request> getSingleRequest(Long id) {
        return repository.findById(id);
    }

    public Optional<Request> updatePhone(Long id, String phone) {

        return repository.findById(id)
                .map(recordForUpdating -> {
                    recordForUpdating.setPhone(phone);
                    return repository.save(recordForUpdating);
                });
    }
}
