#!/bin/bash

mvn clean install -DskipTests;
sshpass -perksystem.123 scp -P 10022 /Users/heokangmoo/dev/Java/aispace/erksystem/target/erksystem.jar  erksystem@129.254.221.95:~/erksystem/lib
