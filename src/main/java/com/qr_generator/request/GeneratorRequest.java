package com.qr_generator.request;

public class GeneratorRequest {

	private String bankBin;
	private String accNo;
	private Double amount;
	private String message;

	public GeneratorRequest(String bankBin, String accNo, Double amount, String message) {
		this.bankBin = bankBin;
		this.accNo = accNo;
		this.amount = amount;
		this.message = message;
	}

	public GeneratorRequest() {
	}

	private GeneratorRequest(Builder builder) {
		setBankBin(builder.bankBin);
		setAccNo(builder.accNo);
		setAmount(builder.amount);
		setMessage(builder.message);
	}

	public String getBankBin() {
		return bankBin;
	}

	public void setBankBin(String bankBin) {
		this.bankBin = bankBin;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static final class Builder {

		private String bankBin;
		private String accNo;
		private Double amount;
		private String message;

		public Builder() {
		}

		public Builder bankBin(String val) {
			bankBin = val;
			return this;
		}

		public Builder accNo(String val) {
			accNo = val;
			return this;
		}

		public Builder amount(Double val) {
			amount = val;
			return this;
		}

		public Builder message(String val) {
			message = val;
			return this;
		}

		public GeneratorRequest build() {
			return new GeneratorRequest(this);
		}
	}
}