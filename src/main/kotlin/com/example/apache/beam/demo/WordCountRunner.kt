package com.example.apache.beam.demo

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.transforms.MapElements
import org.apache.beam.sdk.transforms.SerializableFunction
import org.apache.beam.sdk.values.KV
import org.apache.beam.sdk.values.TypeDescriptors
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class WordCountRunner : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val options = PipelineOptionsFactory.fromArgs(*args).withoutStrictParsing().`as`(
            WordCountOptions::class.java
        )
        runWordCount(options)
    }

    companion object {
        @Throws(InterruptedException::class)
        fun runWordCount(options: WordCountOptions) {
            val p = Pipeline.create(options)
            p.apply("Reading Text", TextIO.read().from(options.inputFile))
                .apply(SplitWords())
                .apply(CountWords())
                .apply("FormatResults", MapElements
                    .into(TypeDescriptors.strings())
                    .via(SerializableFunction { wordCount: KV<String, Long> ->
                        wordCount.key.toString() + ": " + wordCount.value
                    })
                )
                .apply("WriteCounts", TextIO.write().to(options.outputFile))
            p.run().waitUntilFinish()
        }
    }
}