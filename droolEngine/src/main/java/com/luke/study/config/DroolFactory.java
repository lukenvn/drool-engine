package com.luke.study.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;

public class DroolFactory {
    public static final String RULES_FOLDER = "/rules";
    private KieServices kieServices=KieServices.Factory.get();

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
    }
    public KieSession getKieSession(){
        KieContainer kContainer = getKieContainer();
        return kContainer.newKieSession();
    }

    public StatelessKieSession getStatelessKieSession(){
        KieContainer kContainer = getKieContainer();
        return kContainer.newStatelessKieSession();
    }

    private KieContainer getKieContainer() {
        getKieRepository();
        KieFileSystem kieFileSystem = generateKieFileSystem();
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);

        kb.buildAll();

        if (kb.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            throw new RuntimeException("Build time Errors: " + kb.getResults().toString());
        }
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    private KieFileSystem generateKieFileSystem() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        try {
            File[] files = (new File(getClass().getResource(RULES_FOLDER).toURI())).listFiles();
            Arrays.stream(files).forEach(file -> kieFileSystem.write(ResourceFactory.newFileResource(file)));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
        return kieFileSystem;
    }
}
