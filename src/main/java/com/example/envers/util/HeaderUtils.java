package com.example.envers.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderUtils {

    private static final String USER_NAME = "username";
    private static final String USER_ID = "userId";

    public static String getUsername() {
        return getHeaderValue(USER_NAME);
    }

    public static String getUserId() {
        return getHeaderValue(USER_ID);
    }

    private static String getHeaderValue(String header) {

        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(servletRequestAttributes)) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getHeader(header);
        }

        return null;
    }
}
