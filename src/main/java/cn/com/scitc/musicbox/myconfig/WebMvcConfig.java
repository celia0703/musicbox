package cn.com.scitc.musicbox.myconfig;
import cn.com.scitc.musicbox.common.MyInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//注册拦截器
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //移除用户
    public  final static String SESSION_KEY = "loginUser";
    public  final static String MSG = "msg";
    //fastjon格式转换
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //定义一个converter转换消息的对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //添加fastjson的配置信息
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //处理中文乱码
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        //在convert中添加配置信息
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        //将convert加入converter中
//        converters.add(fastJsonHttpMessageConverter);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPattern后跟拦截地址，excludePathPatterns后跟排除拦截地址
        //避免cookie经过重定向次数过多，建议分开写排除不适用数组形式
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/music/music")
                                                    .addPathPatterns("/music/comment")
                                                    .addPathPatterns("/comment")
                                                    .addPathPatterns("/user/myZoe");
//                                                    .addPathPatterns("/admin/Cuser")
//                                                    .addPathPatterns("/admin/Cmusic")
//                                                    .addPathPatterns("/admin/CmusicList")
//                                                    .addPathPatterns("/admin/Ccomment");
//                .excludePathPatterns("/static/**")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/ulogin")
//                .excludePathPatterns("/music/listen")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("user/register");
    }
    //存放音乐的实际地址

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //                指定磁盘存放音乐的绝对路径，所有静态资源存放在music文件夹下
        registry.addResourceHandler("/music/**")
                .addResourceLocations("file:/Users/mac/Desktop/music/");
    }
}
