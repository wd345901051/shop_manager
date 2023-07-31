package cn.tedu.csmall.product.schedule;

import cn.tedu.csmall.product.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 更新缓存的计划任务
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Component
public class CacheSchedule {

    @Autowired
    private IBrandService brandService;

    public CacheSchedule() {
        log.debug("创建计划任务对象：CacheSchedule");
    }

    // 关于@Scheduled常用属性
    // >> fixedRate：每间隔多少毫秒执行一次
    // >> fixedDelay：每延迟多少毫秒执行一次
    // >> cron：使用1个字符串，其中写6-7个值，各值之间使用空格进行分隔
    // >> >> 这6-7个值分别表示：秒 分 时 日 月 周 [年]
    // >> >> 例如：cron = "56 34 12 20 1 ? 2230"，表示“2230年1月20日12:34:56执行，无论当天是星期几”
    // >> >> 以上各个位置，均可以使用星号，表示任意值，在“日”和“周”上，还可以使用问号，表示不关心具体值
    // >> >> 以上各个位置，还可以使用"x/x"格式的值，例如，在"分钟"位置使用 1/5，表示分钟值为1时执行，且每5分钟执行1次
    @Scheduled(fixedRate = 3 * 60 * 1000)
    public void updateCache() {
        log.debug("执行了CacheSchedule的计划任务……");
        log.debug("加载品牌数据到缓存……");
        brandService.loadBrandToCache();
    }

}
