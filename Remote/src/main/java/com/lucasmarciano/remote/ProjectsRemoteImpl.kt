package com.lucasmarciano.remote

import com.lucasmarciano.data.model.ProjectEntity
import com.lucasmarciano.data.repository.ProjectsRemote
import com.lucasmarciano.remote.mapper.ProjectsResponseModelMapper
import com.lucasmarciano.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
) : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}