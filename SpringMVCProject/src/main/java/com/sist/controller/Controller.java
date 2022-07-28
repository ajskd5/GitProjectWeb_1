package com.sist.controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
// @Controller => 모델인지 아닌지 구분
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

}
