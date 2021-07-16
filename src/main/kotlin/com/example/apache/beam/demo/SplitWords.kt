package com.example.apache.beam.demo

import org.apache.beam.sdk.transforms.PTransform
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.values.PCollection


class SplitWords : PTransform<PCollection<String>, PCollection<String?>>() {

    override fun expand(line: PCollection<String>): PCollection<String?> {

        // Convert line of text into individual lines
        return line.apply(ParDo.of(SplitWordsFn()))
    }
}