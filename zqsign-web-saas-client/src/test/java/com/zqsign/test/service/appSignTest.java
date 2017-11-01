package com.zqsign.test.service;

import java.util.HashMap;

import org.junit.Test;

import com.zqsign.client.util.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

public class appSignTest {

	String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
//	String url="http://192.168.1.155:8080/zqsign-web-saas-service";
//	String zqid="ZQ9B008946DCEB4A088DC491AFF2CF2D2C";
//	String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAITXQVyl0O8MiQBmcXOQOanNA9+ylKJVxS3SyN1bkU5lXJ5Avkb2lUoDrfllKh6mcjQ3XYyUolYEwHQB8+iYltCcU50IX8f08zmCgk+L4ZbYXb5Uw01Hj8qwjEH44lxlo48nk34MKAlGjQ8BcQt91tNkUvwYhOtH6gqfDCyUS6+9AgMBAAECgYA5K57Arcg+qSlpF62p6581ebs6EdQwDTPAJeA2jAr8L1lt7jU1uzfC09SS6bv31vB9knpbXziCRqdBBfof11PIMVaMCpulHlQATYAG4ePACgn+5vCgDqUDwvFB2a5b3nvwRgZS8jGGUuhEUcpU81qyQqfltXNQ5a1GB1NE0k4coQJBANgEZpot/Z39A+9zreZ5qGuwK/eNp/PY5/op0iETCd8md5k33/1F2dZzzctqowHPNQnERPDnvSBfeqgfwM58UbUCQQCdbbAP23Cg9enBMtfCqZ4w5obWcDTwxZhCUwk+b1NHtXoSXBSgqhtIyt5sRM+PGjzetWCr7fmsvGdQocsfy0rpAkAQwr6nRdxAzunoQWSD1nqv6Fpw+cfHTtQo/+ey80Q30UdEVyaFA090qUWVe++r60TlPhpPStvM0Du3IceW/eRlAkB65TrxVhQ5g1+k86TdkP4uHHgchOG/J/GxrRLlS5DOxQvzszBjMAg2rjAugxUpGIl2Lk+2SAOF5loc5wEj49apAkEArS1GDBHnR+0ry1rfUD6UUj/rdVZmNOnWpDkHWFViZkyZK2pElxvjQROFQ1sCly5mo+IidEOJc2uvNeAIonS6rA==";
	String url="http://192.168.1.221";
	
	@Test
	public void appShowView() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "11f1daaa-eca7-430f-9c76-bdfbb44ff4d1");
		hashMap.put("user_code", "992127e7-bf4c-4985-850c-c1b69d10b775");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/appShowView", hashMap);
		System.out.println(s);
	}
	
}
