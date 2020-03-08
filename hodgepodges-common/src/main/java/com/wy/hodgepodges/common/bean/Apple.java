package com.wy.hodgepodges.common.bean;

import com.wy.hodgepodges.common.annotation.FruitColor;
import com.wy.hodgepodges.common.annotation.FruitName;
import com.wy.hodgepodges.common.annotation.FruitProvider;
import lombok.Data;


/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-31 15:48
 */
@Data
public class Apple  {

    @FruitName(name=  "Apple")
    private String appleName;

    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id=1,name="陕西红富士集团",address="陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
}
