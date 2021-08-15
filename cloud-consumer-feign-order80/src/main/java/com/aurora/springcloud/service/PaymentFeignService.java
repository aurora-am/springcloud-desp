package com.aurora.springcloud.service;

import com.aurora.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign封装了Ribbon和RestTemplate，实现负载均衡和发送请求
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")       ///作为feign的接口，找CLOUD-PAYMENT-SERVICE服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")        //8001的方法 能找到8001调用的地址
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();

}