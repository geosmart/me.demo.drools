package me.demo.drools.service;

import java.util.List;

/**
 * Created by Think on 2016/8/24.
 */
public interface PointIssueEngine {
    void init();

    void executeRuleEngine(List<Object> list);
}
