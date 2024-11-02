package orderme.service.impl;

import lombok.RequiredArgsConstructor;
import orderme.repository.UserRepository;
import orderme.service.UserService;
import orderme.service.dto.UserDto;
import orderme.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.usersToUserDtos(userRepository.findAll());
    }
}
