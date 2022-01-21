package com.patient.constant;

import lombok.NoArgsConstructor;

/**
 * Patient validator constraints.
 * @author Senthil
 */
@NoArgsConstructor
public class Constraints {

	/** The Constant defines Maximum character limit for first name. */
	public static final int FIRST_NAME_MAX_SIZE = 100;


	/** The Constant defines Maximum character limit for last name. */
    public static final int LAST_NAME_MAX_SIZE = 100;


	/** The Constant defines Maximum character limit for sex. */
    public static final int SEX_MAX_SIZE = 1;


	/** The Constant defines Maximum character limit for address. */
    public static final int ADDRESS_MAX_SIZE = 100;


	/** The Constant defines Maximum character limit for phone no. */
    public static final int PHONE_MAX_SIZE = 15;

}
