#/bin/bash
modules=('mundo-root' 'mundo-core' 'mundo-web' 'mundo-data')

for module in ${modules[@]}
do
    echo "--------------------------< start install $module >--------------------------"
    mvn clean install -Dmaven.test.skip=true -f $module/pom.xml -P aliyun
    echo "---------------------------< end install $module >---------------------------"
done
