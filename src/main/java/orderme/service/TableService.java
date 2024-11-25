package orderme.service;

import orderme.service.dto.TablesDto;

import java.util.List;

public interface TableService {

    List<TablesDto> getTablesByUser();
}
