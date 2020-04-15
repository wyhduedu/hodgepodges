package com.wy.hodgepodges.common.drools;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 18:25
 */

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    public String fireRule() {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // go !
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSession.insert(message);
        //插入
        kSession.fireAllRules();
        //执行规则
        kSession.dispose();
        System.out.println("sjsjsj----");
        return message.getMessage();
    }
}
