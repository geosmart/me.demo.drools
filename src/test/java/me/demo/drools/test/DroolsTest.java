package me.demo.drools.test; /**
 * Created by Think on 2016/7/25.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import me.demo.drools.domain.PointDO;
import me.demo.drools.domain.Promotion;
import me.demo.drools.service.PointIssueEngine;
import me.demo.drools.service.PointIssueEngineImpl;

/**
 * Test class for ESService
 * Created by geomart on 2016/8/24.
 * @see [https://github.com/liangbo/droolsdemo/blob/master/study_note.md]
 *
 * @see me.demo.drools.service.PointIssueEngineImpl
 */
public class DroolsTest {

    @Before
    public void setup() {
        System.out.println("test begin...");
    }

    @Test
    public void test_pointRule() {
        PointIssueEngine engine = new PointIssueEngineImpl();

        PointDO point = new PointDO();
        point.setConsumeTotal(1000);
        point.setBirthdayMonth(true);
        point.setNewMemberIn3Months(true);
        point.setCountOfMonth(20);
        point.setWeekend(true);
        point.setUserName("Tom");

        Promotion promotion = new Promotion();
        engine.executeRuleEngine(Arrays.asList(point, promotion));
        System.out.println("发放消费积分:" + point.getUserName() + ":" + point.getPoint());
    }

    @After
    public void Teardown() {
        System.out.println("test stop...");
    }
}
