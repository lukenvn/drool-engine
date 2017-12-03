package com.luke.study.service;

import com.luke.study.config.DroolFactory;
import com.luke.study.constant.Constant;
import com.luke.study.facts.Message;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MessageServiceTest {
    MessageService messageService;
    public static final String ORIGINAL_MESSAGE = "Hello World";

    @Before
    public void setUp() throws Exception {
        messageService = new MessageService();
        messageService.setKsession(new DroolFactory().getKieSession());
    }

    @Test
    public void applyRule_should_return_oldMessage_when_no_rule_match() throws Exception {
        Message helloMessage = new Message();
        helloMessage.setMessage(ORIGINAL_MESSAGE);
        helloMessage.setStatus(Constant.WEIRD_STATUS);
        messageService.applyRule(helloMessage);
        assertThat(helloMessage.getMessage(),is(ORIGINAL_MESSAGE));
    }

    @Test
    public void applyRule_should_override_message() throws Exception {

        Message helloMessage = new Message();
        helloMessage.setMessage(ORIGINAL_MESSAGE);
        helloMessage.setStatus(Constant.HELLO_STATUS);
        StatelessKieSession slSession = new DroolFactory().getStatelessKieSession();
        slSession.execute(helloMessage);
        assertThat(helloMessage.getMessage(),not(ORIGINAL_MESSAGE));

    }


    @Test
    public void applyRule_should_apply_2_rule() throws Exception {
        Message helloMessage = new Message();
        helloMessage.setMessage(ORIGINAL_MESSAGE);
        helloMessage.setStatus(Constant.HELLO_STATUS);
        int numOfApplyRule = messageService.applyRule(helloMessage);
        assertThat(numOfApplyRule,is(2));

    }

    @Test
    public void applyRule_should_return_good_bye_message() throws Exception {

        Message message = new Message();
        message.setMessage(ORIGINAL_MESSAGE);
        message.setStatus(Constant.GOOD_BYE_STATUS);

        messageService.applyRule(message);
        assertThat(message.getMessage(),is(Constant.GOOD_BYE_MESSAGE));

    }

}