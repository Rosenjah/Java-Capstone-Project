package com.capston;

import com.capston.entities.KidEntity;
import com.capston.entities.ParentEntity;
import com.capston.entities.TeamEntity;
import com.capston.entities.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getSession() {

        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ParentEntity.class)
                .addAnnotatedClass(KidEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(TeamEntity.class)
                .buildSessionFactory();

        return sessionFactory;
    }
}
