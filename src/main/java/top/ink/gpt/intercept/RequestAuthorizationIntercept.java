package top.ink.gpt.intercept;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @author wanglongjun
 */
@Component
@Slf4j
public class RequestAuthorizationIntercept extends BasePathMatchInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json";

    @Value("${openAi.token}")
    private String token;

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request newRequest = oldRequest.newBuilder().addHeader(AUTHORIZATION, BEARER + token)
                .addHeader(CONTENT_TYPE,JSON)
                .method(oldRequest.method(), oldRequest.body())
                .build();
        return chain.proceed(newRequest);
    }
}
