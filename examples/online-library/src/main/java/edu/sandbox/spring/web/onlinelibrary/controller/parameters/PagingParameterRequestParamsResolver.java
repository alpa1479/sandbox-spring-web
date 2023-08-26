package edu.sandbox.spring.web.onlinelibrary.controller.parameters;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

public class PagingParameterRequestParamsResolver extends RequestParamMethodArgumentResolver {

    public PagingParameterRequestParamsResolver(boolean useDefaultResolution) {
        super(useDefaultResolution);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        return new Pagination(Long.parseLong(request.getParameter("limit")), Long.parseLong(request.getParameter("offset")));
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PagingParam.class);
    }
}
