package br.com.springboot.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springboot.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

  // :id -> referencia a que vai receber pelo parametro do metodo              
  @Query("SELECT u from User u where u.id > :id")
  public List<User> findAllMoreThan(@Param("id") Long id);

  // jeito que o jPA fornece a partir de um campo
  public List<User> findByIdGreaterThan(Long id);
  
  public List<User> findByNameIgnoreCase(String name);

}
