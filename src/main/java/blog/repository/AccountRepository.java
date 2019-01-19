package blog.repository;

import blog.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findById(@Param("id") Long id);

  List<Account> findAll();

  List<Account> findByUsername(@Param("username") String username);
}
