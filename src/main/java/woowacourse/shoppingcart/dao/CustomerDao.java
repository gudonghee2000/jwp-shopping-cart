package woowacourse.shoppingcart.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.domain.customer.Customer;
import woowacourse.shoppingcart.exception.InvalidCustomerException;

import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.Objects;

@Repository
public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long findIdByUserName(final String userName) {
        try {
            final String query = "SELECT id FROM customer WHERE username = ?";
            return jdbcTemplate.queryForObject(query, Long.class, userName.toLowerCase(Locale.ROOT));
        } catch (final EmptyResultDataAccessException e) {
            throw new InvalidCustomerException();
        }
    }

    public Long save(final Customer customer) {
        String query = "INSERT INTO customer (user_id, nickname, password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setString(1, customer.getUserId());
            preparedStatement.setString(2, customer.getNickname());
            preparedStatement.setString(3, customer.getPassword());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Boolean existCustomerByUserId(final String userId) {
        String query = "SELECT EXISTS (SELECT id FROM customer WHERE user_id = ?)";
        return jdbcTemplate.queryForObject(query, Boolean.class, userId);
    }

    public Boolean existCustomerByNickname(final String nickname) {
        String query = "SELECT EXISTS (SELECT id FROM customer WHERE nickname = ?)";
        return jdbcTemplate.queryForObject(query, Boolean.class, nickname);
    }
}
