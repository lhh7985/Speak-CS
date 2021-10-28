package com.js.freeproject.util;

import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LogUtil {
	public static void Msg(String msg) {
		//log.info(msg);
	}

	public static void Msg(String desc, String msg) {
		//log.info(desc.concat(" : ").concat(msg));
	}
}
