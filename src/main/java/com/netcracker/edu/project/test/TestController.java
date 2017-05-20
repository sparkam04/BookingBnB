package com.netcracker.edu.project.test;

import com.netcracker.edu.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        return "Testing";
    }

//    @RequestMapping("/Country/{id}")
//    public Country getCountry(@PathVariable Long id) {
//        return testService.getCountry(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/Country")
//    public void addCountry(@RequestBody Country country) {
//        testService.addCountry(country);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/testList")
    public void getTestEntity(@RequestBody TestEntity testEntity) {
        testService.addTestEntity(testEntity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/testList/{id}")
    public void updateTestEntity(@RequestBody TestEntity testEntity, @PathVariable String id) {
        testService.updateTestEntity(id, testEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/testList/{id}")
    public void deleteTestEntity(@PathVariable String id) {
        testService.deleteTestEntity(id);
    }
}
