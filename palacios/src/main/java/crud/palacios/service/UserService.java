package crud.palacios.service;
import crud.palacios.persistence.entities.User;
import crud.palacios.persistence.entities.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}