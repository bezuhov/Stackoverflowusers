package com.android.example.stackoverflowwork.domain.mapper

interface Mapper <Entity, DomainModel>{

    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity

}