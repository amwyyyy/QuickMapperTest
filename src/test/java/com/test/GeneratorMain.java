package com.test;

import com.wen.mapper.generator.MybatisGeneratorBuilder;

public class GeneratorMain {
	public static void main(String[] args) throws Exception {
		MybatisGeneratorBuilder builder = new MybatisGeneratorBuilder();
		builder.setJdbcConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test", "root", "zjcs",
				"lib\\mysql-connector-java-5.1.28.jar"); // 配置数据连接
		builder.setBasePackage("com.test.project"); // 生成文件的根目录

		// builder.addTable("test", "sys_module", "SysModule");
		builder.addTable("test", "sys_role_module", "SysRoleModule");
		// builder.addTable("test", "sys_role", "SysRole");
		// builder.addTable("test", "sys_user_role", "SysUserRole");
		// builder.addTable("test", "sys_user", "SysUser");

		builder.build();
	}
}
