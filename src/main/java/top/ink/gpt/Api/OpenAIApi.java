package top.ink.gpt.Api;


import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;
import org.springframework.stereotype.Service;
import retrofit2.http.Body;
import retrofit2.http.POST;
import top.ink.gpt.entity.completion.CompletionRequest;
import top.ink.gpt.entity.completion.CompletionResponse;
import top.ink.gpt.intercept.RequestAuthorizationIntercept;

@RetrofitClient(baseUrl = "https://api.openai.com/", connectTimeoutMs = 0,readTimeoutMs = 0,writeTimeoutMs = 0,callTimeoutMs = 0)
@Intercept(handler = RequestAuthorizationIntercept.class)
public interface OpenAIApi {

    @POST("v1/completions")
    CompletionResponse completion(@Body CompletionRequest completionRequest);
}
