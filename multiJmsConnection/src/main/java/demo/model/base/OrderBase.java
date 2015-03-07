package demo.model.base;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import demo.model.JSON;


public class OrderBase {
	
	@JsonView(JSON.Orders.class)
	private Long id;

	@JsonView(JSON.Orders.class)
	private String ccyPair;
	
	@JsonView(JSON.Orders.class)
	private BigDecimal amount;
	
	@JsonView(JSON.Orders.class)
	private String buySell;
	
	@JsonView(JSON.Orders.class)
	private Date valueDate;
	
	@JsonView(JSON.Orders.class)
	private Double rate;

	public String getCcyPair() {
		return ccyPair;
	}

	public void setCcyPair(String ccyPair) {
		this.ccyPair = ccyPair;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuySell() {
		return buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	
	

}
