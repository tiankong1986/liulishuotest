set curdir=%~dp0
@echo %curdir%
cd %curdir%
cd ../coins-txn-core
call mvn -DM3_REP_HOME=E:\.m3\coins.txn.core.1.1.0 -f pom.xml clean install
call mvn -DM3_REP_HOME=E:\.m3\coins.txn.core.1.1.0 -f pom.xml source:jar deploy

cd ../coins-txn-api-ws
call mvn -DM3_REP_HOME=E:\.m3\coins.txn.core.1.1.0 -f pom.xml clean package
pause