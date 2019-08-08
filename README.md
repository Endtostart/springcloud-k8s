**这是有个什么项目？**
基于k8s构建和springcloud架构，构建微服务体系
架构体系：
1.负载和服务注册
    基于k8s的 service和pod，pod为具体服务实例；service为调用pod入口，通过label选择pod，
    并通过kube-proxy实现路由和负载。
2.服务调用
    利用springcloud的feign调用服务，服务url使用k8s的service服务地址。该地址为kube-dns
    中服务的域名，可通过kube-dns查询到对应的服务ip
3.服务集群访问入口，路由策略
    利用ingress 在集群内，侦测服务的变化，并约定路由规则。配置ingress-controller，这里
    采用traefik，具体配置可以参考官网。
4.配置中心
    采用springcloud config作为配置中心

如何构建：
前期准备：docker环境搭建，kubeadm环境搭建，traefik-ingres配置
1.构建jar包
    config,order-svc,goods-svc三个服务
2.构建docker镜像
    Dockerfile 在doc目录中,无需修改
    将jar和Dockerfile copy至同一文件夹下。分别构建三个服务镜像
    执行命令: docker build -t _yourImageName_ .
3.发布服务
    分别构建 config-deployment.yaml,order-deployment.yaml,goods-deployment.yaml 三个服务
    执行命令：kubectl create -f _yourDeployment.yaml_
4.对外暴露服务，路由
    构建ingress 
    执行命令: kubectl create -f _ingress.order.yaml_
    
    