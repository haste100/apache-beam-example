package com.example.apache.beam.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BeamDemoApplication

fun main(args: Array<String>) {
	runApplication<BeamDemoApplication>(*args)
}
