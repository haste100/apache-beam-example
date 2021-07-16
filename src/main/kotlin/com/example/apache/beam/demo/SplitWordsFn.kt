package com.example.apache.beam.demo

import org.apache.beam.sdk.transforms.DoFn


class SplitWordsFn : DoFn<String?, String?>() {

    companion object {
        const val SPLIT_PATTERN = ":"
    }

    @ProcessElement
    fun processElement(c: ProcessContext) {
        c.element()?.split(SPLIT_PATTERN.toRegex())?.toTypedArray()!!
            .filter { it.isNotEmpty() }
            .forEach { word -> c.output(word) }
    }
}