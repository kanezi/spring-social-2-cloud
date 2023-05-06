package com.kanezi.springsocial2cloud.db;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cleans up test data inserted by tests that can't be rolled back
 * <p/>
 * <strong>Only Test-Managed Transactions Are Rolled Back<strong/>
 * <br/>
 * Transactions are not rolled back when the code that is invoked does not interact with the database.
 * An example would be writing an end-to-end test that uses RestTemplate to make an HTTP request
 * to some endpoint that then makes a modification in the database.
 * Since the RestTemplate does not interact with the database (it just creates and sends an HTTP request),
 * <code>@Transactional<code/> will not rollback anything that is an effect of that HTTP request.
 * This is because a separate transaction, one not controlled by the test, will be started.
 * <p/>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Sql("/db/fixture/clean_up_test_user.sql")
public @interface CleanUpTestData {
}
