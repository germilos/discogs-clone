package com.milos.releasemicroservice.service.mapper.factory;

import com.milos.releasemicroservice.service.mapper.*;
import org.springframework.stereotype.Component;

@Component("releaseMapperFactory")
public class ReleaseMapperFactory implements AbstractMapperFactory
{

	private final ReleaseDetailDTOMapper releaseDetailDTOMapper;
	private final ItemInExploreDTOMapper itemInExploreDTOMapper;
	private final ReleaseSaveDTOMapper releaseSaveDTOMapper;
	private final ReleaseInItemDTOMapper releaseInItemDTOMapper;

	public ReleaseMapperFactory(final ReleaseDetailDTOMapper releaseDetailDTOMapper,
			final ItemInExploreDTOMapper itemInExploreDTOMapper, final ReleaseSaveDTOMapper releaseSaveDTOMapper,
			final ReleaseInItemDTOMapper releaseInItemDTOMapper)
	{
		this.releaseDetailDTOMapper = releaseDetailDTOMapper;
		this.itemInExploreDTOMapper = itemInExploreDTOMapper;
		this.releaseSaveDTOMapper = releaseSaveDTOMapper;
		this.releaseInItemDTOMapper = releaseInItemDTOMapper;
	}

	@Override
	public EntityMapper resolveEntityMapper(String type)
	{
		switch (type)
		{
		case "Detail":
			return releaseDetailDTOMapper;
		case "Explore":
			return itemInExploreDTOMapper;
		case "Save":
			return releaseSaveDTOMapper;
		case "InItem":
			return releaseInItemDTOMapper;
		default:
			return null;
		}
	}
}
