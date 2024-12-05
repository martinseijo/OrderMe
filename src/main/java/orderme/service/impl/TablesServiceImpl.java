package orderme.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import orderme.entities.Tables;
import orderme.entities.User;
import orderme.repository.TablesRepository;
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
    private final TablesRepository tablesRepository;

    @Override
    public List<TablesDto> getTablesByUser() {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return tablesMapper.toDtos(tablesRepository.findByUser(user).stream()
                .sorted(Comparator.comparing(Tables::getNumber))
                .toList());
    }

    @Override
    public TablesDto createTable(TablesDto tablesDto) {
        User user = userRepository.findByUsername(userService.getAuthenticatedUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Tables table = tablesMapper.toEntity(tablesDto);
        table.setUser(user);
        Tables savedTable = tablesRepository.save(table);
        return tablesMapper.toDto(savedTable);
    }

    @Override
    public TablesDto updateTable(Integer tableId, TablesDto tablesDto) {
        Tables table = tablesRepository.findById(tableId).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        table.setNumber(tablesDto.getNumber());
        table.setName(tablesDto.getName());
        table.setDescription(tablesDto.getDescription());
        Tables updatedTable = tablesRepository.save(table);
        return tablesMapper.toDto(updatedTable);
    }

    @Override
    public void deleteTable(Integer tableId) {
        Tables table = tablesRepository.findById(tableId).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        tablesRepository.delete(table);
    }
}
