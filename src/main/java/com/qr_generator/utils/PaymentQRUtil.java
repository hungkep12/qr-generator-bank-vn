package com.qr_generator.utils;

import com.qr_generator.request.GeneratorRequest;
import java.util.Formatter;
import java.util.Objects;

public class PaymentQRUtil {
	private static String crc16(String input) {
		int crc = 0xffff;

		for (int i = 0; i < input.length(); i++) {
			crc ^= input.charAt(i) << 8;

			for (int j = 0; j < 8; j++) {
				crc = (crc & 0x8000) == 0 ? crc << 1 : (crc << 1) ^ 0x1021;
			}
		}

		Formatter formatter = new Formatter();
		formatter.format("%04X", crc & 0xffff);
		String hexValue = formatter.toString();
		formatter.close();
		return hexValue;
	}

	/**
	 * @param number Số cần chuyển đổi
	 * @return Chuỗi số đã được chuyển đổi, nếu số đầu vào nhỏ hơn 10 thì sẽ thêm số 0 vào đầu chuỗi
	 */
	public static String pad2zero(int number) {
		return String.format("%02d", number);
	}

	public static String makeQRContent(GeneratorRequest request) {
		StringBuilder content = new StringBuilder();
		int idLength = request.getBankBin().length() + request.getAccNo().length();

		content.append("000201010211");
		content.append("38");
		content.append(pad2zero(idLength + 38));
		content.append("0010A000000727");
		content.append("01");
		content.append(pad2zero(idLength + 8));
		content.append("00");
		content.append(pad2zero(request.getBankBin().length()));
		content.append(request.getBankBin());
		content.append("01");
		content.append(pad2zero(request.getAccNo().length()));
		content.append(request.getAccNo());

		content.append("0208QRIBFTTA");
		content.append("5303704");

		if (Objects.nonNull(request.getAmount())) {
			content.append("54");
			content.append(pad2zero(request.getAmount().toString().length()));
			content.append(request.getAmount());
		}

		content.append("5802VN");

		if (Objects.nonNull(request.getMessage())) {
			content.append("62");
			content.append(pad2zero(request.getMessage().length() + 4));
			content.append("08");
			content.append(pad2zero(request.getMessage().length()));
			content.append(request.getMessage());
		}

		content.append("6304");
		content.append(crc16(content.toString()));

		return content.toString();
	}
}
