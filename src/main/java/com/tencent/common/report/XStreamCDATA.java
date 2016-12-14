package com.tencent.common.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description
 *
 * @author Dream_Kidd
 * @since 2016/12/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface XStreamCDATA {
}
