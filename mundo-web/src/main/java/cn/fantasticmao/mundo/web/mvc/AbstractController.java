package cn.fantasticmao.mundo.web.mvc;

import cn.fantasticmao.mundo.web.annotation.CheckCsrf;
import cn.fantasticmao.mundo.web.annotation.CheckLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * AbstractController
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/7/9
 */
@CheckCsrf
@CheckLogin
public abstract class AbstractController<User> {

    @SuppressWarnings("unchecked")
    protected Optional<User> getUserSession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Optional.ofNullable(user);
    }

    protected boolean filterXss() {
        return true;
    }
}
