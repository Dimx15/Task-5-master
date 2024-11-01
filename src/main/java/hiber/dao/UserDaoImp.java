package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> listUsersByCar(String carModel, String carColor) {
      TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().
              createQuery("from User where carUser.model =:carModel and carUser.color =:carColor", User.class);
      userTypedQuery.setParameter("carModel", carModel);
      userTypedQuery.setParameter("carColor", carColor);
      return userTypedQuery.getResultList();
   }

}
