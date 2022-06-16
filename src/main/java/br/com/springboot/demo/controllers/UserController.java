package br.com.springboot.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.demo.model.User;
import br.com.springboot.demo.repositories.UserRepository;


// essa annotation torna nossa classe um REST
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/")
  public List<User> findUsers() {
    return this.userRepository.findAll();
  }

  @PostMapping("/")
  public User createUser(@RequestBody User user){
    this.userRepository.save(user);

    return user;
  }

  @GetMapping("/{id}")
  public User findUserById(@PathVariable("id") Long id){
    Optional<User> user = this.userRepository.findById(id);

    if(user.isPresent()) {
      return user.get();
    }

    return null;
  }

  @GetMapping("/more")
  public List<User> findUsersByIdMoreThan(@RequestParam("id") Long id) {
    List<User> users = this.userRepository.findByIdGreaterThan(id);

    return users;
  }

  @GetMapping("/name/{name}")
  public List<User> findByName(@PathVariable("name") String name) {
    List<User> users = this.userRepository.findByNameIgnoreCase(name);

    return users;
  }
}
