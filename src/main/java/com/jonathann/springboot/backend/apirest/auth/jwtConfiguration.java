 package com.jonathann.springboot.backend.apirest.auth;

public class jwtConfiguration {
	public final static String LLAVE_SECRETA = "alguna.clave.secreta.12345";
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpQIBAAKCAQEAuKJHqwhg+DF604nUgvfLUdQwnHy3gHWkmfKMQO46aGc6IAgf\n" + 
			"yljzm3eDRNDg9kZVlLEGsVnugc4rxtX5UXOURJVVOyHoxRPyVQjnaNa0FPtV4eHZ\n" + 
			"7iNaMpiHg3zxS5HQAYKBsPHawLrprVGIJ/0FGRnAumhpKmpa/oE7lUjDozESBHQ8\n" + 
			"xBBRuM7dLmcV4KnPGKZi048kTx8pNpCjW6/Butmd8CO25nNFwtPkBVfX5fyyOeSx\n" + 
			"wQC9HrFJ3SnGT5FBU7bIU6TxyqkvjvmYzruJQn2lQ+zkKpPaS3Dy/UcNLFihGZXB\n" + 
			"qqp8dUGNT7yez35cbAhduP3JwpieX4RTm89KrwIDAQABAoIBAQCyUsIHdXs6m4d2\n" + 
			"wjtHFw7/hIJC0c3luzn0GifOGoi9B15DS7VwmMMIF8Dn+RodokbL6sJ9Dk/mfPsY\n" + 
			"+tbnDUtZhiWgjBqmJaK68HOYdS3OygTjul/iubBHB7xyiXz/sOAGZUbfYGgYFy7U\n" + 
			"X9eyT5TZ7l6Pa4KPHdOu1aB3iaSyenCEPIImiuF7gmhxQldeuH1wUkv9cqXEWcE9\n" + 
			"P4/+YqAoKJQI0ClU4IWWJo4meQCEGJXelmo45Zd59t73zwazZTokRf9PODaVzoTy\n" + 
			"Nmw1wD31Fh6xQ3TdF53Xx7CXNeFNCOAE9HE9m6JSMD6bzV6Uvt0zmymRFcGZBLwH\n" + 
			"rc641wIxAoGBAOgCTg+h+0YMVWQoMYpi3qsjqvCSDvZ0EUKIbtHzaiVciOhsJCJ8\n" + 
			"Lf8LrgJAeHPs33JbIAcPgz867L9Ulg6x2CoXWYPAuOUYdCFH2xFffpXURHp5F2/C\n" + 
			"+oG05ZcUCsa4PaJ7bTeoe+vT/uT7aODfUuifESRoiFRD4+AEOAzGD+DJAoGBAMu5\n" + 
			"3wA9Y9njqkj9bfll/RSTp3h8PUZ+jE+vhB7uWrAruYDdG7+jQt4wRh5qQrzipq7p\n" + 
			"ycN1yottnt4a0cXZzJhIhn2G0awyMEnUOk75QJV/WNsrDNKHQ9GfvT7aLypHyTIk\n" + 
			"/EnKeoDdj3tyvs3u81ggdRXw1MRi7GxJFSATGUO3AoGBAIqqpgFN7SLmyDq+ky2E\n" + 
			"/lUHUmnKwkqnAJHlfWbat3xxHBreV5KM6ejgEDakVdnjn4F32WKe8PDfxRRt9+n8\n" + 
			"fZhasFPA4kF7Jh2mFtcBXmUqo2sX6NwRiyvu9LIhdndjMuNkJdj4SGihIl21re+J\n" + 
			"SsJiIBrEp4bFtPL1UpK/5kmRAoGAAY98682MlECwLzrxurVTSPMQl1WsSnvxqzKW\n" + 
			"+n2s6CiNlTUcX01q17Ta9KSvui6qRDC6QOsIECVY0C64aKGQdaIolZGQzZbc09Py\n" + 
			"VmSGYB7HmXYs9PfgRxzIIFEfh11hZybtX8tTQOVxkeqBF6a66nPlMbPUjWSMlw/A\n" + 
			"P6ylxuUCgYEA0YC4lMDnIjtjr4wevZoeo4uOLldDrXh7faWJ+N4pQyCZEFlNpXmc\n" + 
			"kU4ZmZj1oWXetIkXJX/BOLwXtyRSsHC1AIbDvBOxd1PtLGNFW5trVsNtVG4tlxvq\n" + 
			"PuN7t9iwd0Wmru7Jw7Yj2sQX5NXo6+TDcATHq/giZ5Rhx9ZHZtlQJr8=\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuKJHqwhg+DF604nUgvfL\n" + 
			"UdQwnHy3gHWkmfKMQO46aGc6IAgfyljzm3eDRNDg9kZVlLEGsVnugc4rxtX5UXOU\n" + 
			"RJVVOyHoxRPyVQjnaNa0FPtV4eHZ7iNaMpiHg3zxS5HQAYKBsPHawLrprVGIJ/0F\n" + 
			"GRnAumhpKmpa/oE7lUjDozESBHQ8xBBRuM7dLmcV4KnPGKZi048kTx8pNpCjW6/B\n" + 
			"utmd8CO25nNFwtPkBVfX5fyyOeSxwQC9HrFJ3SnGT5FBU7bIU6TxyqkvjvmYzruJ\n" + 
			"Qn2lQ+zkKpPaS3Dy/UcNLFihGZXBqqp8dUGNT7yez35cbAhduP3JwpieX4RTm89K\n" + 
			"rwIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
}
