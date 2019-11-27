package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Label;
import com.milos.releasemicroservice.service.dto.LabelDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReleaseInItemDTOMapper.class)
public interface LabelDetailDTOMapper extends EntityMapper<LabelDetailDTO, Label> {
    default Label toEntity(LabelDetailDTO dto) {
        return null;
    }

    @Override
    LabelDetailDTO toDto(Label entity);
}
