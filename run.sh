#!/usr/bin/env bash

if [ -z "$1" ]; then
    echo "Usage: $0 <file.json>"
    exit 1
fi

JSONFILE=$1
./gradlew execute --args=$JSONFILE
