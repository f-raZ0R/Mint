#!/bin/bash
MOD_JAR=../.gradle/loom-cache/remapped_mods/net_fabricmc_yarn_1_20_1_1_20_1_build_9_v2/$1

cd jars
DIRNAME=$(echo $MOD_JAR | rev | cut -d "/" -f1 | cut -d "." -f2- | rev)
mkdir $DIRNAME
cd $DIRNAME
jar xf ../../$MOD_JAR
cd ../../