live:
1、服务注册 http：//服务名/具体请求方法

live:
2、nocas 默认集成了ribbon（负载均衡，使用@LoadBance即可）

live:
3、openfine：类似于dubbo，像调用本地方法一样调用远程服务


feign原理：代理.
ribbon原理：拦截器:为RestTemplate增加了@LoanBalanced 注解后，实际上通过配置，为RestTemplate注入负载均衡拦截器，让负载均衡器选择根据其对应的策略选择合适的服务后，再发送请求。

live:
限流http://21349ee4.wiz03.com/wapp/pages/view/share/s/0xd9XA3_U4q92z2wFv30iNbH0C1ojY35EQXD2s0-Hn2VjzR_

live:
sata：golableTruncation

live:
！im 网关：前台

live:
copyonwrite：写时复制