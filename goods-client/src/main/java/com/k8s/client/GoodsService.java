package com.k8s.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "goods",url = GoodsService.SERVICE_URL)
public interface GoodsService {

    String SERVICE_URL = "http://goods-svc.default.svc.cluster.local";

    @GetMapping("/goods/use")
    @ResponseBody
    String use(@RequestParam("message") String message);
}
