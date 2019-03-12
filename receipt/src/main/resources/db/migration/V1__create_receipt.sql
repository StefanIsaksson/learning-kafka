CREATE TABLE RECEIPT(
                        	ID TEXT PRIMARY KEY NOT NULL UNIQUE,
                        	RECEIPT_NUMBER TEXT NOT NULL UNIQUE,
                        	CLIENT_NAME TEXT NOT NULL,
                        	CLIENT_ADDRESS TEXT NOT NULL,
                        	PAID_AMOUNT REAL NOT NULL,
                        	PAYMENT_DATE TEXT NOT NULL,
							PDF_FILE_NAME TEXT
                        );