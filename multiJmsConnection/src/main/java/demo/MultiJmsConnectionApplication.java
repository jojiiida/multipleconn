package demo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import demo.model.Order;

@SpringBootApplication
public class MultiJmsConnectionApplication {

    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = SpringApplication.run(MultiJmsConnectionApplication.class, args);
        OrderService orderService = ctx.getBean(OrderService.class);
		Order sample = new Order();
		sample.setCcyPair("USD/JPY");
		sample.setAmount(new BigDecimal(1000000));
		sample.setBuySell("Buy");
		sample.setRate(120.0d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sample.setValueDate(sdf.parse("2015-03-10"));
		orderService.saveOrder(sample);

    }
}
