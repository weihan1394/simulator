### Simulator game

# Run Project

```
# build project
mvn clean install

# run test
mvn clean test

# run project
mvn exec:java -Dexec.mainClass="tehpeng.simulator.SimulatorApplication"

# debug docker image
docker build . -t simulator:latest
docker run -it  simulator:latest /bin/sh

# run docker image
docker run -it simulator:latest
```
