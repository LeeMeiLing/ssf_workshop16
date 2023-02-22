package ibf2022.batch2.ssf.ssf_workshop16.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.ssf_workshop16.model.Order;

@Repository
public class OrderRepo {
    
    @Autowired //@Qualifier("my-redis")
	private RedisTemplate<String, Object> template;

	public void insertOrder(Order order) {

        template.opsForValue().set(order.getOrderId(), order);

	}

    public void insertOrder2(Order order) {

        template.opsForHash().put("allOrder", order.getOrderId(), order);

	}

    // @Autowired
	// RedisTemplate<String, Object> redisTemplate; //<String, String> vs <String, Object> ;;; template vs redisTemplate

	// public void insertOrder(Order order) {

    //     redisTemplate.opsForValue().set(order.getOrderId(), order.toString());

	// }
    
}
