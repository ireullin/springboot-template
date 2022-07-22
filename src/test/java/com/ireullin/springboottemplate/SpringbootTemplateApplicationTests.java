package com.ireullin.springboottemplate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Repeat;

// @SpringBootTest // 有這個@Autowired等功能才會生效
@DisplayName("SpringbootTemplateApplicationTests 測試")
class SpringbootTemplateApplicationTests {

	@Test
	@DisplayName("第一個測試")
	// @Disabled // 不需要的時候可以關閉
	void test1() {
	}


	@Test
	@DisplayName("重複測試")
	@Repeat(5)
	void repeatTest(){
		System.out.println("fuck");
	}

}
