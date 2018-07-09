/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author zhee
 */
public class ConfigApplicationContext {
    private ApplicationContext applicationContext;
    private static ConfigApplicationContext provider;

    public ConfigApplicationContext() throws ExceptionInInitializerError {
        try {
            this.applicationContext = new ClassPathXmlApplicationContext("SpringConfig.xml");
        } catch (BeansException ex) {
            System.err.print("error " + ex);
        }
    }

    public synchronized static ConfigApplicationContext getInstance() throws ExceptionInInitializerError {
        ConfigApplicationContext tempProvider;
        if (provider == null) {
            provider = new ConfigApplicationContext();
            tempProvider = provider;
        }else if(provider.getApplicationContext()==null){
            provider=new ConfigApplicationContext();
            tempProvider=provider;
        }else{
            tempProvider=provider;
        }

        return tempProvider;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
