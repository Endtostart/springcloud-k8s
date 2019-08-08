#!/bin/bash
dirs=('config' 'order-svc' 'goods-svc')
echo "创建工作目录 workdir"
mkdir workdir
cd workdir
cp ../doc/Dockerfile ./
for var in ${dirs[@]}
do
	echo "构建"$var"镜像"
	dir="../"$var"/target/"
	cp $dir/*.jar ./
	docker build -t "ningxue/"$var .
	echo "构建"$var"镜像完成"
        rm ./*jar
done
cd ../
rm -rf workdir
echo "构建镜像完成..."
echo "发布k8s服务..."
echo "发布config服务"
kubectl create -f ./doc/config-deployment.yaml
echo "发布order服务"
kubectl create -f ./doc/order-deployment.yaml
echo "发布goods服务"
kubectl create -f ./doc/goods-deployment.yaml
echo ”发布k8s服务结束...“
echo "构建路由规则ingress..."
kubectl create -f ./doc/ingress-order.yaml
echo "构建路由规则结束..."
echo "SUCCESS"


