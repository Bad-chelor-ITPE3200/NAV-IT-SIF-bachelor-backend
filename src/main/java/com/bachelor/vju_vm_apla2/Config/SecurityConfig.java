package com.bachelor.vju_vm_apla2.Config;

import no.nav.security.token.support.spring.api.EnableJwtTokenValidation;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.springframework.context.annotation.Configuration;


@EnableJwtTokenValidation
@EnableMockOAuth2Server
public class SecurityConfig {

}
