#!/bin/bash

native-image -cp target/classes/ io.examples.app.hello.HelloWorld helloworld
