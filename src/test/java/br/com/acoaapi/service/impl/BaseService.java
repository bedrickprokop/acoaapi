package br.com.acoaapi.service.impl;

import br.com.acoaapi.App;
import br.com.acoaapi.config.H2DatabaseConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {App.class, H2DatabaseConfig.class})
@WebAppConfiguration
public abstract class BaseService {
}
