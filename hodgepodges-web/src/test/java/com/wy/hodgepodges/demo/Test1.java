package com.wy.hodgepodges.demo;

import com.mchange.v1.util.Sublist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-03 09:55
 */

@SpringBootTest
@Slf4j
public class Test1 {

    private static List<List<Integer>> data = new ArrayList<>();



    public static void main(String[] args) {
//        for (int i = 0; i < 1000; i++) {
//            List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
//            data.add(rawList.subList(0, 1));
//        }
        List<Integer> integers =new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        List<Integer> subList = integers.subList(0,2);
        subList.set(0,10);
        integers.set(1,20);
        System.out.println(integers);
        System.out.println(subList);
        Sublist sublist = new Sublist();

    }

}
