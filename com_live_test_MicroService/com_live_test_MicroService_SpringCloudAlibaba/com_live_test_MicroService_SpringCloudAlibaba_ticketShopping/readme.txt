com_live_test_MicroService_SpringCloudAlibaba_TicketShopping
各个组件整合、流转


流程：用户购票，创建订单 减库存 加积分 通知（短信通知/邮件通知）


com_live_test_MicroService_SpringCloudAlibaba_TicketShopping_Gateway
网关

order：订单服务(controller + service)

stock：库存 (service)

credits：积分(service)

通知notice



订单服务-ticketShopping_order1启动成功...

订单服务-ticketShopping_order2_provider01启动成功...

订单服务-ticketShopping_order2_provider02启动成功...

订单服务-order2启动成功...

	gateway启动成功：8080

http://{{host}}:8080/apiGteway/ticketShopping/order/createOrder
{
	"userId": 1,
	"ticketId": 1,
	"pcs": 2
}

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:订单服务-ticketShopping_order2_provider01,购票成功：userId:1,ticketId:1,pcs:2

响应来自:订单服务-ticketShopping_order1,购票成功：userId:1,ticketId:1,pcs:2
响应来自:ticketShopping_order2_provider02,购票成功：userId:1,ticketId:1,pcs:2




org.springframework.web.client.HttpClientErrorException: 405 null

