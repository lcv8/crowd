package com.crowd.mvc.config;

import com.crowd.contant.CrowdConstant;
import com.crowd.exception.AccessForbiddenException;
import com.crowd.exception.LoginAcctAlreadylnUseException;
import com.crowd.exception.LoginAcctAlreadylnUseForUndateException;
import com.crowd.exception.LoginFailedException;
import com.crowd.util.CrowdUtil;
import com.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//该注解表示这是一个基于注解的异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctAlreadylnUseForUndateException.class)
    public ModelAndView resolveLoginAcctAlreadylnUseForUndateException(
            //实际捕获的异常类型
            LoginAcctAlreadylnUseForUndateException exception,
            //请求对象
            HttpServletRequest request,
            HttpServletResponse response){
        String viewName = "system-error";
        return commonResolve(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginAcctAlreadylnUseException.class)
    public ModelAndView resolveLoginAcctAlreadylnUseException(
            //实际捕获的异常类型
            LoginAcctAlreadylnUseException exception,
            //请求对象
            HttpServletRequest request,
            HttpServletResponse response){
        String viewName = "admin-add";
        return commonResolve(viewName,exception,request,response);
    }

    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            //实际捕获的异常类型
            AccessForbiddenException exception,
            //请求对象
            HttpServletRequest request,
            HttpServletResponse response){
        String viewName = "admin-login";
        return commonResolve(viewName,exception,request,response);
    }

    // @ExceptionHandler将一个具体的异常类型和一个方法关联起来
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            //实际捕获的异常类型
            LoginFailedException exception,
            //请求对象
            HttpServletRequest request,
            HttpServletResponse response){
        String viewName = "admin-login";
        return commonResolve(viewName,exception,request,response);
    }

    // @ExceptionHandler将一个具体的异常类型和一个方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointException(
            //实际捕获的异常类型
            NullPointerException exception,
            //请求对象
            HttpServletRequest request,
            HttpServletResponse response){
        String viewName = "system-error";
        return commonResolve(viewName,exception,request,response);
    }

    /**
     * @param viewName 异常处理完成要去的地方
     * @param exception 实际捕获到的异常
     * @param request 当前请求对象
     * @param response 当前响应对象
     * */
    public ModelAndView commonResolve(String viewName , Exception exception ,
                                      HttpServletRequest request , HttpServletResponse response){
        //获取异常消息
        String exceptionMessage = exception.getMessage();

        //1.判断当前请求的类型
        boolean requestType = CrowdUtil.judgRequestType(request);

        //2.如果是一个ajax请求
        if(requestType) {
            //3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exceptionMessage);
            //4.创建Gson对象
            Gson gson = new Gson();
            //5.将对象转换为Json字符串
            String json = gson.toJson(resultEntity);
            //6.将Json字符串作为响应体返回给浏览器
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //7.由于上面已经通过原生response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }
        //不是ajax请求
        ModelAndView modelAndView = new ModelAndView();
        //1.将exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        //2.设置对应视图
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
