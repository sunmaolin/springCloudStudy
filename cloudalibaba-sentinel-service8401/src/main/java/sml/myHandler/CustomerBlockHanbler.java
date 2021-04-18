package sml.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import sml.entities.CommonResult;

public class CustomerBlockHanbler {

    public static CommonResult handleException(BlockException blockException) {
        return new CommonResult(444, "自定义1");
    }

    public static CommonResult handleException2(BlockException blockException) {
        return new CommonResult(444, "自定义2");
    }
}
