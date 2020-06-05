package com.wy.hodgepodges.common.design.strategy;

import java.util.Scanner;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-17 13:44
 */

public class Client {

    public static void main(String[] args) {
        Context context;

        context = new Context(new ConcreteStrategyA());
        context.contextInterface();

        context = new Context(new ConcreteStrategyB());
        context.contextInterface();

        context = new Context(new ConcreteStrategyC());
        context.contextInterface();


    }


    public static void storategy(String[] args) {
        Context cashContext = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入打折方式（1/2/3）：");
        int in = scanner.nextInt();
        String type = "";

        switch (in) {
            case 1:
                cashContext = new Context(new ConcreteStrategyA());
                type += "正常收费";
                break;

            case 2:
                cashContext = new Context(new ConcreteStrategyA( ));
                type += "满300返100";
                break;

            case 3:
                cashContext = new  Context(new ConcreteStrategyA( ));
                type += "打8折";
                break;

            default:
                System.out.println("请输入1/2/3");
                break;
        }

        double totalPrices = 0;

        System.out.print("请输入单价：");
        double price = scanner.nextDouble();
        System.out.print("请输入数量：");
        double num = scanner.nextDouble();
        totalPrices = cashContext.getResult(price * num);

        System.out.println("单价：" + price + "，数量：" + num + "，类型：" + type + "，合计：" + totalPrices);

        scanner.close();
    }

}



