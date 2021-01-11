package com.pranab.algos;

import java.math.BigInteger;

public class KaratsubaMul {
	public static void main(String[] args) {
		System.out.println(karatsuba_Mul(args[0], args[1]));
		// System.out.println((long)Math.pow(10, 64/2)*23);
	}

	public static BigInteger karatsuba_Mul(String num1, String num2) {
		if (num1.length() == 1 && num2.length() == 1) {
			BigInteger data1 = new BigInteger(num1);
			BigInteger data2 = new BigInteger(num2);
			return data1.multiply(data2);
		} else {
			int max_num = Math.max(num1.length(), num2.length());
			if (num1.length() < max_num) {
				for (int i = 0; i < max_num - num1.length(); i++) {
					num1 = "0" + num1;
				}
			} else if (num2.length() < max_num) {
				for (int i = 0; i < max_num - num2.length(); i++) {
					num2 = "0" + num2;
				}
			}

			int mid1 = (int) Math.floor(num1.length() / 2.0);
			int mid2 = (int) Math.floor(num2.length() / 2.0);
			String num1_first = num1.substring(0, mid1);
			if ("".equals(num1_first)) {
				num1_first = "0";
			}
			String num1_second = num1.substring(mid1, num1.length());
			String num2_first = num2.substring(0, mid2);
			if ("".equals(num2_first)) {
				num2_first = "0";
			}
			String num2_second = num2.substring(mid2, num2.length());

			BigInteger num_1 = new BigInteger(num1_first);
			BigInteger num_2 = new BigInteger(num1_second);
			BigInteger num_3 = new BigInteger(num2_first);
			BigInteger num_4 = new BigInteger(num2_second);
			BigInteger prod_one = karatsuba_Mul(num1_first, num2_first);
			BigInteger prod_two = karatsuba_Mul(num1_second, num2_second);
			BigInteger prod_three = karatsuba_Mul(num_1.add(num_2).toString(), num_3.add(num_4).toString());
			BigInteger diff = prod_three.subtract(prod_two).subtract(prod_one);

			int poewr_raised = (max_num % 2 == 1 ? max_num + 1 : max_num);
			BigInteger val_one = prod_one.multiply((new BigInteger("10")).pow(poewr_raised));
			BigInteger val_two = diff.multiply((new BigInteger("10")).pow(poewr_raised / 2));
			BigInteger val = val_one.add(val_two).add(prod_two);
			return val;
		}
	}
}
