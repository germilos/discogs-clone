package com.milos.releasemicroservice.service.mapper.factory;

import com.milos.releasemicroservice.service.mapper.EntityMapper;

public interface AbstractMapperFactory
{
	EntityMapper resolveEntityMapper(String type);
}
