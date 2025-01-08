package de.unibayreuth.se.taskboard.business.impl;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserPersistenceService;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserPersistenceService userPersistenceService;

    @Override
    public User create(@NotNull User user) throws UserNotFoundException, DuplicateNameException {
        return userPersistenceService.upsert(user);
    }

    @Override
    public List<User> getAll() {
        return userPersistenceService.getAll();
    }

    @Override
    public User getById(UUID id) throws UserNotFoundException {
        var user = userPersistenceService.getById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("No user with id " + id);
        }else{
            return user.get();
        }
    }

    @Override
    public void clear(){
        userPersistenceService.clear();
    }
}
