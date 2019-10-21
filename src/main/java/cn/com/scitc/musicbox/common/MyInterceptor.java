package cn.com.scitc.musicbox.common;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//登录拦截器
public class MyInterceptor implements HandlerInterceptor {
//在请求处理之前进行调用（Controller方法调用之前）
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("loginUser");//获取登录的session信息
        //
        if(user!= null){
        return true;
        }
        else{
        request.setAttribute("msg","*没有权限请登录*");
        response.sendRedirect(request.getContextPath()+"/user/login");
        return false;
        }
        }
//请求处理之后进行调用，但是在视图呗渲染之前（Controller方法调用之后）
@Override
public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用\n");
        }
//在整个请求结束之后被调用，主要是用于进行资源清理工作
@Override
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion被调用\n");
        }
        }
