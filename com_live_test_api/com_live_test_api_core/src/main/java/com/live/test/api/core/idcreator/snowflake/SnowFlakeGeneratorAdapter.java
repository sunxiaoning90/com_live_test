package com.live.test.api.core.idcreator.snowflake;

import com.live.test.api.core.idcreator.ju.idcreator.impl.BaseIDCreator;

public class SnowFlakeGeneratorAdapter extends BaseIDCreator {
	SnowFlakeGenerator snowFlake = new SnowFlakeGenerator(1, 1);

	@Override
	public String doNext() {
		String id = String.valueOf(snowFlake.nextId());
		return id;
	}
}
