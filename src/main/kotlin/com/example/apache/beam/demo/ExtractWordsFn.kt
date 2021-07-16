package com.example.apache.beam.demo

import org.apache.beam.sdk.transforms.DoFn


class ExtractWordsFn : DoFn<String?, String?>() {

    companion object {
        const val TOKENIZER_PATTERN = "[^\\p{L}]+"
    }

    @ProcessElement
    fun processElement(c: ProcessContext) {
        c.element()?.split(TOKENIZER_PATTERN.toRegex())?.toTypedArray()!!
            .filter { it.isNotEmpty() }
            .forEach { word -> c.output(word) }
    }

}