package com.lucasmarciano.remote

import com.lucasmarciano.data.model.ProjectEntity
import com.lucasmarciano.remote.mapper.ProjectsResponseModelMapper
import com.lucasmarciano.remote.model.ProjectModel
import com.lucasmarciano.remote.model.ProjectsResponseModel
import com.lucasmarciano.remote.service.GithubTrendingService
import com.lucasmarciano.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallServer() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingServiceSearchRepositories(observer: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observer)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}