package com.example.flyingaround.pages

abstract class Page<T>{
    inline operator fun <R> invoke(block: T.() -> R): R {
        return block.invoke(this as T)
    }
}