#!/bin/bash

native-image --language:js --initialize-at-build-time -cp target/classes/ io.examples.app.js.json.PrettyPrintJSON printjson
