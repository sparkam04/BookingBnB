package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.PaySystem;
import com.netcracker.edu.project.service.PaySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PaySystemController {

    @Autowired
    private PaySystemService paySystemService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/paysystem")
    public Collection<PaySystem> getAllPaySystems() {
        return paySystemService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/paysystem/{id}")
    public PaySystem getPaySystem(@PathVariable Long id) {
        return paySystemService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/paysystem")
    public boolean addPaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.addEntity(paySystem);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/paysystem")
    public boolean updatePaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.updateEntity(paySystem);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/paysystem/{id}")
    public boolean deletePaySystem(@PathVariable Long id) {
        return paySystemService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/paysystem")
    public boolean deletePaySystem(@RequestBody PaySystem paySystem) {
        return paySystemService.removeEntity(paySystem);
    }
}
