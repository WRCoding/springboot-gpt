package top.ink.gpt.intercept;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class RequestAuthorizationIntercept extends BasePathMatchInterceptor {
    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        log.info("method: {}, body: {}", oldRequest.method(), oldRequest.body() );
        Request newRequest = oldRequest.newBuilder().addHeader("Authorization", "Bearer " + "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")
                .addHeader("Content-Type","application/json")
                .method(oldRequest.method(), oldRequest.body())
                .build();
        System.out.println(newRequest.url());
        return chain.proceed(newRequest);
    }
}
