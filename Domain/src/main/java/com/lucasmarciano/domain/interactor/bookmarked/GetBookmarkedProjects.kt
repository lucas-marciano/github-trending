package com.lucasmarciano.domain.interactor.bookmarked

import com.lucasmarciano.domain.executor.PostExecutionThread
import com.lucasmarciano.domain.interactor.ObservableUseCase
import com.lucasmarciano.domain.module.Project
import com.lucasmarciano.domain.repository.ProjectsRepository
import javax.inject.Inject

open class GetBookmarkedProjects@Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?)
            = projectsRepository.getBookmarkedProjects()

}