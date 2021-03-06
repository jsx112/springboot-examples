package com.springboot.https.conf;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConnectorConfig { // 此类专门负责HTTP的连接的相关配置

	private Logger logger = LoggerFactory.getLogger(HttpConnectorConfig.class);

	private static final String HTTP_URL_PATTERNS[] = {
			"/hello",
			"/static/*",
			"/download/*",
			"/doc/*"
	};

	@Value("${http.port}")
	private int httpPort;

	@Value("${server.port}")
	private int serverPort;

	@Bean
	public Connector initConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol") ;
		connector.setScheme("http"); 	// 如果现在用户使用普通的http的方式进行访问
		connector.setPort(httpPort);	// 用户访问的是80端口
		connector.setSecure(true); // 如果该连接为跳转则表示不是一个新的连接对象
		connector.setRedirectPort(serverPort); 	// 设置转发操作端口
		return connector; 
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory servletContainerFactory(Connector connector) {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("NONE");
				SecurityCollection collection = new SecurityCollection();
				for (String pattern : HTTP_URL_PATTERNS) {
					collection.addPattern(pattern);
				}
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);

				SecurityConstraint securityConstraint2 = new SecurityConstraint();
				securityConstraint2.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection2 = new SecurityCollection();
				collection2.addPattern("/");
				securityConstraint2.addCollection(collection2);
				context.addConstraint(securityConstraint2);

				logger.info("Constraints length = " + context.findConstraints().length);
			}
		};
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}
}
 