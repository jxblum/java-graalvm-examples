#!/bin/bash

native-image -cp target/classes/ io.examples.app.jre.JavaRuntimeDescription jredescribe
