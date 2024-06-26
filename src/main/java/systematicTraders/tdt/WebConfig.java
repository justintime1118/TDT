package systematicTraders.tdt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import systematicTraders.tdt.argumentResolvers.LoginUserArgumentResolver;
import systematicTraders.tdt.interceptors.LogInterceptor;
import systematicTraders.tdt.interceptors.LoginCheckInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = 0;
        registry.addInterceptor(new LogInterceptor())
                .order(++order)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(++order)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/users/register", "/auth/login", "/auth/logout",
                        "/css/**", "/*.ico", "/error/**"
                );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }
}
