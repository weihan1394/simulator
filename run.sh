mvn -Dmaven.test.skip=true -Ddependency-check.skip=true  install
mvn exec:java -Dexec.mainClass="tehpeng.simulator.SimulatorApplication"