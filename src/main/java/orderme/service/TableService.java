package orderme.service;

import orderme.service.dto.TablesDto;

import java.util.List;

public interface TableService {

    List<TablesDto> getTablesByUser();
    TablesDto createTable(TablesDto tablesDto);
    TablesDto updateTable(Integer tableId, TablesDto tablesDto);
    void deleteTable(Integer tableId);
}
