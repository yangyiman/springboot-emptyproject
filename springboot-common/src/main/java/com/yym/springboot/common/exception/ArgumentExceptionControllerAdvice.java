package com.yym.springboot.common.exception;

import com.yym.springboot.common.entity.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这个controller增强只拦截参数校验时抛出的异常
 *
 * 常使用的校验规则如下:
 * @Null，标注的属性值必须为空
 * @NotNull，标注的属性值不能为空
 * @AssertTrue，标注的属性值必须为true
 * @AssertFalse，标注的属性值必须为false
 * @Min，标注的属性值不能小于min中指定的值
 * @Max，标注的属性值不能大于max中指定的值
 * @DecimalMin，小数值，同上
 * @DecimalMax，小数值，同上
 * @Negative，负数
 * @NegativeOrZero，0或者负数
 * @Positive，整数
 * @PositiveOrZero，0或者整数
 * @Size，指定字符串长度，注意是长度，有两个值，min以及max，用于指定最小以及最大长度
 * @Digits，内容必须是数字
 * @Past，时间必须是过去的时间
 * @PastOrPresent，过去或者现在的时间
 * @Future，将来的时间
 * @FutureOrPresent，将来或者现在的时间
 * @Pattern，用于指定一个正则表达式
 * @NotEmpty，字符串内容非空
 * @NotBlank，字符串内容非空且长度大于0
 * @Email，邮箱
 * @Range，用于指定数字，注意是数字的范围，有两个值，min以及max
 *
 */
@RestControllerAdvice
public class ArgumentExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 捕捉对象的属性错误类型
     *
     * 说明:
     * 在对应的entity的实例属性上加入校验规则
     * 在方法对应的参数上加上@Validated或@Valid(它可以使用在实例属性上,实现嵌套校验),如果报错就会进入此方法报错
     *
     * eg: Get test(@Valid @RequestBody User user){}
     * @param ex 错误对象
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String catchException(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException for {}", ex);
        // 每次只取第一个错误返还给用户,不然错误信息太多显的乱
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 用逗号拼接所有的参数错误信息
        /*
        String msg = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
         */
        return ResultModel.fail2Json(400, msg);
    }

    /**
     * 一般的参数绑定时候抛出的异常,区别于@ResponseBody请求体构造对象
     * eg: Get test(@Valid User user){}
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public String handleBindException(BindException ex){
        logger.error("BindException for {}", ex);
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultModel.fail2Json(400, msg);
    }

    /**
     * 单个参数校验
     *
     * 在对应的类上加上@Validated
     * 在需要校验的参数上写上校验规则
     *
     * eg: class Get test(@NotBleak String name){}
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String handleBindGetException(ConstraintViolationException ex){
        logger.error("ConstraintViolationException for {}", ex);
        String msg = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        return ResultModel.fail2Json(400, msg);
    }

}
