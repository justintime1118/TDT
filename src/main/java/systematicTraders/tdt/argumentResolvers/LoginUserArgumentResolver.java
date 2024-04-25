package systematicTraders.tdt.argumentResolvers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import systematicTraders.tdt.domain.auth.SessionConst;
import systematicTraders.tdt.domain.user.User;

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 지원하는 파라미터인지 체크
     * @param parameter the method parameter to check
     * @return @Login 애너테이션이 붙어있으면서 User 타입인 파라미터인지 여부
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasUserType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && hasUserType;
    }

    /**
     * 로그인 정보 반환
     * @param parameter the method parameter to resolve. This parameter must
     * have previously been passed to {@link #supportsParameter} which must
     * have returned {@code true}.
     * @param webRequest the current request
     * @return 세션에 있는 로그인 정보를 담은 User 객체 or 만약 session 자체가 없거나, User 객체가 없으면 null
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_USER);
    }
}
