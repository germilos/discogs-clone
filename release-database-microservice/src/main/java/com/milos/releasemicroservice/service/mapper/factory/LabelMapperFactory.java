package com.milos.releasemicroservice.service.mapper.factory;

import com.milos.releasemicroservice.service.mapper.EntityMapper;
import com.milos.releasemicroservice.service.mapper.ItemInExploreDTOMapper;
import com.milos.releasemicroservice.service.mapper.LabelDetailDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("labelMapperFactory")
public class LabelMapperFactory implements AbstractMapperFactory
{

	@Autowired
	private LabelDetailDTOMapper labelDetailDTOMapper;
	@Autowired
	private ItemInExploreDTOMapper itemInExploreDTOMapper;

	@Override
	public EntityMapper resolveEntityMapper(String type)
	{
		switch (type)
		{
		case "Detail":
			return labelDetailDTOMapper;
		case "Explore":
			return itemInExploreDTOMapper;
		default:
			return null;
		}
	}
}
