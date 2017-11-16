package com.mundo.web.mvc;

import com.mundo.web.annotation.CheckCsrf;
import com.mundo.web.annotation.CheckLogin;
import com.mundo.data.constant.Words;

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
