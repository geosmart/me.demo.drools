package me.demo.drools.service;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

import java.util.List;

/**
 * Created by Think on 2016/8/24.
 */
public class PointIssueEngineImpl implements PointIssueEngine {
    KnowledgeBase knowledgeBase;

    public PointIssueEngineImpl() {
        init();
    }


    @Override
    public void init() {
        KnowledgeBuilderConfiguration config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();

        String droolsPath = System.getProperty("user.dir") + "\\bin\\drools";
        config.setProperty("drools.dump.dir", droolsPath);
        config.setProperty("drolls.dateformat", "yyyy-MM-dd HH:mm:ss");

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config);
        knowledgeBuilder.add(ResourceFactory.newFileResource(droolsPath + "\\point.drl"), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException("rule has error! pls check it!" + knowledgeBuilder.getErrors());
        }

        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }

    @Override
    public void executeRuleEngine(List<Object> list) {
        StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
        list.forEach(obj -> session.insert(obj));
        session.fireAllRules(new AgendaFilter() {
            @Override
            public boolean accept(Activation activation) {
                System.out.println("execute rule name is " + activation.getRule().getName());
                return true;
            }
        });
        session.dispose();
    }


}
