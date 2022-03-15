package com.miwas.tapmobiletestapp.core

interface BaseViewModel<T> {

    fun startProcesses()

    fun dispatchEvent(event: T)

    fun finishProcesses()
}