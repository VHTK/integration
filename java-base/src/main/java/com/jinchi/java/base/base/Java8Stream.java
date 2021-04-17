package com.jinchi.java.base.base;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 测试使用stream中List Map之间的转换
 */
public class Java8Stream {

    private static Set<String> zhyUrl = new LinkedHashSet<String>() {
        {
            add("POST@/treatment/zhy/portal/{orgId}/device/data");
            add("GET@/user/zhy/portal/{orgId}/check");
            add("POST@/user/zhy/portal/{orgId}/warn");
        }
    };

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("开始技术");
        UserRole userRole = new UserRole();
        userRole.setCode("243243");
        userRole.setId(1);
        userRole.setUserCode("32432");
        userRole.setRoleCode("23432432");
        stopWatch.start("分组");

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        //获取ID列表
        List<Integer> ids = userRoles.stream().map(UserRole::getId).collect(Collectors.toList());
        System.out.println("id size: "+ids.size());
        List<Integer> ids1 = userRoles.stream().map(UserRole::getId).distinct().collect(Collectors.toList());
        System.out.println("ids1 size: "+ids1.size());

        //根据ID分组   一对一
        Map<Integer, UserRole> idAndUserRoleMap = userRoles.stream().collect(Collectors.toMap(UserRole::getId, Function.identity()));

        //根据RoleCode分组   一对多   一个角色对应多个用户
        Map<String, List<UserRole>> roleAndUserMap = userRoles.stream().collect(Collectors.groupingBy(UserRole::getRoleCode));
        stopWatch.stop();

        stopWatch.start("比较");
        Integer s = 3;
        Integer b = 4;
        Integer c = 2;
        Integer f = 3;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s.compareTo(b));
        System.out.println(s.compareTo(c));
        System.out.println(s.compareTo(f));

        stopWatch.stop();
        stopWatch.start("匹配");
        String reg = "(^1\\d{10}$)|(^(\\d{3,4})-(\\d{7,8})$)";

        System.out.println("15248523546".matches(reg));
        System.out.println("2531-15824355".matches(reg));
        System.out.println("432-1582455".matches(reg));
        System.out.println("253-15824455".matches(reg));
        System.out.println("2531-1588455".matches(reg));

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        LocalDate now = LocalDate.of(2019,10,15);
        LocalDate next = LocalDate.of(2019,10,8);
        System.out.println(next.until(now, ChronoUnit.DAYS));
        System.out.println(now.until(next, ChronoUnit.DAYS));

        try {
            System.out.println(5/0);
        } catch (Exception e) {
            System.out.println("Exception message " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
        System.out.println("try catch");

        List<UserRole> logs = new ArrayList<>();
        UserRole l1 = new UserRole();
        l1.setBeginDate(LocalDate.of(2018, 10, 5));

        UserRole l2 = new UserRole();
        l2.setBeginDate(LocalDate.of(2019, 1, 5));

        UserRole l3 = new UserRole();
        l3.setBeginDate(LocalDate.of(2019, 11, 5));

        logs.add(l2);
        logs.add(l1);
        logs.add(l3);

        logs.sort(Comparator.comparing(UserRole::getBeginDate).reversed());

        logs.forEach(l -> System.out.println(ofPattern("yyyy-MM-dd").format(l.getBeginDate())));
    }
}
