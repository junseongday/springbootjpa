package com.js.springbootjpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class AccountRepositoryTest {
    Logger logger = LoggerFactory.getLogger(AccountRepositoryTest.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void jdbcTest() throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
           DatabaseMetaData metaData = connection.getMetaData();
           logger.info(metaData.getDriverName());
           logger.info(metaData.getURL());
           logger.info(metaData.getUserName());
        }
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setPassword("1234");
        account.setUserName("JS");
        Account newAccount = accountRepository.save(account);

        assertThat(newAccount).isNotNull();
        Account existAccount = accountRepository.findByUserName(newAccount.getUserName());
        assertThat(existAccount).isNotNull();

        Account nonExistAccount = accountRepository.findByUserName("whiteship");
        assertThat(nonExistAccount).isNull();
    }
}