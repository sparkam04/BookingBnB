package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.PaySystem;
import com.netcracker.edu.project.service.PaySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class PaySystemController {

    @Autowired
    private PaySystemService paySystemService;

    @RequestMapping("/PaySystem")
    public Collection<PaySystem> getAllCountries() {
        return paySystemService.getAllEntities();
    }

    @RequestMapping("/PaySystem/{id}")
    public PaySystem getPaySystem(@PathVariable Long id) {
        return paySystemService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/PaySystem")
    public boolean addPaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.addEntity(paySystem);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/PaySystem")
    public boolean updatePaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.updateEntity(paySystem);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/PaySystem/{id}")
    public boolean deletePaySystem(@PathVariable Long id) {
        return paySystemService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/PaySystem")
    public boolean deletePaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.removeEntity(paySystem);
    }
}

