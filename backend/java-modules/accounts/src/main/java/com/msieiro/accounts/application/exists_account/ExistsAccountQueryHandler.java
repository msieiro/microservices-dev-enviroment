package com.msieiro.accounts.application.exists_account;

import com.msieiro.accounts.domain.AccountEmail;
import com.msieiro.shared.domain.Service;
import com.msieiro.shared.domain.bus.query.QueryHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class ExistsAccountQueryHandler implements QueryHandler<ExistsAccountQuery, ExistsAccountResponse> {

    @Autowired
    private ExistsAccountChecker existsAccountChecker;

    @Override
    public ExistsAccountResponse handle(final ExistsAccountQuery query) {
        log.info("Ha llegado una query al queryHandler: {}", query.toString());
        return new ExistsAccountResponse(existsAccountChecker.accountExist(new AccountEmail(query.email())));
    }
}
