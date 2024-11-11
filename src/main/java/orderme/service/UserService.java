package orderme.service;

import orderme.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllUsers();
}
