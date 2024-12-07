package orderme.service.mapper;

import orderme.entities.Tables;
import orderme.service.dto.TablesDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TablesMapper {

    TablesDto toDto(Tables entity);
    List<TablesDto> toDtos(List<Tables> entities);

    Tables toEntity(TablesDto dto);
    List<Tables> toEntities(List<TablesDto> dtos);
}
