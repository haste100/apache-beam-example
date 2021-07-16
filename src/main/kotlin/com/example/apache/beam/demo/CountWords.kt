package com.example.apache.beam.demo

import org.apache.beam.sdk.transforms.Count
import org.apache.beam.sdk.transforms.PTransform
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.values.KV
import org.apache.beam.sdk.values.PCollection


class CountWords :
    PTransform<PCollection<String?>, PCollection<KV<String, Long>>>() {
    override fun expand(lines: PCollection<String?>): PCollection<KV<String, Long>> {
        return lines
            .apply(
                ParDo.of<String, String>(ExtractWordsFn())  // Convert text into individual words
            )
            .apply(Count.perElement())  // Count the words
    }
}