package com.yym.springboot.security.filter;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * 权限过滤器
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    private static String FILTER_APPLIED = "__spring_security_MyFilterSecurityInterceptor_filterApplied";

    @Autowired
    private AccessDecisionManager accessDecisionManager;
    @Autowired
    private SecurityMetadataSource securityMetadataSource;

    /**
     * 初始化时将定义的DecisionManager,注入到父类AbstractSecurityInterceptor中
     * 注意：
     *
     * @PostConstruct 用于在依赖关系注入完成之后需要执行的方法，以执行任何初始化。
     * 此方法必须在将类放入服务之前调用，且只执行一次。
     */
    @PostConstruct
    public void init() {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    /**
     * 向父类提供要处理的安全对象类型，因为父亲被调用的方法参数类型大多是Object，框架需要保证传递进去的安全对象类型相同
     *
     * @return 子类为其提供服务的安全对象的类型
     */
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    /**
     * 获取到自定义MetadataSource的方法
     *
     * 启动时会有3次调用
     * 第一次调用：{@link AbstractSecurityInterceptor#afterPropertiesSet()} 135行
     * 第二次调用：{@link AbstractSecurityInterceptor#afterPropertiesSet()} 137行
     * 第三次调用：{@link AbstractSecurityInterceptor#afterPropertiesSet()} 156行
     *
     * 登录时调用
     * 调用：{@link AbstractSecurityInterceptor#beforeInvocation(Object)} 196行
     *
     * @return  权限资源映射的数据源
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    /**
     * 每当request/response对由于客户端请求链末端的资源而通过链时，容器调用过滤器的doFilter方法。
     * 传入此方法的filter chain 允许Filter传递请求并响应链中的下一个实体。
     *
     * 此方法的典型实现将遵循以下模式：
     * 1.检查请求
     * 2.也可以使用自定义实现包装请求对象，输入filter的内容或头
     * 3.（可选）使用自定义实现包装响应对象，以Filter 内容或头进行输出过滤
     * 4.使用FilterChain对象的chain.doFilter()调用链中的下一个实体
     * 5.在调用FilterChain中的下一个实体后，直接在响应上设置头。
     * @param request  要处理的请求
     * @param response 与请求关联的响应
     * @param chain   提供对链中下一个Filter的访问，以便此Filter将请求和响应传递给以进行进一步处理
     * @throws IOException      如果在此筛选器处理请求期间发生I/O错误
     * @throws ServletException 如果由于其他原因处理失败
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        if ((fi.getRequest() != null) && (fi.getRequest().getAttribute(FILTER_APPLIED) != null)) {
            // filter already applied to this request and user wants us to observe
            // once-per-request handling, so don't re-do security checking
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }
        else {
            // first time this request being called, so perform security checking
            if (fi.getRequest() != null ) {
                fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
            }

            /* 调用父类的beforeInvocation ==> accessDecisionManager.decide(..) */
            InterceptorStatusToken token = super.beforeInvocation(fi);

            try {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            }
            finally {
                super.finallyInvocation(token);
            }
            super.afterInvocation(token, null);
        }
    }
}
