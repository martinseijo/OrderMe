package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import orderme.entities.Tables;
import orderme.entities.User;
import orderme.repository.UserRepository;
import orderme.service.TableService;
import orderme.service.UserService;
import orderme.service.dto.TablesDto;
import orderme.service.mapper.TablesMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TablesServiceImpl implements TableService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TablesMapper tablesMapper;

    @Override
    public List<TablesDto> getTablesByUser() {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return tablesMapper.toDtos(user.getTables().stream()
                .sorted(Comparator.comparing(Tables::getNumber))
                .toList());
    }
}
