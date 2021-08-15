package com.aurora.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component  //加入容器
public class MyLB implements LoadBalancer {

    //新建一个原子整形类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            //如果current是最大值，重新计算，否则加1（防止越界）
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;

            //进行CAS判断，如果不为true，进行自旋
        }while (!this.atomicInteger.compareAndSet(current, next));  //第一个参数是期望值，第二个参数是修改值是
        System.out.println("****第几次访问，次数next：" + next);

        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {   //得到机器的列表
        //非空判断
      /*  if(serviceInstance.size() <= 0){
            return null;
        }*/
        //进行取余
        int index = getAndIncrement() % serviceInstance.size(); //得到服务器的下标位置
        //返回选中的服务实例
        return serviceInstance.get(index);
    }
}
