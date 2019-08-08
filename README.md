<h2> What's it ?</h2>
基于k8s构建和springcloud架构，构建微服务体系
<h3>架构体系：</h3>
<p4>1.负载和服务注册</p4>
  基于k8s的 service和pod，pod为具体服务实例；service为调用pod入口，通过label选择pod，</br>
并通过kube-proxy实现路由和负载。
<h4>2.服务调用</h4>
  利用springcloud的feign调用服务，服务url使用k8s的service服务地址。该地址为kube-dns</br>
中服务的域名，可通过kube-dns查询到对应的服务ip
<h4>3.服务集群访问入口，路由策略</h4>
  利用ingress 在集群内，侦测服务的变化，并约定路由规则。配置ingress-controller，这里</br>
采用traefik，具体配置可以参考官网。
<h4>4.配置中心</h4>
  采用springcloud config作为配置中心
<h3>如何构建：</h3>
前期准备：
  docker环境搭建,   
  kubeadm环境搭建,   
  traefik-ingres配置   
<h3>一键构建</h3>
  <code>mvn clean package</code></br>
  <code>/bin/bash ./auto.sh</code>
<h3>手动构建</h3>
<h4>1.构建jar包</h4>
  config,order-svc,goods-svc三个服务</br>
<h4>2.构建docker镜像</h4>
  Dockerfile 在doc目录中,无需修改</br>
  将jar和Dockerfile copy至同一文件夹下。分别构建三个服务镜像</br>
  执行命令: <code>docker build -t _yourImageName_ .</code>
<h4>3.发布服务</h4>
  分别构建 config-deployment.yaml,order-deployment.yaml,goods-deployment.yaml 三个服务</br>
  执行命令：<code>kubectl create -f _yourDeployment.yaml_</code>
<h4>4.对外暴露服务，路由</h4>
  构建ingress </br>
  执行命令: <code>kubectl create -f _ingress.order.yaml_</code>
    
    
