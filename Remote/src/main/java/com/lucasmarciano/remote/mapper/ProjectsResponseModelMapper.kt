package com.lucasmarciano.remote.mapper

import com.lucasmarciano.data.model.ProjectEntity
import com.lucasmarciano.remote.model.ProjectModel

open class ProjectsResponseModelMapper : ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
            model.id,
            model.name,
            model.fullName,
            model.starCount.toString(),
            model.dateCreated,
            model.owner.ownerName,
            model.owner.ownerAvatar
        )
    }

}