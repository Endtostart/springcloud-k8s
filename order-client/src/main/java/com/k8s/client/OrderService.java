package com.k8s.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="order",url = OrderService.SERVICE_URL)
public interface OrderService {

    String SERVICE_URL = "http://order-svc.default.svc.cluster.local:8080";

    @GetMapping("/order/callBack")
    @ResponseBody
    String callBack(@RequestParam("message") String message);
}
