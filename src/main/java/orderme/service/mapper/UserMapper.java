package orderme.service.mapper;

import orderme.entities.User;
import orderme.service.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> users);

    User userDtoToUser(UserDto userDto);
    List<User> userDtosToUsers(List<UserDto> userDtos);
}
