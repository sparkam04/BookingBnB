package com.netcracker.edu.project.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    private List<TestEntity> testEntities = new ArrayList<TestEntity>(Arrays.asList(
            new TestEntity("1", "Test1"),
            new TestEntity("2", "Test2"),
            new TestEntity("3", "Test3")
    ));

    public List<TestEntity> getTestEntities() {
        return testEntities;
    }

    public TestEntity getTestEntity(String id) {
        return testEntities.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void addTestEntity(TestEntity testEntity) {
        testEntities.add(testEntity);
    }

    public void updateTestEntity(String id, TestEntity testEntity) {
        for (int i = 0; i < testEntities.size(); i++) {
            TestEntity t = testEntities.get(i);
            if (t.getId().equals(id)) {
                testEntities.set(i, testEntity);
                return;
            }
        }
    }


    public void deleteTestEntity(String id) {
        testEntities.removeIf(t -> t.getId().equals(id));
    }
}
