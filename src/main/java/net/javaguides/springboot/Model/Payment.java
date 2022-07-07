package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long paymentId;
	
	@Column(name ="polId",nullable = false)
	private long polId;
	
	@Column(name ="userId",nullable = false)
	private long userId;
	
	@Column(name ="paymentTransactionId",nullable = false)
	private long paymentTransactionId;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getPolId() {
		return polId;
	}

	public void setPolId(long polId) {
		this.polId = polId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(long paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
	
	

}
