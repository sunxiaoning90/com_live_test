com_live_test_MicroService_SpringCloudAlibaba_ticketShopping_order
订单服务

1、服务名
spring.application.name=ticketShopping-order

2、controller访问Service 的方式： 直接访问本地Service
public class OrderController {

	@Autowired
	OrderService orderService;

	/**
	 * 请求： http://{{host}}:8081/order/createOrder
	 * 
	 * { "userId": 1, "ticketId": 1, "pcs": 2 }
	 * 
	 * @return
	 */
//	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	@PostMapping(value = "/createOrder")
	public String createOrder(@RequestBody Map<String, String> map) {

//		String userId = map.get("userId");
//		String ticketId = map.get("ticketId");
//		String pcs = map.get("pcs");
//
//		String r = new StringBuffer("响应来自:" + App.APP_NAME_ALIAS + ",购票成功：").append("userId:").append(userId)
//				.append(",ticketId:").append(ticketId).append(",pcs:").append(pcs).toString();
//		System.out.println(r);
//		return r;

		// controller 直接访问本地Service
		return orderService.createOrder(map);
	}