package com.netcracker.edu.project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        return "Testing";
    }

    @RequestMapping("/testList")
    public List<TestEntity> testList() {

        return testService.getTestEntities();

    }

    @RequestMapping("/testList/{id}")
    public TestEntity getTestEntity(@PathVariable String id) {
        return testService.getTestEntity(id);
    }

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
