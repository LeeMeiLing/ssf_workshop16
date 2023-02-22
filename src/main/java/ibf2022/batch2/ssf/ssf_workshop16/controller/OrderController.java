package ibf2022.batch2.ssf.ssf_workshop16.controller;

import java.io.StringReader;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.ssf.ssf_workshop16.model.Order;
import ibf2022.batch2.ssf.ssf_workshop16.model.OrderResponse;
import ibf2022.batch2.ssf.ssf_workshop16.service.OrderService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


@RestController
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    private Logger logger = Logger.getLogger(OrderController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processOrder(@RequestBody String payload) {

        // read the payload and convert into json object
        JsonReader jsonReader= Json.createReader(new StringReader(payload)); 
        JsonObject json = jsonReader.readObject();

        // convert json object into java object
        Order order = Order.create(json);
        
        // perform business operation: create & save order
        String orderId = orderSvc.createNewOrder(order);

        // construct response
        OrderResponse resp = new OrderResponse();
		resp.setOrderId(orderId);
		resp.setMessage("Order created");

		json = resp.toJson();

		// Send response back to client
		return ResponseEntity.status(201).body(json.toString());

	}

    

}
    
    // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> processOrder(@RequestBody String payload) {

    //     // read the payload and convert into json object
    //     JsonReader jsonReader= Json.createReader(new StringReader(payload)); 
    //     JsonObject json = jsonReader.readObject();

    //     //     {"name":"fred",
    //     //     "email":"fred@gmail.com",
    //     //     "deliveryDate":"2023-02-23",
    //     //     "lineItems":[{"item":"apple","quantity":1},{"item":"orange","quantity":3}]}

    //     String name = json.getString("name");
    //     String email = json.getString("email");
    //     String deliveryDate = json.getString("deliveryDate");
    //     JsonArray array = json.getJsonArray("lineItems");
    //     Map<String,Integer> lineItems = new LinkedHashMap<>();
    //     for(int i = 0; i<array.size(); i++){
    //         json = array.getJsonObject(i);
    //         lineItems.put(json.getString("item"), json.getInt("quantity"));
    //     }
        
    //     logger.log(Level.INFO, "\n >>> JSON email: %s\n".formatted(email));


    //     for (String key : lineItems.keySet()){
    //         logger.log(Level.INFO, "\n >>> JSON lineItem: %s, qty: %d \n".formatted(key,lineItems.get(key)));
    //     }

    //     Random rand = new SecureRandom();
    //     Integer id = rand.nextInt(10000000,100000000);
    //     String orderId = String.valueOf(id);
    //     String message = "Order generated for %s. Delivery date is %s".formatted(name,deliveryDate);

    //     JsonObject returnPayload = Json.createObjectBuilder()
    //         .add("orderId", orderId)
    //         .add("message", message)
    //         .build();

    //     return ResponseEntity.status(201).body(returnPayload.toString());

    // }

// }
