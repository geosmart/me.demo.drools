package me.demo.drools.service;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

/**
 * Created by Think on 2016/8/24.
 */
public abstract class BaseRuleEngine {
    private static String droolsPath = System.getProperty("user.dir") + "\\bin\\drools";
    private static KnowledgeBase knowledgeBase;
    private KnowledgeBuilderConfiguration config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();

    public KnowledgeBase getKnowledgeBase() {
        if (knowledgeBase == null) {
            init();
        }
        return knowledgeBase;
    }

    public void init() {
        if (knowledgeBase == null) {
            config.setProperty("drools.dump.dir", droolsPath);
            config.setProperty("drolls.dateformat", "yyyy-MM-dd HH:mm:ss");
            knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        }
    }

    public void addResourceByDrl(String drlFileName) {
        if (knowledgeBase == null) {
            init();
        }
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config);
        knowledgeBuilder.add(ResourceFactory.newFileResource(droolsPath + "\\" + drlFileName), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException("rule has error! pls check it!" + knowledgeBuilder.getErrors());
        }
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
    }
}
