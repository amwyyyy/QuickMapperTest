package com.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestTest {

	@Test
	public void test() throws ScriptException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 3);

		list.stream().filter(value -> value > 1).map(value -> {
			return value + "b";
		}).collect(Collectors.toList());

		// 去重
		Optional<String> o = Optional.ofNullable("a");
		o.map(value -> {
			return value + "a";
		}).orElse(null);

		Set<String> perms = new HashSet<>();
		perms.add("aa;bb");
		perms.add("cc;dd");

		perms.stream().filter(StringUtils::isNotEmpty).map(perm -> {
			return "aa";
		}).collect(Collectors.toSet()).forEach(System.out::println);
	}

	@Test
	public void test1() throws InterruptedException {
		// new Thread(() -> {
		// String s = "yyyy-MM-dd";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();
		//
		// new Thread(() -> {
		// String s = "yyyy-MM-dd HH";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();
		//
		// new Thread(() -> {
		// String s = "yyyyMMdd";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();
		//
		// new Thread(() -> {
		// String s = "yyyy-MM-dd HH:mm";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();
		//
		// new Thread(() -> {
		// String s = "yyyy-MM-dd HH:mm:ss";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();
		//
		// new Thread(() -> {
		// String s = "yyyyMM-dd";
		// System.out.println(s + " - " + new SimpleDateFormat(s).format(new
		// Date()));
		// }).start();

		// TimeUnit.SECONDS.sleep(1);
	}
}
