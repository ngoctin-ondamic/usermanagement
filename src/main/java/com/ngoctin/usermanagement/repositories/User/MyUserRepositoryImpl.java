package com.ngoctin.usermanagement.repositories.User;

import com.ngoctin.usermanagement.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyUserRepositoryImpl implements MyUserRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public boolean updateUser(Long id, User user) {
        try {
            String sql = "UPDATE users " +
                    "SET name = ? , email = ? , phone_number = ? " +
                    "WHERE id = ?";
            Query query = entityManager.createNativeQuery(sql,User.class);
            query.setParameter(1,user.getName());
            query.setParameter(2,user.getEmail());
            query.setParameter(3,user.getPhoneNumber());
            query.setParameter(4,id);
            int check = query.executeUpdate();
            log.info("Check : {}",check);
            if(check > 0){
                return true;
            }
            return false;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        try {
            String sql = "UPDATE users " +
                    "SET is_Existed = ? " +
                    "WHERE id = ?";
            Query query = entityManager.createNativeQuery(sql,User.class);
            query.setParameter(1,true);
            query.setParameter(2,id);
            if(query.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateNameByID(Long id, String name) {
        try {
            String sql = "UPDATE userss " +
                    "SET name = ? " +
                    "WHERE id = ?";
            Query query = entityManager.createNativeQuery(sql,User.class);
            query.setParameter(1,name);
            query.setParameter(2,id);
            int check = query.executeUpdate();
            if(check > 0){
                return true;
            }
            log.warn("try");
            return false;
        }catch (UnexpectedRollbackException exception){
            log.warn("catch");
            return false;
        }
    }

}
