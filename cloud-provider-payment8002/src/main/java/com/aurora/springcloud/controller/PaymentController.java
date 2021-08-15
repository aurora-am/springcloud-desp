package com.aurora.springcloud.controller;

import com.aurora.springcloud.entities.CommonResult;
import com.aurora.springcloud.entities.Payment;
import com.aurora.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j  //日志
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;	//springframework的DiscoveryClient（不要导错包了）

    @Value("${server.port}")        //读取到 application.yml中的server.port
    private String serverPort;

    //前后端分离，所以不能直接返回对象，数据要先经过CommonResult封装再返回
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
//        log.info("******插入的数据为：" + payment);
//        log.info("******插入结果：" + result);
        if(result > 0){
            //插入成功
            return new CommonResult(200, "插入数据库成功,serverPort"+serverPort, result);
        }else{
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            //查询成功
            return new CommonResult(200, "查询成功,serverPort"+serverPort, payment);
        }else{
            return new CommonResult(444, "没有对应记录，查询ID：" + id);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取服务列表的信息
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*******element：" + element);
        }

        //获取CLOUD-PAYMENT-SERVICE服务的所有具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            //getServiceId服务器id getHost主机名称 getPort端口号  getUri地址
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
