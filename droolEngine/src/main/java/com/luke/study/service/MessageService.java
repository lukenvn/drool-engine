package com.luke.study.service;

import com.luke.study.facts.Message;
import org.kie.api.runtime.KieSession;

public class MessageService {

    private KieSession ksession;

    public void setKsession(KieSession ksession) {
        this.ksession = ksession;
    }


    public int applyRule(Message message){
        ksession.insert(message);
        return ksession.fireAllRules();

    }
}
