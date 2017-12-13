package com.springboot.sitemesh.conf.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;

/**
* 前台页面装饰器配置
* @author jiasx
* @create 2017/11/24 17:38
**/
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

	/** 需要装饰的访问路径 */
    @Value("${sitemesh.contentPath}")
	private String contentPath;

	/** 装饰器页面路径 */
	@Value("${sitemesh.decoratorPath}")
	private String decoratorPath;

	/** 不需要装饰的访问路径,多个之间用英文逗号分隔 */
	@Value("${sitemesh.excludedPaths}")
	private String excludedPaths;

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

		// 通过配置文件
		builder.addDecoratorPath(contentPath, decoratorPath);
		if (excludedPaths == null) {
			return;
		}
		String[] paths = excludedPaths.split(",");
		for (String path : paths) {
			builder.addExcludedPath(path);
		}
	}

}