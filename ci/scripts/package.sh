#!/bin/sh

cd resource-addcar

export TERM=${TERM:-dumb}

gradle -Dorg.gradle.native=false build

ls build/libs
cp build/libs/addcar.jar ../resource-jar
cp Dockerfile ../resource-jar

