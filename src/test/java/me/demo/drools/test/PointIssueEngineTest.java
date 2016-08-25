package me.demo.drools.test; /**
 * Created by Think on 2016/7/25.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.demo.drools.domain.FireAlarm;
import me.demo.drools.domain.PointDO;
import me.demo.drools.domain.Promotion;
import me.demo.drools.service.FireAlarmEngine;
import me.demo.drools.service.PointIssueEngine;

/**
 * Test class for ESService
 * Created by geomart on 2016/8/24.
 *
 * @see [https://github.com/liangbo/droolsdemo/blob/master/study_note.md]
 * @see PointIssueEngine
 */
public class PointIssueEngineTest {

    @Before
    public void setup() {
        System.out.println("test begin...");
    }

    @Test
    public void test_pointIssueEngine() {
        PointIssueEngine engine = new PointIssueEngine();

        PointDO point = new PointDO();
        point.setUserName("Tom");
        point.setConsumeTotal(1000);
        point.setNewMemberIn3Months(false);
        point.setWeekend(false);
        point.setCountOfMonth(10);
        point.setBirthdayMonth(true);

        Promotion promotion = new Promotion();
        promotion.setInPromotion(false);
        engine.executeRuleEngine(Arrays.asList(point, promotion));
        point.print();
    }

    @After
    public void Teardown() {
        System.out.println("test stop...");
    }
}
