package com.capston.dao;

import com.capston.entities.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractGenericDao<UserEntity> {

    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("username", username));
        Object object = criteria.uniqueResult();

        if (object != null) {
            return (UserEntity) object;
        }
        throw new UsernameNotFoundException("User was not found");
    }
}
