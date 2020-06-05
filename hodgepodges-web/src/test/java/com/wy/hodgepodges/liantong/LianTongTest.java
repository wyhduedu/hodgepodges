package com.wy.hodgepodges.liantong;

import com.wy.hodgepodges.common.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/29 2:45 下午
 */
@Slf4j
public class LianTongTest  /*extends WebApplicationTests */{

//    @Autowired
//    MailService mailService;

    final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    final static ExecutorService executorService = ThreadUtils.getExecutorService();

    /**
     * Long转16进制度
     * @param num
     * @return
     */
    public static String toHexTest(long num) {
        int mag = Long.SIZE - Long.numberOfLeadingZeros(num);
        int chars = Math.max(((mag + (4 - 1)) / 4), 1);
        char[] buf = new char[chars];
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[0 + --chars] = digits[((int) num) & mask];
            num >>>= 4;
        } while (num != 0 && chars > 0);
        return new String(buf);
    }

//    @Test
//    public void sendEmail() {
//        executorService.submit(() -> {
//            mailService.send(new String[]{"123@qq.com"}, "验证码", "000100");
//        });
//    }



}
