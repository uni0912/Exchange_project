package kopoland;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProcessingClass {
	public static Calendar cal = Calendar.getInstance();
	OutputClass output = null;
	
	ProcessingClass() {
		output = new OutputClass();
		
	}
	//������ ��� �Լ�
	public int calcAge(String customerIDNumber) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		Date date = new Date();
		int customerYear = Integer.parseInt(customerIDNumber.substring(0, 2)); 
		Date customerDate = sdf.parse(customerIDNumber.substring(2, 6));
		int todayYear = cal.get(cal.YEAR);
		Date todayDate = sdf.parse(sdf.format(cal.getTime()));
		int age = 0;
		
		if (customerYear >= 0 && customerYear <= (todayYear-2000)) {
			customerYear = customerYear + 2000;
		} else {
			customerYear = customerYear + 1900;
		}
		
		int compare = customerDate.compareTo(todayDate);
		if (compare >= 0) {
			age = todayYear - customerYear - 1;
		} else {
			age = todayYear - customerYear;
		}
		return age;
	}
	//���ɴ뺰 �з�
	public int calcAgeGroup(int age) {
		if (age <= ConstValueClass.MAX_BABY) {
			return 1;
		} else if (age > ConstValueClass.MAX_BABY && age <= ConstValueClass.MAX_CHILD) {
			return 2;
		} else if (age > ConstValueClass.MAX_CHILD && age <= ConstValueClass.MAX_TEEN) {
			return 3;
		} else if (age > ConstValueClass.MAX_TEEN && age <= ConstValueClass.MAX_ADULT) {
			return 4;
		} else {
			return 5;
		}
	}
	//�ְ���, �߰��ǰ� ���̿� ���� �ݾ� ���
	public int calcPriceProcess(int ticketSelect, int age) {
		int calcPirce = 0;
		if (ticketSelect == 1) {
			switch (calcAgeGroup(age)) {
			case 1:
				calcPirce = ConstValueClass.BABY_PRICE;
				break;
			case 2:
				calcPirce = ConstValueClass.CHILD_DAY_PRICE;
				break;
			case 3:
				calcPirce = ConstValueClass.TEEN_DAY_PRICE;
				break;
			case 4:
				calcPirce = ConstValueClass.ADULT_DAY_PRICE;
				break;
			case 5:
				calcPirce = ConstValueClass.OLD_DAY_PRICE;
				break;
			}
		} else {
			switch (calcAgeGroup(age)) {
			case 1:
				calcPirce = ConstValueClass.BABY_PRICE;
				break;
			case 2:
				calcPirce = ConstValueClass.CHILD_NIGHT_PRICE;
				break;
			case 3:
				calcPirce = ConstValueClass.TEEN_NIGHT_PRICE;
				break;
			case 4:
				calcPirce = ConstValueClass.ADULT_NIGHT_PRICE;
				break;
			case 5:
				calcPirce = ConstValueClass.OLD_NIGHT_PRICE;
				break;
			}
		}
		return calcPirce;
	}
	//�����׿� ���� ���� ���
	public int calcDiscount(int calcPrice, int discountSelect) {
		switch (discountSelect) {
			case 1:
				break;
			case 2:
				calcPrice = (int)(calcPrice *  ConstValueClass.DISABLE_DISCOUNT_RATE);
				break;
			case 3:
				calcPrice = (int)(calcPrice *  ConstValueClass.MERIT_DISCOUNT_RATE);
				break;
			case 4:
				calcPrice = (int)(calcPrice * ConstValueClass.MULTICHILD_DISCOUNT_RATE);
				break;
			case 5:
				calcPrice = (int)(calcPrice * ConstValueClass.PREGNANT_DISCOUNT_RATE);
				break;
		}
		return calcPrice;
	}
	//�ֹ������� ���� �����ݾ� ���
	public int calctotalPrice(int calcPrice, int ticketCount) {
		int totalPrice = 0;
		totalPrice = calcPrice * ticketCount;
		return totalPrice;
	}

}