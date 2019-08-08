package com.k8s.api;

import com.k8s.client.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("goods")
public class GoodsService {

    static String host;
    @Value("${goods.profile}")
    String profile;

    @Autowired
    private OrderService orderService;

    @GetMapping("use")
    public String use(@RequestParam("message") String message) {
        String msg = message + "\n商品服务 [" + profile + "]：==》use ok\n>>ip" + host;
        return orderService.callBack(msg);
    }

    static {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        host =  address.getHostAddress();
    }
}
