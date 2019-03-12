package com.lucasmarciano.domain.repository

import com.lucasmarciano.domain.module.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsRepository {
    fun getProjects(): Observable<List<Project>>

    fun getBookmarkedProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable
}