package com.github.throyer.cars.modules.user.repositories.custom;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.user.models.User;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomUserRepository {
  private final EntityManager manager;

  public Page<User> findAllFetchRoles(Pageable pageable) {
    var range = manager
      .createQuery("select u.id from user u", Long.class);
    
    var query = manager
      .createQuery("select distinct u from user u join fetch u.roles where u.id in (:user_ids) order by u.id", User.class);

    var count = manager
      .createQuery("select count(u.id) from user u", Long.class).getSingleResult();

    var pageNumber = pageable.getPageNumber();
    var pageSize = pageable.getPageSize();

    range.setFirstResult(pageNumber * pageSize);
    range.setMaxResults(pageSize);
    
    List<Long> ids = range.getResultList();

    query.setParameter("user_ids", ids);
    
    List<User> content = query.getResultList();

    return Page.of(content, pageNumber, pageSize, count);
  }
}
