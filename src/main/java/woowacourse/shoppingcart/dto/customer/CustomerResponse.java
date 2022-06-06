package woowacourse.shoppingcart.dto.customer;

import woowacourse.shoppingcart.domain.Customer;

public class CustomerResponse {

    private Long id;
    private String userId;
    private String nickname;

    private CustomerResponse() {
    }

    private CustomerResponse(final Long id, final String userId,
                             final String nickname) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
    }

    public static CustomerResponse from(final Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getUserId(), customer.getNickname());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }
}
