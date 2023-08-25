package com.zukeramrenzo.workshopmongo.services;

import com.zukeramrenzo.workshopmongo.domains.User;
import com.zukeramrenzo.workshopmongo.dto.UserDTO;
import com.zukeramrenzo.workshopmongo.repositories.UserRepository;
import com.zukeramrenzo.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user){
        return repo.insert(user);
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User user){
        User newUser = repo.findById(user.getId()).get();
        updateData(newUser, user);
        return repo.save(newUser);
    }

    private void updateData(User newUser, User user){
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
