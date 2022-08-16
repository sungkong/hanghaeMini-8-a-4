package hanghae8mini.booglogbackend.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 요청 api 주소 설정
                .allowedOrigins("http://frontDomain.com")  // 요청 origin 설정
                .allowedMethods("GET","POST")  // 요청 method 설정
                .maxAge(600);  // pre-flight 리퀘스트 캐싱 유지 시간
    }
}
