package com.mundo.web.filter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * HttpFormatRequestLoggingFilterTest
 *
 * @author maomao
 * @since 2019-06-15
 */
public class HttpFormatRequestLoggingFilterTest {

    @Test
    public void createMessage() {
        HttpFormatRequestLoggingFilter filter = new HttpFormatRequestLoggingFilter();
        MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.POST.name(), "/login");
        request.setQueryString("a=1&b=2&c=3");
        request.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        request.addHeader("Cache-Control", "no-cache");

        String msg = filter.createMessage(request, null, null);
        String expected = "POST /login?a=1&b=2&c=3 HTTP/1.1" + System.lineSeparator() +
                "Content-Type: application/x-www-form-urlencoded" + System.lineSeparator() +
                "Cache-Control: no-cache" + System.lineSeparator();
        Assert.assertEquals(expected, msg);
    }
}