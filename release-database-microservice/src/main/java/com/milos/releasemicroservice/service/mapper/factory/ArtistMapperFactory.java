package com.milos.releasemicroservice.service.mapper.factory;

import com.milos.releasemicroservice.service.mapper.ArtistDetailDTOMapper;
import com.milos.releasemicroservice.service.mapper.EntityMapper;
import com.milos.releasemicroservice.service.mapper.ItemInExploreDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("artistMapperFactory")
public class ArtistMapperFactory implements AbstractMapperFactory {

    @Autowired
    private ArtistDetailDTOMapper artistDetailDTOMapper;
    @Autowired
    private ItemInExploreDTOMapper itemInExploreDTOMapper;

    @Override
    public EntityMapper resolveEntityMapper(String type) {
        switch (type) {
            case "Detail":
                return artistDetailDTOMapper;
            case "Explore":
                return itemInExploreDTOMapper;
            default:
                return null;
        }
    }
}
