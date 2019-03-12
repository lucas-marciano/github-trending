package com.lucasmarciano.data.repository

import com.lucasmarciano.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}