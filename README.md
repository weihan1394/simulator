### Simulator game

# Run Project

```
# build and run project
sh run.sh

# build project without scan and test
mvn -Dmaven.test.skip=true -Ddependency-check.skip=true  install

# build project with scan and test
mvn clean install

# run test
mvn clean test

# run project
mvn exec:java -Dexec.mainClass="tehpeng.simulator.SimulatorApplication"
# run docker image
docker run -it simulator:latest

# debug docker image
docker build . -t simulator:latest
docker run -it  simulator:latest /bin/sh
```
