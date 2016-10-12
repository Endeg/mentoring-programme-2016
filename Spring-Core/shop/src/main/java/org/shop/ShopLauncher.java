package org.shop;


import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.UserService;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        final OrderService orderServiceByName = (OrderService) context.getBean("orderService");
        final ItemService itemServiceByType = context.getBean(ItemService.class);
        final OrderService orderServiceByNameAndType = context.getBean("orderService", OrderService.class);
        final UserService userServiceByAlias = context.getBean("userServiceAlias", UserService.class);
        final ProductService productService = context.getBean(ProductService.class);
        final ProposalService proposalService = context.getBean(ProposalService.class);

        Product galaxy = productService.getProductsByName("Samsung Galaxy Tab").get(0);
        Proposal proposal = proposalService.getProposalsByProduct(galaxy).get(0);

        orderServiceByName.createOrder(userServiceByAlias.getUserById((long) 1), proposal);

        for (Order order : orderServiceByName.getOrdersByUserId((long) 1)) {
            System.out.println(order);

            for (Item item : itemServiceByType.getItemsByOrderId(order.getId())) {
                System.out.println(item);
            }
        }
    }
}
