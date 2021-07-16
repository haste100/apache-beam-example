package com.example.apache.beam.demo

import org.apache.beam.sdk.options.Default
import org.apache.beam.sdk.options.Description
import org.apache.beam.sdk.options.PipelineOptions


interface WordCountOptions : PipelineOptions {
    @get:Default.String("./src/main/resources/input.txt")
    @get:Description("Path to the input file")
    var inputFile: String?

    @get:Default.String("./src/main/resources/output.txt")
    @get:Description("Path to the output file")
    var outputFile: String?
}