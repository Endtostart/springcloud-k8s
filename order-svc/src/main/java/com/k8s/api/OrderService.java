package com.k8s.api;

import com.k8s.client.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("order")
public class OrderService {
    static String host;
    @Value("${order.profile}")
    String profile;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("pay")
    public String pay() {
        String message = "\n订单服务 ["+profile+"]：==》pay ok\n>>ip" + host;
        return goodsService.use(message);
    }

    @GetMapping("callBack")
    public String callBack(@RequestParam("message") String message) {
        return message + "\n订单服务 [" + profile + "]：==》callBack ok\n>>ip" + host;
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
