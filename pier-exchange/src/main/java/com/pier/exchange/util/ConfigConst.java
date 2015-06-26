package com.pier.exchange.util;

public interface ConfigConst {
	
	public String RUN_MODLE = "Run.Module";
	
	/**
	 * Email related.
	 */
	public String EMAIL_HOST_NAME = "email.host.name";
	public String EMAIL_SENDER_NAME = "email.sender.name";
	public String EMAIL_SENDER_PASSWORD = "email.sender.password";
	
	/**
	 * Profile image bucket name related
	 */
	public String PROFILE_IMG_BUCKETNAME = "profile.img.bucketname";
	
	/**
	 * Javascript CORS related;
	 */
	public String CORS_ALLOWED_ORIGINS = "cors.allowed.origins";
	public String CORS_ALLOWED_METHODS = "cors.allowed.methods";
	
	/**
	 * Image related (small image size)
	 */
	public String IMAGE_LOGO_SIZE = "image.logoSize";
	public String IMAGE_PRODUCE_SIZE = "image.productSize";

	/**
	 *  SMS related.
	 */
	public String SMS_ACCOUNT_SID = "sms.account.sid";
	public String SMS_AUTH_TOKEN = "sms.auth.token";
	public String SMS_FROM_NUMBER = "sms.from.number";
	public String SMS_CODE_DISPLAY = "sms.code.display";
	
	/**
	 * url related.
	 */
	public String URL_AGREEMENT_TERM = "url.agreement.term";
	public String URL_AGREEMENT_PRIVACY = "url.agreement.privacy";
	public String URL_CREDIT_API = "url.credit.api";
	public String URL_PASSWORD_RETRIEVE = "url.password.retrieve";
	
	/**
	 * Test DB related.
	 */
	public String DB_TEST_DRIVER_CLASS = "db.test.driverClassName";
	public String DB_TEST_URL = "db.test.url";
	public String DB_TEST_USERNAME = "db.test.username";
	public String DB_TEST_PASSWORD = "db.test.password";
	public String DB_TEST_SCHEMA = "db.test.schema";
	public String DB_TEST_INSTANCE = "db.test.instance";
	public String DB_TEST_INITIAL_SIZE = "db.test.initialSize";
	public String DB_TEST_MAX_ACTIVE = "db.test.maxActive";

	/**
	 * Produce DB related 
	 */
	public String DB_PRODUCE_DRIVER_CLASS = "db.produce.driverClassName";
	public String DB_PRODUCE_URL = "db.produce.url";
	public String DB_PRODUCE_USERNAME = "db.produce.username";
	public String DB_PRODUCE_PASSWORD = "db.produce.password";
	public String DB_PRODUCE_SCHEMA = "db.produce.schema";
	public String DB_PRODUCE_INITIAL_SIZE = "db.produce.initialSize";
	public String DB_PRODUCE_MAX_ACTIVE = "db.produce.maxActive";
	
	/**
	 * DB ConnectionPool Related
	 */
	public String DB_CONPOOL_VALIDATION_INTERVAL="db.conpool.validation.interval";
	public String DB_CONPOOL_TIME_BETWEEN_EVICTION_RUNS_MILLIS="db.conpool.time.between.eviction.runs.millis";
	public String DB_CONPOOL_MAX_WAIT="db.conpool.max.wait";
	public String DB_CONPOOL_REMOVE_ABANDONED_TIMEOUT="db.conpool.remove.abandoned.timeout";
	public String DB_CONPOOL_MIN_EVICTABLE_IDLE_TIME_MILLS="db.conpool.min.evictable.idle.time.millis";
	public String DB_CONPOOL_MIN_IDLE="db.conpool.min.idle";
	
	/**
	 * APNS Related
	 */
	public String APNS_POOL_SIZE="apns.pool.size";
	public String APNS_CONNECTION_TIMEOUT="apns.connection.timeout";
	public String APNS_READ_TIMEOUT="apns.read.timeout";
	public String APNS_MODULE="apns.module";
	
	/**
	 * Linkedin related.
	 */
	public String LINKEDIN_PROFILE_SELECTOR = "linkedin.profile.selector";
	public String LINKEDIN_REDIRECT_URL = "linkedin.redirect.url";
	public String LINKEDIN_API_KEY = "linkedin.api.key";
	public String LINKEDIN_SECRET_KEY = "linkedin.secret.key";

	/**
	 * Couchbase related;
	 */
	public String COUCHBASE_URL = "couchbase.url";
	public String COUCHBASE_BUCKETNAME = "couchbase.bucketname";
	public String COUCHBASE_PASSWORD = "couchbase.password";
	public String COUCHBASE_IDOLOGY_BUCKETNAME = "couchbase.idology.bucketname";
	public String COUCHBASE_IDOLOGY_PASSWORD = "couchbase.idology.password";

}
