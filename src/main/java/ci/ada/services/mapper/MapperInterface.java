package ci.ada.services.mapper;

import java.util.List;

public interface MapperInterface <DTO,ENTITY> {
    ENTITY toEntity(DTO d);

    DTO toDTO(ENTITY entity);

    List<DTO> toDTOs(List<ENTITY> entities);
}
