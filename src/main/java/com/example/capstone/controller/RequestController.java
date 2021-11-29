package com.example.capstone.controller;

import com.example.capstone.model.Request;
import com.example.capstone.service.RequestService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {
;
    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @GetMapping("/requests")
    List<Request> getAllRequests() {
        return service.getAllRequests();
    }

    @PostMapping("/requests")
    Request save(@RequestBody Request request) {
        return service.postRequest(request);
    }

    @DeleteMapping("/requests/{id}")
    void deleteRecord(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/requests/{id}")
    Optional<Request> getSingleRequest(@PathVariable Long id) {
        return service.getSingleRequest(id);
    }

    @PutMapping("/requests")
    void updateAPhoneNumber(@RequestParam Long id, @RequestParam String phone) {
        service.updatePhone(id, phone);
    }

}
