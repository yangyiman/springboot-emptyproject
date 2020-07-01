## Rest+Security
基于RestFul风格的基于url校验后台系统
### 实现原理
1. 认证  
    - 基于JWT,实现无感知的登录  
    基于Json方式的登录,登录成功即可生成token(失效时间为2H),并且将token置于请求头中(Authorization)
    每次请求都会校验token,校验成功即为登录成功
   - 相关的类  
      1. JwtLoginAuthenticationFilter 处理登录请求  
      2. JwtCheckAuthenticationFilter 处理token,并且解析  
      3. JwtSuccessAuthenticationHandler 登录成功  
      4. JwtFailureAuthenticationHandler 登录失败  
      5. JwtAccessDeniedHandler 403  
      6. JwtEntryPointHandler 401
      7. JwtLogoutHandler 登出
2. 授权  
   - 基于url和请求方式鉴权,进一步将粒度降低  
   - 相关的类  
      1. MyFilterInvocationSecurityMetadataSource 这个类用于判断当前请求的url是否需要权限,需要的话返回其权限  
      2. MyAccessDecisionManager将用户权限和所需权限对比,看用户是否可以访问  
      3. MyFilterSecurityInterceptor 拦截请求的类
  
  ## 测试  
  ### 接口
  tb_blog表  
  blog POST  
  blog/:id PUT  
  blog/:id GET  
  blog/list GET  
  blog/:id DELETE  
  ### 用户和权限
  1. tom=admin ALL
  2. jack=user GET/POST