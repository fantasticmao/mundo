package com.mundo.mvc;

import com.mundo.annotation.CheckCsrf;
import com.mundo.annotation.CheckLogin;
import com.mundo.constant.Words;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * AbstractController
 *
 * @author maodh
 * @since 2017/7/9
 */
@CheckCsrf
@CheckLogin
public abstract class AbstractController<User> {

    @SuppressWarnings("unchecked")
    protected Optional<User> getUserSession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Words.USER);
        return Optional.ofNullable(user);
    }

    protected boolean filterXss() {
        return true;
    }
}
