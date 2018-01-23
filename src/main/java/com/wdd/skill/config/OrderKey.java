package com.wdd.skill.config;

import com.wdd.skill.redis.BasePrefix;

public class OrderKey extends BasePrefix {

	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

}
