package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;

@Service
public interface UserService {

    User findByUsername(String username);

    void addNewUser(User user);
}
