package com.hi.main.th

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author wbxuzhen
 * date : 2021/7/27 11:16
 * description : 线程池
 */
class MyThreadPool private constructor() {
    private var threadMap = hashMapOf<String, ThreadPoolExecutor>()

    /**
     * 静态内部类单例
     */
    companion object {
        fun getInstance() = SingleHolder.SINGLE_HOLDER
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()//cpu数量
        private val CPU_POOL_SIZE = CPU_COUNT + 1 //核心线程
        private val MAX_POOL_SIZE = CPU_COUNT * 2 + 1 //最大线程为cpu+1
        private const val KEEP_ALIVE_TIME = 3L //线程活跃时间 单位：秒  超时线程回收
        private const val QUEUE_SIZE = 128 //等待队列大小
    }

    private object SingleHolder {
        val SINGLE_HOLDER = MyThreadPool()
    }

    private fun getThreadPool(tag: String): ThreadPoolExecutor {
        var threadPoolExecutor = threadMap[tag]
        if (threadPoolExecutor == null) {
            threadPoolExecutor = ThreadPoolExecutor(
                CPU_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                ArrayBlockingQueue(QUEUE_SIZE),
                Executors.defaultThreadFactory(),
                RejectedExecutionHandler { _, _ ->

                }
            )
        }
        threadPoolExecutor.allowCoreThreadTimeOut(true)
        threadMap[tag] = threadPoolExecutor
        return threadPoolExecutor
    }

    fun addTask(tag: String, runnable: Runnable) {
        getThreadPool(tag).execute(runnable)
    }

    fun remove(tag: String, runnable: Runnable) {
        getThreadPool(tag).queue.remove(runnable)
    }

    fun exitThreadPool(tag: String) {
        val threadPoolExecutor = threadMap[tag]
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow()
            threadMap.remove(tag)
        }
    }
}