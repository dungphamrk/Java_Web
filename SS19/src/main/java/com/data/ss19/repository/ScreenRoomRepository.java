package com.data.ss19.repository;


import com.data.ss19.model.ScreenRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScreenRoomRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<ScreenRoom> findByStatus(Boolean status) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM ScreenRoom WHERE status = :status ORDER BY id DESC", ScreenRoom.class)
                .setParameter("status", status)
                .getResultList();
    }

    public ScreenRoom findById(Long id) {
        return sessionFactory.getCurrentSession().get(ScreenRoom.class, id);
    }

    public void save(ScreenRoom screenRoom) {
        sessionFactory.getCurrentSession().save(screenRoom);
    }

    public void update(ScreenRoom screenRoom) {
        sessionFactory.getCurrentSession().update(screenRoom);
    }

    public void delete(Long id) {
        ScreenRoom screenRoom = findById(id);
        if (screenRoom != null) {
            screenRoom.setStatus(false);
            update(screenRoom);
        }
    }
}

