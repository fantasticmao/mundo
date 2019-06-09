#/bin/bash
echo "[INFO] ------------------------< start install >-------------------------"
mvn clean install -Dmaven.test.skip=true -fpom.xml -P aliyun
echo "[INFO] ------------------------< end install >-------------------------"