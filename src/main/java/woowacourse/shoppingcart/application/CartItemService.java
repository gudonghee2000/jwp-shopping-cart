package woowacourse.shoppingcart.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.auth.dto.TokenRequest;
import woowacourse.shoppingcart.dao.CartItemDao;
import woowacourse.shoppingcart.dao.CustomerDao;
import woowacourse.shoppingcart.domain.CartItem;
import woowacourse.shoppingcart.domain.Customer;
import woowacourse.shoppingcart.dto.cartitem.CartItemAddRequest;
import woowacourse.shoppingcart.dto.cartitem.CartItemAddResponse;
import woowacourse.shoppingcart.dto.cartitem.CartItemCreateRequest;
import woowacourse.shoppingcart.dto.cartitem.CartItemCreateResponse;
import woowacourse.shoppingcart.dto.cartitem.CartItemDeleteRequest;
import woowacourse.shoppingcart.dto.cartitem.CartItemResponse;
import woowacourse.shoppingcart.exception.InvalidProductException;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartItemService {

    private static final int CREATE_CART_ITEM_QUANTITY = 1;

    private final CartItemDao cartItemDao;
    private final CustomerDao customerDao;

    public CartItemService(final CartItemDao cartItemDao, final CustomerDao customerDao) {
        this.cartItemDao = cartItemDao;
        this.customerDao = customerDao;
    }

    @Transactional
    public List<CartItemCreateResponse> createCartItems(final TokenRequest tokenRequest,
                                                        final List<CartItemCreateRequest> cartItemCreateRequests) {
        Customer customer = customerDao.findById(tokenRequest.getId());
        return cartItemCreateRequests.stream()
                .map(cartItemCreateRequest -> createCartItemResponse(customer, cartItemCreateRequest.getProductId()))
                .collect(Collectors.toList());
    }

    private CartItemCreateResponse createCartItemResponse(final Customer customer, final Long productId) {
        return new CartItemCreateResponse(createCartItem(customer.getId(), productId), CREATE_CART_ITEM_QUANTITY);
    }

    private Long createCartItem(final Long customerId, final Long productId) {
        if (cartItemDao.existCartItem(customerId, productId)) {
            return cartItemDao.addCartItem(customerId, productId, CREATE_CART_ITEM_QUANTITY);
        }
        return cartItemDao.createCartItem(customerId, productId, CREATE_CART_ITEM_QUANTITY);
    }

    public CartItemAddResponse addCartItem(final TokenRequest tokenRequest,
                                           final CartItemAddRequest cartItemAddRequest) {
        Customer customer = customerDao.findById(tokenRequest.getId());
        validateExistCartItem(cartItemAddRequest.getId());
        Long cartItemId = cartItemDao.addCartItemById(cartItemAddRequest.getId(), cartItemAddRequest.getQuantity());
        return new CartItemAddResponse(cartItemId, cartItemAddRequest.getQuantity());
    }

    private void validateExistCartItem(final Long cartItemId) {
        if (!cartItemDao.existCartItemById(cartItemId)) {
            throw new InvalidProductException("카트에 선택한 상품중 존재하지 않는 상품이 있습니다.");
        }
    }

    public List<CartItemResponse> findCartItemsByCustomerId(final TokenRequest tokenRequest) {
        Customer customer = customerDao.findById(tokenRequest.getId());
        List<CartItem> cartItems = cartItemDao.findByCustomerId(customer.getId());
        return convertToCartItemResponses(cartItems);
    }

    private List<CartItemResponse> convertToCartItemResponses(final List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItemResponse::from)
                .collect(Collectors.toList());
    }

    public void deleteCartItems(final TokenRequest tokenRequest,
                                final List<CartItemDeleteRequest> cartItemDeleteRequests) {
        Customer customer = customerDao.findById(tokenRequest.getId());
        cartItemDeleteRequests
                .forEach(cartItemDeleteRequest -> {
                    validateExistCartItem(cartItemDeleteRequest.getId());
                    cartItemDao.deleteCartItem(cartItemDeleteRequest.getId());
                });
    }
}
