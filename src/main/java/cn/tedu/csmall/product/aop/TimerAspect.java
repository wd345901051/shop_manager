package cn.tedu.csmall.product.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimerAspect {

    // 【切面中的方法】
    // 访问权限：public
    // 返回值类型：当使用@Around时，使用Object类型
    // 方法名称：自定义
    // 参数列表：当使用@Around时，添加ProceedingJoinPoint类型的参数
    // 异常：当使用@Around时，抛出Throwable

    // @Before：在……之前
    // @After：在……之后
    // @AfterReturning：在返回之后
    // @AfterThrowing：在抛出异常之后
    // @Around：环绕

    // 关于以上注解，大致是：
    // @Before
    // try {
    //   pjp.proceed();
    //   @AfterReturning
    // } catch (Throwable e) {
    //   @AfterThrowing
    //   throw e;
    // } finally {
    //   @After
    // }

    // 注解中的表达式用于匹配某些方法，在表达式中，应该表示出“返回值类型 包.类.方法(参数列表)”
    // 以表达式中，可以使用星号(*)和2个连续的小数点(..)作为通配符
    // 其中，星号可以用于：返回值类型、包名、类名、方法名、参数
    // 而2个连续的小数点可以用于：包、参数
    // 星号只表示匹配1次，而2个连续的小数点表示匹配0~n次

    // 连接点：JoinPoint，表现为切面需要处理的某个方法，或其它的某种行为
    // 切入点：Point Cut，写在@Around等注解中的表达式
    // 通知：Advice，即@Around等注解及对应的代码
    // 切面：Aspect，囊括了切入点和通知的模块

    @Around("execution(* cn.tedu.csmall.product.service.impl.*.*(..))")
    public Object timer(ProceedingJoinPoint pjp) throws Throwable {
        // 【需求】统计每个Service方法的耗时
        log.debug("在某个Service的某个方法之前执行了……");

        long start = System.currentTimeMillis();

        // 执行（处理）连接点，即执行业务方法
        // 注意：必须获取调用proceed()方法的返回值
        // 注意：如果连接点是Service中的方法，调用proceed()时的异常必须声明抛出，不可以try...catch
        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        log.debug("当前切面匹配到的组件类：{}", pjp.getTarget());
        log.debug("当前切面匹配到的方法：{}", pjp.getSignature());
        log.debug("当前切面匹配到的方法的参数列表：{}", pjp.getArgs());
        log.debug("执行耗时：{}毫秒", end - start);

        // 注意：必须返回调用proceed()方法的结果
        return result;
    }

}
