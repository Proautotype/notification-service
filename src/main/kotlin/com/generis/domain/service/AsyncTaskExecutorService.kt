package com.generis.domain.service

import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@ApplicationScoped
class AsyncTaskExecutorService {

    private val executor: ThreadPoolExecutor = ThreadPoolExecutor(
        10, // core pool size
        10, // max pool size
        60L,
        TimeUnit.SECONDS,
        LinkedBlockingDeque(100), // max 100 queued tasks
        Executors.defaultThreadFactory(),
        RejectedExecutionHandler { _, _ -> {} }
    )

    fun submit(task: () -> Unit) {
        executor.submit(task)
    }

    fun getStatus(): ExecutorStatus {
        return ExecutorStatus(
            activeThreads = executor.activeCount,
            queuedTasks = executor.queue.size,
            completedTasks = executor.completedTaskCount,
            totalTasksSubmitted = executor.taskCount
        )
    }

    data class ExecutorStatus(
        val activeThreads: Int,
        val queuedTasks: Int,
        val completedTasks: Long,
        val totalTasksSubmitted: Long
    )

}