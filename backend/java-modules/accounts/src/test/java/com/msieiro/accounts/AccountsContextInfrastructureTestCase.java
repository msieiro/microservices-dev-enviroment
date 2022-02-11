package com.msieiro.accounts;

import com.msieiro.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AccountsApplication.class)
@SpringBootTest
public abstract class AccountsContextInfrastructureTestCase extends InfrastructureTestCase {

}
